package ru.bank.star.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.star.postgresql.repository.RuleStatisticsRepository;

import java.util.UUID;

@Service
public class RuleEngineService {
    @Autowired
    private RuleStatisticsRepository ruleStatisticsRepository;

    public void processRule(UUID ruleId) {
        if (ruleId != null) {
            ruleStatisticsRepository.createOrUpdateHitCount(ruleId);
        }
    }
}
