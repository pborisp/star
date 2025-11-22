package ru.bank.star.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="rule_statistics")
@Getter
@Setter
public class RuleStatistics implements Serializable {
    @Id
    private UUID ruleId;
    private Integer hitsCount;

    public RuleStatistics(final UUID ruleId, final Integer hitsCount) {
        this.ruleId = ruleId;
        this.hitsCount = hitsCount;
    }

    public RuleStatistics() {

    }

    public UUID getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(final UUID ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getHitsCount() {
        return this.hitsCount;
    }

    public void setHitsCount(final Integer hitsCount) {
        this.hitsCount = hitsCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final RuleStatistics that = (RuleStatistics) o;
        return Objects.equals(this.ruleId, that.ruleId) && Objects.equals(this.hitsCount, that.hitsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ruleId, this.hitsCount);
    }

    @Override
    public String toString() {
        return "RuleStatistics{" +
                "ruleid=" + ruleId +
                ", hitsCount=" + hitsCount +
                '}';
    }
}

