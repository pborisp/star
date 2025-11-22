package ru.bank.star.service;

import org.springframework.stereotype.Component;
import ru.bank.star.h2.DTO.RecomendationsDTO;
import ru.bank.star.h2.repository.RecomendDTORepository;
import ru.bank.star.postgresql.repository.RecomendationsRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserOfRecomendationRuleSet implements RecomendationRuleSet {
    RecomendDTORepository recomendDTORepository;
    RecomendationsRepository recomendationsRepository;

    public UserOfRecomendationRuleSet(final RecomendDTORepository recomendDTORepository, final RecomendationsRepository recomendationsRepository) {
        this.recomendDTORepository = recomendDTORepository;
        this.recomendationsRepository = recomendationsRepository;
    }

    @Override
    public Optional<Object> getRecomendationById(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        String queryType = (String) rules.get("query");
        switch (queryType) {
            case "USER_OF" : recomendations = processUserOfCase(id, recomendationsDTO, rules);
                break;
            case "ACTIVE_USER_OF" : recomendations = processActiveUserOfCase(id, recomendationsDTO, rules);
                break;
            case "TRANSACTION_SUM_COMPARE" : recomendations = processTransactionSumCompareCase(id, recomendationsDTO, rules);
                break;
            case "TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW" : recomendations = processTransactionDepositWithdrawCase(id, recomendationsDTO, rules);
                break;
            case "CREDIT" : recomendations = processForCredit(id, recomendationsDTO, rules);
                break;
            case "INVEST_500" : recomendations = processForInvest500(id, recomendationsDTO, rules);
                break;
            case "TOP_SAVING" : recomendations = processForTopSaving(id, recomendationsDTO, rules);
                break;
            default :
                throw new IllegalArgumentException("Unknown query type: " + queryType);
        }
        return Optional.ofNullable(recomendations);
    }

    private RecomendationsDTO processUserOfCase(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getQueryForUserOf(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }

    private RecomendationsDTO processActiveUserOfCase(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getQueryForActiveUserOf(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }

    private RecomendationsDTO processTransactionSumCompareCase(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getQueryForTransactionSumCompareCase(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }

    private RecomendationsDTO processTransactionDepositWithdrawCase(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getQueryForTransactionDepositWithdrawCase(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }
    private RecomendationsDTO processForCredit(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getUserForCredit(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }
    private RecomendationsDTO processForTopSaving(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getUserForTopSaving(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }
    private RecomendationsDTO processForInvest500(UUID id, RecomendationsDTO recomendationsDTO, Map<String, Object> rules) {
        RecomendationsDTO recomendations = new RecomendationsDTO();
        if (!recomendDTORepository.getUserForInvest500(id, rules).isEmpty()) {
            recomendations.setId(recomendationsDTO.getId());
            recomendations.setProduct_id(recomendationsDTO.getProduct_id());
            recomendations.setProduct_name(recomendationsDTO.getProduct_name());
            recomendations.setText(recomendationsDTO.getText());
        }
        return recomendations;
    }
}
