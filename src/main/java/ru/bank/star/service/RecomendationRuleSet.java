package ru.bank.star.service;

import ru.bank.star.h2.DTO.RecomendationsDTO;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface RecomendationRuleSet {
    Optional<Object> getRecomendationById(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules);
}
