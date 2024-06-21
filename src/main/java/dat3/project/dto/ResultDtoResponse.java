package dat3.project.dto;

import dat3.project.Enum.ResultType;
import dat3.project.entity.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResultDtoResponse {
    private int id;
    private ResultType resultType;
    private LocalDateTime date;
    private int resultValue;
    private String participantName;
    private String disciplinName;

    public ResultDtoResponse(Result result) {
        this.id = result.getId();
        this.resultType = result.getResultType();
        this.date = result.getDate();
        this.resultValue = result.getResultValue();
        this.participantName = result.getParticipant().getName();
        this.disciplinName = result.getDisciplin().getName();
    }
}

