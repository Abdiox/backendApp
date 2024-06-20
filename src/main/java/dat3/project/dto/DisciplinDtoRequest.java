package dat3.project.dto;

import dat3.project.entity.Disciplin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinDtoRequest {
    private int id;
    private String name;
   private Enum resultType;

    public DisciplinDtoRequest(Disciplin disciplin) {
        this.id = disciplin.getId();
        this.name = disciplin.getName();
        this.resultType = disciplin.getResultType();

    }
}
