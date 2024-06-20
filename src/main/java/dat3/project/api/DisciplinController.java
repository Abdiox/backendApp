package dat3.project.api;

import dat3.project.dto.DisciplinDtoResponse;
import dat3.project.dto.DisciplinDtoRequest;
import dat3.project.service.DisciplinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplins")
public class DisciplinController {

    private final DisciplinService disciplinService;

    public DisciplinController(DisciplinService disciplinService) {
        this.disciplinService = disciplinService;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinDtoResponse>> getAllPets() {
        return ResponseEntity.ok(disciplinService.getAllDisciplins());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DisciplinDtoResponse> getPetsById(@PathVariable Integer id) {
        return ResponseEntity.ok(disciplinService.getDisciplinById(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinDtoResponse> addDisciplin(@RequestBody DisciplinDtoRequest request) {
        DisciplinDtoResponse response = disciplinService.addDisciplin(request);
        return ResponseEntity.status(201).body(response); // 201 Created
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DisciplinDtoResponse> editDisciplin(@RequestBody DisciplinDtoRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(disciplinService.editDisciplin(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDisciplin(@PathVariable Integer id) {
        return disciplinService.deleteDisciplin(id);
    }
}
