package ru.bank.star.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.bank.star.DTO.RecomendDTO;
import ru.bank.star.repository.RecomendationsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RecomendationRuleSetService {
    private final List<RecomendationRuleSet> recomendstionService;

    @Autowired
    public RecomendationRuleSetService(List<RecomendationRuleSet> recomendstionService) {
        this.recomendstionService = recomendstionService;
    }

    public List<Optional<RecomendDTO>> getAllRecomendations(UUID id) {
        return recomendstionService.stream()
                .map(recomendstionService -> recomendstionService.getRecomendationById(id))
                .collect(Collectors.toList());
    }
}
