package ru.bank.star.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.star.postgresql.model.Chats;

public interface ChatsRepository extends JpaRepository<Chats, Long> {
}
