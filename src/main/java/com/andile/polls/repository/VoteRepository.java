package com.andile.polls.repository;

import com.andile.polls.models.ChoiceVoteCount;
import com.andile.polls.models.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT NEW com.andile.polls.model.ChoiceVoteCount(v.choice.id,count(v.id)) FROM Vote v.poll.id in :pollIds GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollsIds") List<Long> pollIds);

    @Query("SELECT NEW com.andile.polls.model.ChoiceVoteCount(v.choice.id,count(v.id)) FROM Vote v.poll.id = :pollId GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollsIds")Long pollIds);

    @Query("SELECT v FROM  Vote where v.user.id = :userId and v.poll.id in :pollIds")
    List<Vote> findByUserIdAndPollIdIn(@Param("userId")Long userId,@Param("pollId")List<Long> pollsIds);

    @Query("SELECT v FROM  Vote where v.user.id = :userId and v.poll.id in= :pollId")
    Vote findByUserIdAndPollId(@Param("userId")Long userId,@Param("pollId")Long pollId);

    @Query("SELECT v COUNT(v.id) FROM Vote v WHERE v.user.id = :userId")
    Long countByUserId(@Param("userId")Long userId);

    @Query("SELECT v.poll.id FROM Vote v WHERE v.user.id = :userId")
    List<Vote> findVotedPollIdsByUserId(@Param("userId")Long userId, Pageable pageable);
}