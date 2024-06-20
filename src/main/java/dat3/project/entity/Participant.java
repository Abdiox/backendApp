package dat3.project.entity;

import dat3.project.entity.Disciplin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private int age;
    private String club;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "participant_disciplin",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplin_id")
    )
    private Set<Disciplin> disciplins;

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Result> results;

    public Participant() {
    }

    public Participant(String name, String gender, int age, String club) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.club = club;
    }
}
