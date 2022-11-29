package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExt {

    Optional<User> findByUserName(String userName);

    User findOneByUserName(String userName);

    Optional<User> findOneByEmail(String username);

    User findOneById(Long contactId);

    Optional<User> findByPhoneNumber(String phoneNumber);


    @Query(value = "select u from Users u " +
            "where u.userName like :userName and u.phoneNumber like :phoneNumber " +
            " and u.email like :email" )
    Page<User> findBySearchUserVm(@Param("userName") String name, @Param("phoneNumber") String phoneNumber, @Param("email") String email, Pageable pageable);
}