package dat3.project.dto;

import dat3.project.Enum.ResultType;
import dat3.project.entity.Result;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResultDtoResponse {
    private int id;
    private int resultValue;
    private LocalDateTime date;
    private ResultType resultType;
    private int participantId;
    private int disciplinId;


    public ResultDtoResponse(Result result) {
        this.id = result.getId();
        this.resultValue = result.getResultValue();
        this.date = result.getDate();
        this.resultType = result.getResultType();
        if (result.getParticipant() != null) {
            this.participantId = result.getParticipant().getId();
            this.disciplinId = result.getDisciplin().getId();
        }
    }
}
