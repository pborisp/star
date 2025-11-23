package ru.bank.star.h2.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecomendationsDTO {
    private Long id;
    private String product_name;
    private UUID product_id;
    private String text;
    private Map<String, Object> Ruls;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(final String product_name) {
        this.product_name = product_name;
    }

    public UUID getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(final UUID product_id) {
        this.product_id = product_id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public Map<String, Object> getRuls() {
        return this.Ruls;
    }

    public void setRuls(Map<String, Object> ruls) {
        this.Ruls = ruls;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final RecomendationsDTO that = (RecomendationsDTO) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.product_name, that.product_name) && Objects.equals(this.product_id, that.product_id) && Objects.equals(this.text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.product_name, this.product_id, this.text);
    }

    @Override
    public String toString() {
        return "RecomendationsDTO{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", product_id=" + product_id +
                ", text='" + text + '\'' +
                ", Ruls=" + Ruls +
                '}';
    }
}
