package ru.bank.star.postgresql.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.star.postgresql.model.RuleStatistics;

import java.util.UUID;

public interface RuleStatisticsRepository extends CrudRepository<RuleStatistics, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rule_statistics (ruleid, hitscount) VALUES (:ruleOfId, 1) ON CONFLICT (ruleid) DO UPDATE SET hitscount = rule_statistics.hitscount + 1", nativeQuery = true)
    void createOrUpdateHitCount(UUID ruleOfId);

}
