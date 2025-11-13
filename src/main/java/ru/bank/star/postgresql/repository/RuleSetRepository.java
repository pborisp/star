package ru.bank.star.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.star.postgresql.repository.model.RuleSet;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface RuleSetRepository extends JpaRepository<RuleSet, Long> {
    List<RuleSet> findAll();

}
