package dat3.project.dto;

import dat3.project.entity.Owners;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class OwnersDtoResponse {
    private int id;
    private String name;
    private int age;
    private List<PetsDtoResponse> pets; // Add pets to include pet information in the response

    public OwnersDtoResponse(Owners owners) {
        this.id = owners.getId();
        this.name = owners.getName();
        this.age = owners.getAge();
        this.pets = owners.getPets().stream().map(PetsDtoResponse::new).collect(Collectors.toList());
    }
}
