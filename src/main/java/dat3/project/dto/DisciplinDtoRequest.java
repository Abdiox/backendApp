package dat3.project.dto;

import dat3.project.Enum.DisciplineType;
import dat3.project.Enum.ResultType;
import dat3.project.entity.Disciplin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisciplinDtoRequest {
    private int id;
    private String name;
    private DisciplineType disciplineType; // Include the DisciplineType
    private ResultType resultType;

    public DisciplinDtoRequest(Disciplin disciplin) {
        this.id = disciplin.getId();
        this.name = disciplin.getName();
        this.disciplineType = disciplin.getDisciplineType();
        this.resultType = disciplin.getResultType();
    }
}
