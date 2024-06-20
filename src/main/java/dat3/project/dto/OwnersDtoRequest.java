package dat3.project.dto;

import dat3.project.entity.Owners;
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
public class OwnersDtoRequest {
    private int id;
    private String name;
    private int age;
    private List<PetsDtoRequest> pets; // Add pets to handle pet information

    public OwnersDtoRequest(Owners owners) {
        this.id = owners.getId();
        this.name = owners.getName();
        this.age = owners.getAge();
        this.pets = owners.getPets().stream().map(PetsDtoRequest::new).collect(Collectors.toList());
    }
}
