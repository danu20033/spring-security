package com.danushka.test.springsecurityclient.controller;

import com.danushka.test.springsecurityclient.entity.User;
import com.danushka.test.springsecurityclient.entity.VerificationToken;
import com.danushka.test.springsecurityclient.event.RegistrationCompleteEvent;
import com.danushka.test.springsecurityclient.model.UserModel;
import com.danushka.test.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {

        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User verifies successfully";
        }
        return "Bad User";
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerification(@RequestParam("token") String token,HttpServletRequest request){
        VerificationToken verificationToken = userService.generateVerificationToken(token);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user,applicationUrl(request),verificationToken);
        return "Verification Link sent.";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl,VerificationToken verificationToken) {
        String url = applicationUrl+"/resendVerificationToken?token="+verificationToken.getToken();
        //send verification email
        log.info("Click the link to verify your account:{}",url);

    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
