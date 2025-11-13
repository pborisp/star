package ru.bank.star.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bank.star.h2.DTO.RecomendDTO;
import ru.bank.star.h2.repository.RecomendationsRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class TopSavingRecomendationRuleSet implements RecomendationRuleSet {
    RecomendationsRepository recomendationsRepository;

    @Autowired
    public TopSavingRecomendationRuleSet(final RecomendationsRepository recomendationsRepository) {
        this.recomendationsRepository = recomendationsRepository;
    }

    @Override
    public Optional<RecomendDTO> getRecomendationById(UUID id) {
        RecomendDTO recomendDTO = new RecomendDTO(null, null, null);
        System.out.println(recomendationsRepository.getUserForTopSaving(id) + " - TopSaving");
        StringBuilder sb = new StringBuilder("Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем!\n" +
                "Преимущества «Копилки»:\n" +
                "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.\n" +
                "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.\n" +
                "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.\n" +
                "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!");
        if (!recomendationsRepository.getUserForTopSaving(id).isEmpty()) {
            recomendDTO.setIdProduct("59efc529-2fff-41af-baff-90ccd7402925");
            recomendDTO.setName("Top Saving");
            recomendDTO.setTextRecomendation(sb);
        } else {
            recomendDTO = null;
        }

        return Optional.ofNullable(recomendDTO);
    }
}
