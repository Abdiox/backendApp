package dat3.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Pets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String species;
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owners owner;

    public Pets() {
    }

    public Pets(String name, String species, String color) {
        this.name = name;
        this.species = species;
        this.color = color;
    }
}
