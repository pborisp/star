package ru.bank.star.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.bank.star.DTO.RecomendDTO;
import ru.bank.star.repository.RecomendationsRepository;
import ru.bank.star.service.RecomendationRuleSet;
import ru.bank.star.service.RecomendationRuleSetService;

import java.util.List;
import java.util.UUID;

@RestController
public class RecomendationRulSetController {
    private final RecomendationRuleSetService recomendationRuleSetService;

    public RecomendationRulSetController(RecomendationRuleSetService recomendationRuleSetService) {
        this.recomendationRuleSetService = recomendationRuleSetService;
    }

    @GetMapping("recommendation/{user_id}")
    public ResponseEntity<List<RecomendationRuleSet>> getRecomendations(@PathVariable UUID user_id) {
        List<RecomendDTO> recomendDTOS = recomendationRuleSetService.getAllRecomendations(user_id);
        if (recomendDTOS == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recomendDTOS);
    }
}
