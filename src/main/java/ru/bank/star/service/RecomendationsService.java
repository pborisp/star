package ru.bank.star.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.star.h2.DTO.RecomendationsDTO;
import ru.bank.star.postgresql.model.Recomendations;
import ru.bank.star.postgresql.repository.RecomendationsRepository;

import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class RecomendationsService {
    private final List<RecomendationRuleSet> recomendationRuleSets;
    private final RecomendationsRepository recomendationsRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(RecomendationsService.class);

    @Autowired
    public RecomendationsService(final List<RecomendationRuleSet> recomendationRuleSets, final RecomendationsRepository recomendationsRepository) {
        this.recomendationRuleSets = recomendationRuleSets;
        this.recomendationsRepository = recomendationsRepository;
    }

    public void createRuleRecomendation(RecomendationsDTO dto) throws Exception {
        Recomendations recomendations = new Recomendations();
        recomendations.setId(dto.getId());
        recomendations.setProduct_id(dto.getProduct_id());
        recomendations.setProduct_name(dto.getProduct_name());
        recomendations.setText(dto.getText());

        ObjectMapper mapper = new ObjectMapper();
        recomendations.setRulesJson(mapper.writeValueAsString(dto.getRuls()));
        recomendationsRepository.save(recomendations);
    }

    public void deleteRuleSet(Long id) {
        recomendationsRepository.deleteById(id);
    }

    @Cacheable(value="recomendationsCache")
    public Collection<Optional<RecomendationsDTO>> getAllRule() {
        List<Recomendations> recomendations = recomendationsRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        Collection<Optional<RecomendationsDTO>> dtos = new ArrayList<>();
        for (Recomendations recomendation : recomendations) {
            try {
                TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {
                };
                Map<String, Object> params = mapper.readValue(recomendation.getRulesJson(), typeRef);
                RecomendationsDTO dto = new RecomendationsDTO();
                dto.setId(recomendation.getId());
                dto.setProduct_id(recomendation.getProduct_id());
                dto.setProduct_name(recomendation.getProduct_name());
                dto.setText(recomendation.getText());
                dto.setRuls(params);
                dtos.add(Optional.of(dto));
            } catch (IOException e) {
                throw new RuntimeException("Ошибка десериализации параметра: " + e.getMessage(), e);
            }
        }
        return dtos;
    }
}
