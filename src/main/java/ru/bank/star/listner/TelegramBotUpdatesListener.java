package ru.bank.star.listner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.star.h2.DTO.RecomendationsDTO;
import ru.bank.star.h2.repository.RecomendDTORepository;

import jakarta.annotation.PostConstruct;
import ru.bank.star.service.DinamicRecomendationService;

import java.util.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private RecomendDTORepository recomendDTORepository;
    @Autowired
    private DinamicRecomendationService dinamicRecomendationService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            String str = update.message().text();

            if (str.equals("/recommend username")) {
                SendMessage sendMessage = new SendMessage(update.message().chat().id(), "Здравствуйте " + update.message().chat().firstName() + " " + update.message().chat().lastName() + "!");
                telegramBot.execute(sendMessage);

                if (recomendDTORepository.getIdUser(update.message().chat().firstName(), update.message().chat().lastName()) == null) {
                    SendMessage sendMessage2 = new SendMessage(update.message().chat().id(), "Пользователь не найден");
                    telegramBot.execute(sendMessage2);
                } else {
                    UUID userId = recomendDTORepository.getIdUser(update.message().chat().firstName(), update.message().chat().lastName());
                    Collection<Optional<Object>> results = dinamicRecomendationService.getAllRecomendations(userId);
                    SendMessage sendMessage2 = new SendMessage(update.message().chat().id(), "Новые продукты для вас: \n" + formatToTelegram(results));
                    telegramBot.execute(sendMessage2);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private String formatToTelegram(Collection<Optional<Object>> results) {
        StringBuilder message = new StringBuilder();
        boolean hasResults = false;

        for (Optional<Object> optRec : results) {
            if (optRec.isPresent()) {
                Object obj = optRec.get();
                if (obj instanceof RecomendationsDTO) {
                    RecomendationsDTO dto = (RecomendationsDTO) obj;
                    message.append("Product ID:    ").append(dto.getProduct_id()).append("\n")
                            .append("Продукт:     ").append(dto.getProduct_name()).append("\n")
                            .append("Описание:     ").append(dto.getText()).append("\n").append("\n");
                    hasResults = true;
                }
            }
        }
        if (!hasResults) {
            message.append("_Нет рекомендаций._");
        }
        return message.toString();
    }
}
