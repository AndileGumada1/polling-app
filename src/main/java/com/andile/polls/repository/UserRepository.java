package com.andile.polls.repository;

import com.andile.polls.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    List<User> findByIdIn(List<Long> creatorIds);

    <T> Optional<T> findByUsername(String username);
}