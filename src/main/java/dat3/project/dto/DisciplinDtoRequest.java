package dat3.project.dto;

import dat3.project.entity.Disciplin;
import dat3.project.Enum.ResultType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisciplinDtoRequest {
    private int id;
    private String name;
    private ResultType resultType;

    public DisciplinDtoRequest(Disciplin disciplin) {
        this.id = disciplin.getId();
        this.name = disciplin.getName();
        this.resultType = disciplin.getResultType();
    }
}
