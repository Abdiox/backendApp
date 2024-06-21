package dat3.project.api;

import dat3.project.dto.ParticipantDtoRequest;
import dat3.project.dto.ParticipantDtoResponse;
import dat3.project.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<ParticipantDtoResponse> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ParticipantDtoResponse getParticipantById(@PathVariable int id) {
        return participantService.getParticipantById(id);
    }

    @PostMapping
    public ParticipantDtoResponse addParticipant(@RequestBody ParticipantDtoRequest request) {
        return participantService.addParticipant(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDtoResponse> editParticipant(@RequestBody ParticipantDtoRequest request, @PathVariable int id) {
        try {
            ParticipantDtoResponse updatedParticipant = participantService.editParticipant(request, id);
            return ResponseEntity.ok(updatedParticipant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Integer id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok().build();
    }
}
