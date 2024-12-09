package com.codingnomads.corespring.examples.application_context.customeventlistener;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SecondUserRegistrationCompletedEventListener implements ApplicationListener<UserRegistrationCompletedEvent> {

    @Override
    public void onApplicationEvent(UserRegistrationCompletedEvent event) {
        System.out.println("I'm a second listener!");
    }
}
