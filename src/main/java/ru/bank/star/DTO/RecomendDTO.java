package ru.bank.star.DTO;

import java.util.Objects;
import java.util.UUID;

public class RecomendDTO {
    private String idProduct;
    private String name;
    private StringBuilder textRecomendation;

    public RecomendDTO(String idProduct, String name, StringBuilder textRecomendation) {
        this.idProduct = idProduct;
        this.name = name;
        this.textRecomendation = textRecomendation;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringBuilder getTextRecomendation() {
        return this.textRecomendation;
    }

    public void setTextRecomendation(final StringBuilder textRecomendation) {
        this.textRecomendation = textRecomendation;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final RecomendDTO that = (RecomendDTO) o;
        return Objects.equals(this.idProduct, that.idProduct) && Objects.equals(this.name, that.name) && Objects.equals(this.textRecomendation, that.textRecomendation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idProduct, this.name, this.textRecomendation);
    }

    @Override
    public String toString() {
        return "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", textRecomendation='" + textRecomendation + '\'';
    }
}