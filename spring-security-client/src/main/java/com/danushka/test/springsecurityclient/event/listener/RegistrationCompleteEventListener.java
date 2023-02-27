package com.danushka.test.springsecurityclient.event.listener;

import com.danushka.test.springsecurityclient.entity.User;
import com.danushka.test.springsecurityclient.event.RegistrationCompleteEvent;
import com.danushka.test.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token,user);
        //Send mail to the user

        String url = event.getApplicationUrl()+"/verifyRegistration?token="+token;
        //send Verification email
        log.info("Click the link to verify your account: {}" , url);
    }
}
