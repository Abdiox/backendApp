package dat3.project.entity;

import dat3.project.Enum.ResultType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int resultValue;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    @ManyToOne
    @JoinColumn(name = "disciplin_id", nullable = false)
    private Disciplin disciplin;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    public Result() {
    }

    public Result(int resultValue, LocalDateTime date, ResultType resultType, Disciplin disciplin, Participant participant) {
        this.resultValue = resultValue;
        this.date = date;
        this.resultType = resultType;
        this.disciplin = disciplin;
        this.participant = participant;
    }

    // Getters and setters
}
