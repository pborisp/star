package ru.bank.star.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.star.postgresql.model.RuleSet;

import java.util.List;

public interface RuleSetRepository extends JpaRepository<RuleSet, Long> {
    List<RuleSet> findAll();

}
