package dat3.project.dto;

import dat3.project.Enum.ResultType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResultDtoRequest {
    private int resultValue;
    private LocalDateTime date;
    private ResultType resultType;
    private int participantId;
    private int disciplinId;
}
