package ru.bank.star.postgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name = "recomendations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recomendations implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product_name;
    private UUID product_id;
    private String text;
    private String rulesJson;


    public Recomendations(final Long id, final String product_name, final UUID product_id, final String text, final String ruleSets) {
        this.id = id;
        this.product_name = product_name;
        this.product_id = product_id;
        this.text = text;
        this.rulesJson = ruleSets;
    }

    public Recomendations() {
    }

    public void setRulesJson(final String rulesJson) {
        this.rulesJson = rulesJson;
    }

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

    public String getRulesJson() {
        return this.rulesJson;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final Recomendations that = (Recomendations) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.product_name, that.product_name) && Objects.equals(this.product_id, that.product_id) && Objects.equals(this.text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.product_name, this.product_id, this.text);
    }

    @Override
    public String toString() {
        return "Recomendations{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", product_id=" + product_id +
                ", text=" + text +
                '}';
    }
}
