package ru.bank.star.service;

import org.springframework.stereotype.Component;
import ru.bank.star.DTO.RecomendDTO;
import ru.bank.star.repository.RecomendationsRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class Invest500RecomendationRuleSet implements RecomendationRuleSet {
    RecomendationsRepository recomendationsRepository;

    @Override
    public RecomendDTO getRecomendationById(UUID id) {
        RecomendDTO recomendDTO;
        StringBuilder sb = new StringBuilder("Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!");
        if (recomendationsRepository.getUserForInvest500(id).equals(id)) {
            recomendDTO.setIdProduct("147f6a0f-3b91-413b-ab99-87f081d60d5a");
            recomendDTO.setName("Invest 500");
            recomendDTO.setTextRecomendation(sb);
        }
        return recomendDTO;
    }
}
