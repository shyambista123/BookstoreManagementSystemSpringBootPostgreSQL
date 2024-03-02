package com.spring.bookstore.event.listener;

import com.spring.bookstore.event.RegistrationCompleteEvent;
import com.spring.bookstore.model.User;
import com.spring.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;
    private User thUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        thUser = event.getUser();
        String verificationToken = UUID.randomUUID().toString();
        userService.saveVerificationToken(thUser,verificationToken);
        String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
        log.info("Click the link to verify or enable user : {}", url);
    }
}
