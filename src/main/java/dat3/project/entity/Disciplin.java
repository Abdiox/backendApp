package dat3.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Enum resultType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Participant participant;

    public Disciplin() {
    }

    public Disciplin(String name, Enum resultType) {
        this.name = name;
        this.resultType = resultType;
    }
}
