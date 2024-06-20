package dat3.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDtoRequest {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String club;
    private List<Integer> disciplineIds;
}
