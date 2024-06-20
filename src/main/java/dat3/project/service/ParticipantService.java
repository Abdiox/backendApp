package dat3.project.service;

import dat3.project.dto.ParticipantDtoRequest;
import dat3.project.dto.ParticipantDtoResponse;
import dat3.project.entity.Disciplin;
import dat3.project.entity.Participant;
import dat3.project.repository.DisciplinRepository;
import dat3.project.repository.ParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final DisciplinRepository disciplinRepository;

    public ParticipantService(ParticipantRepository participantRepository, DisciplinRepository disciplinRepository) {
        this.participantRepository = participantRepository;
        this.disciplinRepository = disciplinRepository;
    }


    /**
     * Gets all participants
     * @return A list of participants
     */

    public List<ParticipantDtoResponse> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream().map(ParticipantDtoResponse::new).collect(Collectors.toList());
    }

    /**
     * Gets a participant by id
     * @param id The id of the participant
     * @return The participant
     */

    public ParticipantDtoResponse getParticipantById(int id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        return new ParticipantDtoResponse(participant);
    }

    /**
     * Adds a participant
     * @param request The request containing the participant data
     * @return The added participant
     */

    public ParticipantDtoResponse addParticipant(ParticipantDtoRequest request) {
        Participant newParticipant = new Participant(request.getName(), request.getGender(), request.getAge(), request.getClub());
        Set<Disciplin> disciplins = request.getDisciplineIds().stream()
                .map(discId -> disciplinRepository.findById(discId)
                        .orElseThrow(() -> new RuntimeException("Discipline not found")))
                .collect(Collectors.toSet());
        newParticipant.setDisciplins(disciplins);
        participantRepository.save(newParticipant);
        return new ParticipantDtoResponse(newParticipant);
    }

    /**
     * Edits a participant
     * @param request The request containing the participant data
     * @param id The id of the participant
     * @return The edited participant
     */

    public ParticipantDtoResponse editParticipant(ParticipantDtoRequest request, int id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        participant.setName(request.getName());
        participant.setGender(request.getGender());
        participant.setAge(request.getAge());
        participant.setClub(request.getClub());

        Set<Disciplin> updatedDisciplins = request.getDisciplineIds().stream()
                .map(discId -> disciplinRepository.findById(discId)
                        .orElseThrow(() -> new RuntimeException("Discipline not found")))
                .collect(Collectors.toSet());

        participant.setDisciplins(updatedDisciplins);
        participantRepository.save(participant);
        return new ParticipantDtoResponse(participant);
    }

    /**
     * Deletes a participant
     * @param id The id of the participant
     * @return A response entity
     */

    public ResponseEntity<Void> deleteParticipant(int id) {
        participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        participantRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
