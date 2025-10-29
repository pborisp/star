package ru.bank.star.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.bank.star.DTO.RecomendDTO;
import ru.bank.star.repository.RecomendationsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class RecomendationRuleSetService {
    private final List<RecomendationRuleSet> allRecomendations;

    @Autowired
    public RecomendationRuleSetService(List<RecomendationRuleSet> allRecomendations) {
        this.allRecomendations = allRecomendations;
    }

    public List<RecomendationRuleSet> getAllRecomendations(UUID id) {
        for (RecomendationRuleSet recomendations : allRecomendations) {
            recomendations.getRecomendationById(id);
        }
        return allRecomendations;
    }
}
