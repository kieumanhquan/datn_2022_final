package com.itsol.recruit.security;


import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {


    public final UserRepository userRepositoryJpa;

    public UserDetailServiceImpl(UserRepository userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepositoryJpa.findByUserName(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("username not found");
        }

        if (!user.get().isActive()) {
            throw new UserNotActivatedException("User " + username + " was not activated");
        }

        List<GrantedAuthority> grantedAuthorities
                = user.get().getRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getCode())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), grantedAuthorities);

    }
}
