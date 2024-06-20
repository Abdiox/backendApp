package dat3.project.dto;

import dat3.project.entity.Disciplin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisciplinDtoResponse {
    private int id;
    private String name;
    private Enum resultType;

    public DisciplinDtoResponse(Disciplin disciplin) {
        this.id = disciplin.getId();
        this.name = disciplin.getName();
        this.resultType = disciplin.getResultType();
    }
}
