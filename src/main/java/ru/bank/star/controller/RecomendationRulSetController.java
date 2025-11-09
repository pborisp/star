package ru.bank.star.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bank.star.DTO.RecomendDTO;
import ru.bank.star.service.RecomendationRuleSetService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("recomendations")
public class RecomendationRulSetController {
    private final RecomendationRuleSetService recomendationRuleSetService;

    public RecomendationRulSetController(RecomendationRuleSetService recomendationRuleSetService) {
        this.recomendationRuleSetService = recomendationRuleSetService;
    }

    @GetMapping("{user_id}")
    public ResponseEntity<List<Optional<RecomendDTO>>> getRecomendations(@PathVariable UUID user_id) {
        return ResponseEntity.ok(recomendationRuleSetService.getAllRecomendations(user_id));
    }
}
