package ru.bank.star.service;

import ru.bank.star.h2.DTO.RecomendDTO;

import java.util.Optional;
import java.util.UUID;

public interface RecomendationRuleSet {
    Optional<RecomendDTO> getRecomendationById(UUID id);
}
