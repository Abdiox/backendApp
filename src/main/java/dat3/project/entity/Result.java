package dat3.project.entity;

import dat3.project.Enum.ResultType;
import dat3.project.entity.Disciplin;
import dat3.project.entity.Participant;
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

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    private LocalDateTime date;
    private int resultValue;

    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public Result(ResultType resultType, LocalDateTime date, int resultValue, Disciplin disciplin, Participant participant) {
        this.resultType = resultType;
        this.date = date;
        this.resultValue = resultValue;
        this.disciplin = disciplin;
        this.participant = participant;
    }

    public Result() {

    }
}
