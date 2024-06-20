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
    private String gender;
    private int age;
    private String club;
    private List<DisciplinDtoResponse> disciplin;
    private List<ResultDtoResponse> result;

    public ParticipantDtoResponse(Participant participant) {
        this.id = participant.getId();
        this.name = participant.getName();
        this.gender = participant.getGender();
        this.age = participant.getAge();
        this.club = participant.getClub();
        this.disciplin = participant.getDisciplins().stream().map(DisciplinDtoResponse::new).collect(Collectors.toList());
    }


}