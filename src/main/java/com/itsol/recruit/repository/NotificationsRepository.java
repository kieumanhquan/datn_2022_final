package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Notifications;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications,Long> {

    List<Notifications> findByReceiver(User oneById);
}
