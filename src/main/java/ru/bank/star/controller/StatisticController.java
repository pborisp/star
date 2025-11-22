package ru.bank.star.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bank.star.postgresql.model.RuleStatResponse;
import ru.bank.star.postgresql.repository.RuleStatisticsRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/rule")
public class StatisticController {
    @Autowired
    private RuleStatisticsRepository statisticsRepo;

    @GetMapping("/stat")
    public ResponseEntity<List<RuleStatResponse>> getRuleStats() {
        List<RuleStatResponse> stats = StreamSupport.stream(statisticsRepo.findAll().spliterator(), false)
                .map(RuleStatResponse::fromModel)
                .collect(Collectors.toList());
        System.out.println(stats);
        return ResponseEntity.ok(stats);
    }
}
