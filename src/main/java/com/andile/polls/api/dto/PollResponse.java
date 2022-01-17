package com.andile.polls.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class PollResponse {
    private Long id;
    private String question;
    private List<ChoiceResponse> choice;
    private UserSummary createdBy;
    private Instant creationDateTime;
    private Instant expirationDateTime;
    private Boolean isExpired;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoice;
    private List totalVotes;
}
