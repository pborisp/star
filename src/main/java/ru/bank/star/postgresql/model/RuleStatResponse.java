package ru.bank.star.postgresql.model;

import lombok.Data;

import java.util.UUID;

@Data
public class RuleStatResponse {
    private UUID ruleId;
    private Integer hitsCount;

    public static RuleStatResponse fromModel(RuleStatistics model) {
        RuleStatResponse response = new RuleStatResponse();
        response.ruleId = model.getRuleId();
        response.hitsCount = model.getHitsCount();
        return response;
    }
}
