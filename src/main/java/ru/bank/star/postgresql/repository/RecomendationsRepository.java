package ru.bank.star.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.star.postgresql.model.Recomendations;

public interface RecomendationsRepository extends JpaRepository<Recomendations, Long> {

}
