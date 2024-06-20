package dat3.project.api;


import dat3.project.dto.DisciplinDtoResponse;
import dat3.project.dto.ParticipantDtoRequest;
import dat3.project.dto.ParticipantDtoResponse;
import dat3.project.entity.Participant;
import dat3.project.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<List> getAllParticipants() {
        List<ParticipantDtoResponse> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(participants);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ParticipantDtoResponse> getParticipantsById(@PathVariable Integer id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }
    @GetMapping("/name{name}")
    public ParticipantDtoResponse getParticipantByName(@PathVariable String name) {
        Participant participant = participantService.getParticipantByName(name);
        return new ParticipantDtoResponse(participant);
    }


    @PostMapping
    public ResponseEntity<ParticipantDtoResponse> addParticipant(@RequestBody ParticipantDtoRequest request) {
        ParticipantDtoResponse response = participantService.addParticipant(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ParticipantDtoResponse> editParticipant(@RequestBody ParticipantDtoRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(participantService.editParticipant(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Integer id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok().build();
    }
}
