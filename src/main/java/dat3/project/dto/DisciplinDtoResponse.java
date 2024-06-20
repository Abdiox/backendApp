package dat3.project.dto;

import dat3.project.entity.Disciplin;
import dat3.project.entity.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DisciplinDtoResponse {
    private int id;
    private String name;
    private Enum resultType;
    private List<ResultDtoResponse> result;


    public DisciplinDtoResponse(Disciplin disciplin) {
        this.id = disciplin.getId();
        this.name = disciplin.getName();
        this.resultType = disciplin.getResultType();
        this.result = disciplin.getResults().stream().map(ResultDtoResponse::new).collect(java.util.stream.Collectors.toList());
    }
}
