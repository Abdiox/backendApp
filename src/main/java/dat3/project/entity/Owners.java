package dat3.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Owners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "owner")
    private Set<Pets> pets;

    public Owners() {
    }

    public Owners(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
