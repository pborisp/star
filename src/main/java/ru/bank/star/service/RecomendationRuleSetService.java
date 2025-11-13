package ru.bank.star.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.star.h2.DTO.RecomendDTO;
import ru.bank.star.postgresql.model.RuleSet;
import ru.bank.star.postgresql.repository.RuleSetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RecomendationRuleSetService {
    private final List<RecomendationRuleSet> recomendstionService;
    private final RuleSetRepository ruleSetRepository;

    @Autowired
    public RecomendationRuleSetService(List<RecomendationRuleSet> recomendstionService, RuleSetRepository ruleSetRepository) {
        this.recomendstionService = recomendstionService;
        this.ruleSetRepository = ruleSetRepository;
    }

    public List<Optional<RecomendDTO>> getAllRecomendations(UUID id) {
        return recomendstionService.stream()
                .map(recomendstionService -> recomendstionService.getRecomendationById(id))
                .collect(Collectors.toList());
    }

    public void createRuleSet(RuleSet ruleSet) {
        ruleSetRepository.save(ruleSet);
    }

    public void deleteRuleSet(Long rule_id) {
        ruleSetRepository.deleteById(rule_id);
    }

    public List<RuleSet> getAllRule() {
        return ruleSetRepository.findAll();
    }

}
