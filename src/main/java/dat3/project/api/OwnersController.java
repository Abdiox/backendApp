package dat3.project.api;

import dat3.project.dto.OwnersDtoRequest;
import dat3.project.dto.OwnersDtoResponse;
import dat3.project.service.OwnersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnersController {
    private final OwnersService ownersService;

    public OwnersController(OwnersService ownersService) {
        this.ownersService = ownersService;
    }

    @GetMapping
    public ResponseEntity<List<OwnersDtoResponse>> getAllOwners() {
        return ResponseEntity.ok(ownersService.getAllOwners());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OwnersDtoResponse> getOwnersById(@PathVariable Integer id) {
        return ResponseEntity.ok(ownersService.getOwnersById(id));
    }

    @PostMapping
    public ResponseEntity<OwnersDtoResponse> addOwners(@RequestBody OwnersDtoRequest request) {
        OwnersDtoResponse response = ownersService.addOwners(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<OwnersDtoResponse> editOwners(@RequestBody OwnersDtoRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(ownersService.editOwners(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteOwners(@PathVariable Integer id) {
        ownersService.deleteOwners(id);
        return ResponseEntity.ok().build();
    }
}
