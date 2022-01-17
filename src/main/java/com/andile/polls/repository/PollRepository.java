package com.andile.polls.repository;

import com.andile.polls.models.Poll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    Optional<Poll> findByCreatedBy(Long userId, Pageable pageable);
}