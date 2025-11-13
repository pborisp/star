package ru.bank.star.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bank.star.h2.DTO.RecomendDTO;
import ru.bank.star.postgresql.model.RuleSet;
import ru.bank.star.service.RecomendationRuleSetService;

import java.util.Collection;
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

    @PostMapping("/rule")
    public ResponseEntity<Void> createRuleSet(@RequestBody RuleSet ruleSet) {
        recomendationRuleSetService.createRuleSet(ruleSet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("user_id")
    public ResponseEntity<Void> deleteRuleSet(@PathVariable Long rule_id) {
        recomendationRuleSetService.deleteRuleSet(rule_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allRule")
    public ResponseEntity<Collection<RuleSet>> getAllRule() {
        return ResponseEntity.ok(recomendationRuleSetService.getAllRule());
    }
}
