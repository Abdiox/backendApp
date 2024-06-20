package dat3.project.api;

import dat3.project.dto.PetsDtoRequest;
import dat3.project.dto.PetsDtoResponse;
import dat3.project.service.PetsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping
    public ResponseEntity<List<PetsDtoResponse>> getAllPets() {
        return ResponseEntity.ok(petsService.getAllPets());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PetsDtoResponse> getPetsById(@PathVariable Integer id) {
        return ResponseEntity.ok(petsService.getPetsById(id));
    }

    @PostMapping
    public ResponseEntity<PetsDtoResponse> addPets(@RequestBody PetsDtoRequest request) {
        PetsDtoResponse response = petsService.addPets(request);
        return ResponseEntity.status(201).body(response); // 201 Created
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PetsDtoResponse> editPets(@RequestBody PetsDtoRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(petsService.editPets(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePets(@PathVariable Integer id) {
        return petsService.deletePets(id);
    }
}
