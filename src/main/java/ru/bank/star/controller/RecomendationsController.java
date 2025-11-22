package ru.bank.star.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bank.star.h2.DTO.RecomendationsDTO;
//import ru.bank.star.postgresql.model.RuleSet;
import ru.bank.star.service.DinamicRecomendationService;
import ru.bank.star.service.RecomendationsService;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("recomendations")
public class RecomendationsController {
    private final RecomendationsService recomendationsService;
    private final DinamicRecomendationService dinamicRecomendationService;

    public RecomendationsController(final RecomendationsService recomendationsService, final DinamicRecomendationService dinamicRecomendationService) {
        this.recomendationsService = recomendationsService;
        this.dinamicRecomendationService = dinamicRecomendationService;
    }

    @GetMapping("{user_id}")
    public ResponseEntity<Collection<Optional<Object>>> getRecomendations(@PathVariable UUID user_id) {
        return ResponseEntity.ok(dinamicRecomendationService.getAllRecomendations(user_id));
    }

    @PostMapping("/rule")
    public ResponseEntity<String> createRuleSet(@RequestBody RecomendationsDTO recomendations) throws Exception {
        try {
            recomendationsService.createRuleRecomendation(recomendations);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRuleSet(@PathVariable Long id) {
        recomendationsService.deleteRuleSet(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allRule")
    public ResponseEntity<Collection<Optional<RecomendationsDTO>>> getAllRule() {
        return ResponseEntity.ok(recomendationsService.getAllRule());
    }
}
