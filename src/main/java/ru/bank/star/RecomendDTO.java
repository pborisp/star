package ru.bank.star;

import java.util.UUID;

public class RecomendDTO {
    private UUID id;
    private String name;
    private String textRecomendation;

    public RecomendDTO(UUID id, String name, String textRecomendation) {
        this.id = id;
        this.name = name;
        this.textRecomendation = textRecomendation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextRecomendation() {
        return textRecomendation;
    }

    public void setTextRecomendation(String textRecomendation) {
        this.textRecomendation = textRecomendation;
    }
}
