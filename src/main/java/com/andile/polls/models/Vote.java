package com.andile.polls.models;

import com.andile.polls.models.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "votes", uniqueConstraints={
        @UniqueConstraint(columnNames = {
        "poll_id", "user_id"
        })
})
@Getter
@Setter
public class Vote extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "choice_id",nullable = false)
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private Choice choice;
}