package ru.bank.star.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bank.star.postgresql.model.Messages;

public interface MessagesRepository  extends JpaRepository<Messages, Long> {
    @Query(value = "SELECT messages.id FROM messages m WHERE m.chats_id = chatId", nativeQuery = true)
    Long getIdMessagesByChatId(Long chatId);
}
