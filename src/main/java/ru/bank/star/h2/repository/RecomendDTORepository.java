package ru.bank.star.h2.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RecomendDTORepository {

    private final JdbcTemplate jdbcTemplate;

    public RecomendDTORepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<UUID> getUserForInvest500(UUID user,  Map<String, Object> rules) {
        try {
            Object arguments1 = rules.getOrDefault("arguments1", null);
            Object arguments2 = rules.getOrDefault("arguments2", null);
            Object arguments3 = rules.getOrDefault("arguments3", null);
            Object arguments4 = rules.getOrDefault("arguments4", null);
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING count(DISTINCT CASE WHEN p.TYPE = 'DEBIT' THEN p.id end) > 0 AND NOT EXISTS (SELECT 1 FROM transactions tt JOIN products pp ON tt.product_id = pp.id WHERE tt.user_id = u.id AND pp.TYPE = 'INVEST') AND sum(CASE WHEN p.TYPE = 'SAVING' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) > 1000 AND u.id = ?",
                    UUID.class,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<UUID> getUserForTopSaving(UUID user, Map<String, Object> rules) {
        try {
            Object arguments1 = rules.getOrDefault("arguments1", null);
            Object arguments3 = rules.getOrDefault("arguments3", null);
            Object arguments4 = rules.getOrDefault("arguments4", null);
            Object arguments5 = rules.getOrDefault("arguments5", null);
            Object arguments6 = rules.getOrDefault("arguments6", null);
            Object arguments7 = rules.getOrDefault("arguments7", null);
            Object arguments8 = rules.getOrDefault("arguments8", null);
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING COUNT(DISTINCT CASE WHEN p.TYPE = 'DEBIT' THEN p.id END) > 0 AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' OR p.TYPE = 'SAVING' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) >= 50000 AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) > sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) AND u.id = ?",
                    UUID.class,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<UUID> getUserForCredit(UUID user, Map<String, Object> rules) {
        try {
            Object arguments1 = rules.getOrDefault("arguments1", null);
            Object arguments3 = rules.getOrDefault("arguments3", null);
            Object arguments4 = rules.getOrDefault("arguments4", null);
            Object arguments5 = rules.getOrDefault("arguments5", null);
            Object arguments6 = rules.getOrDefault("arguments6", null);
            Object arguments7 = rules.getOrDefault("arguments7", null);
            Object arguments8 = rules.getOrDefault("arguments8", null);
            Object arguments9 = rules.getOrDefault("arguments49", null);
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING NOT EXISTS (SELECT 1 FROM transactions tt JOIN products pp ON tt.product_id = pp.id WHERE tt.user_id = u.id AND pp.TYPE = 'CREDIT') AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) > sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) > 100000 AND u.id = ?",
                    UUID.class,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<UUID> getQueryForUserOf(UUID user, Map<String, Object> rules) {
        try {
            Object arguments = rules.getOrDefault("arguments", null);
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING count(DISTINCT CASE WHEN p.TYPE = ? THEN p.id end) > 0 AND u.id = ?",
                    UUID.class,
                    arguments,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<UUID> getQueryForActiveUserOf(UUID user, Map<String, Object> rules) {
        try {
            Object arguments = rules.getOrDefault("arguments", null);
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING count(DISTINCT CASE WHEN p.TYPE = ? THEN p.id end) > 4 AND u.id = ?",
                    UUID.class,
                    arguments,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    public Optional<UUID> getQueryForTransactionSumCompareCase(UUID user, Map<String, Object> rules) {
        try {
            Object arguments1 = rules.getOrDefault("arguments1", null);
            Object arguments2 = rules.getOrDefault("arguments2", null);
            Object arguments3 = rules.getOrDefault("arguments3", null);
            Object arguments4 = rules.getOrDefault("arguments4", null);

            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING sum(CASE WHEN p.TYPE = ? AND t.TYPE = ? THEN t.amount ELSE 0 END) " + arguments3 + " ? AND u.id = ?",
                    UUID.class,
                    arguments1,
                    arguments2,
                    arguments4,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<UUID> getQueryForTransactionDepositWithdrawCase(UUID user, Map<String, Object> rules) {
        try {
            Object arguments1 = rules.getOrDefault("arguments1", null);
            Object arguments2 = rules.getOrDefault("arguments2", null);
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id HAVING sum(CASE WHEN p.TYPE = ? AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) " + arguments2 + " sum(CASE WHEN p.TYPE = ? AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) AND u.id = ?",
                    UUID.class,
                    arguments1,
                    arguments1,
                    user
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public UUID getIdUser(String firstName, String lastName) {
        try {
            UUID result = jdbcTemplate.queryForObject(
                    "SELECT u.id FROM users u WHERE u.first_name = ? and u.last_name = ?",
                    UUID.class,
                    firstName,
                    lastName
            );
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
