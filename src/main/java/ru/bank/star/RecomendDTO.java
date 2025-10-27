import java.util.Objects;
import java.util.UUID;

public class RecomendDTO {
    private UUID idPtoduct;
    private String name;
    private String textRecomendation;

    public RecomendDTO(UUID id, String name, String textRecomendation) {
        this.idPtoduct = id;
        this.name = name;
        this.textRecomendation = textRecomendation;
    }

    public UUID getIdPtoduct() {
        return idPtoduct;
    }

    public void setIdPtoduct(UUID idPtoduct) {
        this.idPtoduct = idPtoduct;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RecomendDTO that = (RecomendDTO) o;
        return Objects.equals(idPtoduct, that.idPtoduct) && Objects.equals(name, that.name) && Objects.equals(textRecomendation, that.textRecomendation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPtoduct, name, textRecomendation);
    }

    @Override
    public String toString() {
        return "RecomendDTO{" +
                "id=" + idPtoduct +
                ", name='" + name + '\'' +
                ", textRecomendation='" + textRecomendation + '\'' +
                '}';
    }
}