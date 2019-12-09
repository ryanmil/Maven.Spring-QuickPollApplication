package io.zipcoder.tc_spring_poll_application.controller;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ComputeResultController {

    private VoteRepository voteRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<VoteResult> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);

        int count = 0;
        Map<Long, Integer> voteCounts = new HashMap<>();
        for (Vote v : allVotes) {
            Long key = v.getOption().getId();
            if (voteCounts.containsKey(key)) {
                voteCounts.put(key, voteCounts.get(key) + 1);
            } else {
                voteCounts.put(key, 1);
            }
            count++;
        }

        List<OptionCount> optionCounts = new ArrayList<>();
        for (Map.Entry<Long, Integer> e : voteCounts.entrySet()) {
            optionCounts.add(new OptionCount(e.getKey(), e.getValue()));
        }

        voteResult.setResults(optionCounts);
        voteResult.setTotalVotes(count);

        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }
}

