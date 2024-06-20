package dat3.project.dto;

import dat3.project.entity.Pets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetsDtoRequest {
    private int id;
    private String name;
    private String species;
    private String color;

    public PetsDtoRequest(Pets pets) {
        this.id = pets.getId();
        this.name = pets.getName();
        this.species = pets.getSpecies();
        this.color = pets.getColor();
    }
}
