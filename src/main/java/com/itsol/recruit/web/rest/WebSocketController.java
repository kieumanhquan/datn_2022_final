package com.itsol.recruit.web.rest;

import com.itsol.recruit.entity.Notifications;
import com.itsol.recruit.service.NotificationsService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    private final NotificationsService notificationsService;

    public WebSocketController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @MessageMapping("/job-register")
    @SendTo("/topic/apply")
    public Notifications applyOnJob(Notifications notifications) {
        return notificationsService.add(notifications);
    }

}
