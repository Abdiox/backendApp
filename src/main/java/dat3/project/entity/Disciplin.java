package dat3.project.entity;

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

    @Enumerated(EnumType.STRING)
    private ResultType resultType; // Use the ResultType enum

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @OneToMany(mappedBy = "disciplin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Result> results;

    public Disciplin() {
    }

    public Disciplin(String name, ResultType resultType, Participant participant) {
        this.name = name;
        this.resultType = resultType;
        this.participant = participant;
    }
}
