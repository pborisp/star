package ru.bank.star.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RecomendationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecomendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID getUserForInvest500(UUID user) {
        UUID result = jdbcTemplate.queryForObject(
                "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id " +
                        "HAVING count(DISTINCT CASE WHEN p.TYPE = 'DEBIT' THEN p.id end) > 0 " +
                        "AND NOT EXISTS (SELECT 1 FROM transactions tt JOIN products pp ON tt.product_id = pp.id WHERE tt.user_id = u.id AND pp.TYPE = 'INVEST') " +
                        "AND sum(CASE WHEN p.TYPE = 'SAVING' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) > 1000 " +
                        "AND u.id = ?",
                UUID.class,
                user
        );
        return result;
    }

    public UUID getUserForTopSaving(UUID user) {
        UUID result = jdbcTemplate.queryForObject(
                "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id " +
                        "HAVING COUNT(DISTINCT CASE WHEN p.TYPE = 'DEBIT' THEN p.id END) > 0 " +
                        "AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' OR p.TYPE = 'SAVING' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) >= 50000 " +
                        "AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) > sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) " +
                        "AND u.id = ?",
                UUID.class,
                user
        );
        return result;
    }

    public UUID getUserForCredit(UUID user) {
        UUID result = jdbcTemplate.queryForObject(
                "SELECT DISTINCT u.id FROM users u JOIN transactions t ON u.id = t.user_id JOIN products p ON t.product_id = p.id GROUP BY u.id " +
                        "HAVING NOT EXISTS (SELECT 1 FROM transactions tt JOIN products pp ON tt.product_id = pp.id WHERE tt.user_id = u.id AND pp.TYPE = 'CREDIT') " +
                        "AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) > sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) " +
                        "AND sum(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW' THEN t.amount ELSE 0 END) > 100000 " +
                        "AND u.id = ?",
                UUID.class,
                user
        );
        return result;
    }
}
