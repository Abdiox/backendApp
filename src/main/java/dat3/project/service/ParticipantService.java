package dat3.project.service;

import dat3.project.dto.ParticipantDtoRequest;
import dat3.project.dto.ParticipantDtoResponse;
import dat3.project.entity.Participant;
import dat3.project.repository.ParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    /**
     * Gets all participants
     * @return A list of participants
     */

    public List<ParticipantDtoResponse> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream()
                .map(ParticipantDtoResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets a participant by id
     * @param id The id of the participant
     * @return The participant
     */

    public ParticipantDtoResponse getParticipantById(int id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Participant not found"));
        return new ParticipantDtoResponse(participant);
    }

    /**
     * Adds a participant
     * @param request The request containing the participant data
     * @return The added participant
     */

    public ParticipantDtoResponse addParticipant(ParticipantDtoRequest request) {
        Participant newParticipant = new Participant(request.getName(), request.getGender(), request.getAge(), request.getClub());
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
        Participant participant = participantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Participant not found"));
        participant.setName(request.getName());
        participant.setGender(request.getGender());
        participant.setAge(request.getAge());
        participant.setClub(request.getClub());
        participantRepository.save(participant);
        return new ParticipantDtoResponse(participant);
    }

    /**
     * Deletes a participant
     * @param id The id of the participant
     * @return A response entity
     */

    public ResponseEntity<Void> deleteParticipant(int id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Participant not found"));
        participantRepository.delete(participant);
        return ResponseEntity.ok().build();
    }
}
