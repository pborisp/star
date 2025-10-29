package ru.bank.star.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.bank.star.DTO.RecomendDTO;
import ru.bank.star.repository.RecomendationsRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class CreditRecomendationRuleSet implements RecomendationRuleSet {
    RecomendationsRepository recomendationsRepository;

    @Override
    public RecomendDTO getRecomendationById(UUID id) {
        RecomendDTO recomendDTO;
        StringBuilder sb = new StringBuilder("Откройте мир выгодных кредитов с нами!\n" +
                "Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.\n" +
                "Почему выбирают нас:\n" +
                "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.\n" +
                "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.\n" +
                "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.\n" +
                "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!");
        if (recomendationsRepository.getUserForCredit(id).equals(id)) {
            recomendDTO.setIdProduct("ab138afb-f3ba-4a93-b74f-0fcee86d447f");
            recomendDTO.setName("Простой кредит");
            recomendDTO.setTextRecomendation(sb);
        } else {
            recomendDTO = null;
        }
        return recomendDTO;
    }
}
