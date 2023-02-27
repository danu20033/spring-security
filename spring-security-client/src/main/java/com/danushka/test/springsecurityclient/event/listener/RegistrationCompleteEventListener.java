package com.danushka.test.springsecurityclient.entity.listener;

import com.danushka.test.springsecurityclient.event.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification token for the user with link
        User user = event.

        //Send mail to the user
    }
}
