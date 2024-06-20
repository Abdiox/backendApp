package dat3.project.service;

import dat3.project.dto.PetsDtoRequest;
import dat3.project.dto.PetsDtoResponse;
import dat3.project.entity.Pets;
import dat3.project.repository.PetsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetsService {

    private final PetsRepository petsRepository;

    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    /**
     * Gets all pets
     * @return A list of pets
     */
    public List<PetsDtoResponse> getAllPets() {
        List<Pets> pets = petsRepository.findAll();
        return pets.stream()
                .map(PetsDtoResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets a pet by id
     * @param id The id of the pet
     * @return The pet
     */
    public PetsDtoResponse getPetsById(int id) {
        Pets pets = petsRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pets not found"));
        return new PetsDtoResponse(pets);
    }

    /**
     * Adds a pet
     * @param request The request containing the pet data
     * @return The added pet
     */
    public PetsDtoResponse addPets(PetsDtoRequest request) {
        Pets newPets = new Pets();
        updatePets(newPets, request);
        petsRepository.save(newPets);
        return new PetsDtoResponse(newPets);
    }

    /**
     * Edits a pet
     * @param request The request containing the pet data
     * @param id The id of the pet
     * @return The edited pet
     */
    public PetsDtoResponse editPets(PetsDtoRequest request, int id) {
        Pets pets = petsRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pets not found"));
        updatePets(pets, request);
        petsRepository.save(pets);
        return new PetsDtoResponse(pets);
    }

    /**
     * Deletes a pet
     * @param id The id of the pet
     * @return A response entity
     */
    public ResponseEntity<Void> deletePets(int id) {
        Pets pets = petsRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pets not found"));
        petsRepository.delete(pets);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates a pet
     * @param pets The pet to update
     * @param request The request containing the pet data
     */
    private void updatePets(Pets pets, PetsDtoRequest request) {
        pets.setName(request.getName());
        pets.setSpecies(request.getSpecies());
        pets.setColor(request.getColor());
        // Add logic here if you want to set the owner
        // pets.setOwner(request.getOwner());
    }
}
