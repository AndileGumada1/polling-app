package com.andile.polls.utils;

import com.andile.polls.api.dto.ChoiceResponse;
import com.andile.polls.api.dto.PollResponse;
import com.andile.polls.api.dto.UserSummary;
import com.andile.polls.models.Poll;
import com.andile.polls.models.User;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToResponse(Poll poll, Map<Long,Long> choiceVotesMap, User creator,Long userVote){

        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        Instant now = Instant.now();
        pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());

            if (choiceVotesMap.containsKey(choice.getId())){
                choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
            }else {
                choiceResponse.setVoteCount(0);
            }
            return choiceResponse;
        }).collect(Collectors.toList());

        pollResponse.setChoice(choiceResponses);

        UserSummary userSummary = new UserSummary(creator.getId(),creator.getUsername(),creator.getName());
        pollResponse.setCreatedBy(userSummary);

        if (userVote != null){
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoice().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(Collections.singletonList(totalVotes));

        return pollResponse;
    }
}
