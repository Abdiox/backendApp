package dat3.project.dto;

import dat3.project.entity.Participant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDtoResponse {
    private int id;
    private String name;
    private int age;
    private List<DisciplinDtoResponse> disciplin; // Add pets to include pet information in the response

    public ParticipantDtoResponse(Participant participant) {
        this.id = participant.getId();
        this.name = participant.getName();
        this.age = participant.getAge();
        this.disciplin = participant.getDisciplins().stream().map(DisciplinDtoResponse::new).collect(Collectors.toList());
    }
}
