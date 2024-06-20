package dat3.project.entity;

import dat3.project.Enum.DisciplineType;
import dat3.project.Enum.ResultType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private DisciplineType disciplineType;
    private ResultType resultType;

    @ManyToMany(mappedBy = "disciplins", fetch = FetchType.LAZY)
    private Set<Participant> participants;

    @OneToMany(mappedBy = "disciplin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Result> results;

    public Disciplin() {
    }

    public Disciplin(String name, DisciplineType disciplineType, ResultType resultType) {
        this.name = name;
        this.disciplineType = disciplineType;
        this.resultType = resultType;
    }
}
