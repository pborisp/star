package ru.bank.star.postgresql.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rule_set")
public class RuleSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer version;

        private String query;

        private List<String> arguments;

        private boolean negate;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public List<String> getArguments() {
            return arguments;
        }

        public void setArguments(List<String> arguments) {
            this.arguments = arguments;
        }

        public boolean isNegate() {
            return negate;
        }

        public void setNegate(boolean negate) {
            this.negate = negate;
        }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final RuleSet ruleSet = (RuleSet) o;
        return this.negate == ruleSet.negate && Objects.equals(this.id, ruleSet.id) && Objects.equals(this.query, ruleSet.query) && Objects.equals(this.arguments, ruleSet.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.query, this.arguments, this.negate);
    }

    @Override
    public String toString() {
        return "RuleSet{" +
                "id=" + id +
                ", query='" + query + '\'' +
                ", arguments=" + arguments +
                ", negate=" + negate +
                '}';
    }
}
