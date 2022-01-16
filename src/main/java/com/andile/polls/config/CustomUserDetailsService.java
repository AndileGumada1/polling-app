package com.andile.polls.config;

import com.andile.polls.models.User;
import com.andile.polls.repository.UserRepository;
import com.andile.polls.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    /**
     * @param usernameOrEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException(""+usernameOrEmail));
        return UserPrincipal.create(user);
    }

    /**
     * @param id
     * @return
     */
    public UserDetails loadUserById(Long id){
        User user = repository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id"+ id)
        );
        return UserPrincipal.create(user);
    }
}
