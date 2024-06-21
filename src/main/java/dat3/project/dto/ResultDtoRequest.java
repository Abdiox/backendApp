package dat3.project.dto;

import dat3.project.Enum.ResultType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResultDtoRequest {
    private int id;
    private ResultType resultType;
    private LocalDateTime date;
    private int resultValue;
    private int participantId;
    private int disciplinId;
}
