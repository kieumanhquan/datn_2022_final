package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Notifications;
import com.itsol.recruit.repository.NotificationsRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.NotificationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificationsRepository notificationsRepository;

    private final UserRepository userRepository;

    public NotificationsServiceImpl(NotificationsRepository notificationsRepository, UserRepository userRepository) {
        this.notificationsRepository = notificationsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notifications add(Notifications notifications){
        return notificationsRepository.save(notifications);
    }

    @Override
    public List<Notifications> findAll(){
        return notificationsRepository.findAll();
    }

    @Override
    public List<Notifications> findByUser(Long receiverId){

        return notificationsRepository.findByReceiver(userRepository.findOneById(receiverId));
    }
}

