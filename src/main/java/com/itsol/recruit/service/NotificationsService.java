package com.itsol.recruit.service;

import com.itsol.recruit.entity.Notifications;

import java.util.List;

public interface NotificationsService {
    Notifications add(Notifications notifications);

    List<Notifications> findAll();

    List<Notifications> findByUser(Long receiverId);
}
