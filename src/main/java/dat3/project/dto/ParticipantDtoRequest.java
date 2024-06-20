package dat3.project.dto;

import dat3.project.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDtoRequest {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String club;
    private List<PetsDtoRequest> pets; // Add pets to handle pet information

    public ParticipantDtoRequest(Participant participant) {
        this.id = participant.getId();
        this.name = participant.getName();
        this.gender = participant.getGender();
        this.age = participant.getAge();
        this.club = participant.getClub();
        this.pets = participant.getPets().stream().map(PetsDtoRequest::new).collect(Collectors.toList());
    }
}
