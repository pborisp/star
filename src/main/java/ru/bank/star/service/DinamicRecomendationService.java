package ru.bank.star.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.star.h2.DTO.RecomendationsDTO;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class DinamicRecomendationService{
    private final List<RecomendationRuleSet> recomendationRuleSets;
    private final RecomendationsService recomendationsService;
    private final static Logger LOGGER = LoggerFactory.getLogger(RecomendationsService.class);

    public DinamicRecomendationService(final List<RecomendationRuleSet> recomendationRuleSets, final RecomendationsService recomendationsService) {
        this.recomendationRuleSets = recomendationRuleSets;
        this.recomendationsService = recomendationsService;
    }

    public Collection<Optional<Object>> getAllRecomendations(UUID id) {
        Collection<Optional<RecomendationsDTO>> dtos = recomendationsService.getAllRule();
        List<RecomendationsDTO> recommendations = dtos.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        Collection<Optional<Object>> results = new ArrayList<>();
        for (RecomendationsDTO recomendation : recommendations) {
            Map<String, Object> rules = recomendation.getRuls();
            for (RecomendationRuleSet ruleSet : recomendationRuleSets) {
                Optional<Object> processedRecommendation = ruleSet.getRecomendationById(id, recomendation, rules);
                if (isValidRecommendation(processedRecommendation.get())) {
                    results.add(processedRecommendation);

                }
            }
        }
        return results;
    }
    private boolean isValidRecommendation(Object recommendation) {
        if (recommendation instanceof RecomendationsDTO) {
            RecomendationsDTO dto = (RecomendationsDTO) recommendation;
            return dto.getProduct_name() != null;
        }
        return false;
    }
}
