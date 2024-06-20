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
     * Gets all Participants
     *
     * @return A list of Participants
     */
    public List<ParticipantDtoResponse> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream()
                .map(ParticipantDtoResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets an Participant by id
     *
     * @param id The id of the Participant
     * @return The Participant
     */
    public ParticipantDtoResponse getParticipantById(int id) {
        Participant participants = participantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Participant not found"));
        return new ParticipantDtoResponse(participants);
    }

    /**
     * Gets an Participant by name
     * @param name The name of the Participant
     * @return The Participant
     */
    public Participant getParticipantByName(String name) {
        return participantRepository.findByName(name).orElseThrow(() ->
                new RuntimeException("Participant not found"));
    }

    /**
     * Adds an participant
     *
     * @param request The request containing the participant data
     * @return The added participant
     */
    public ParticipantDtoResponse addParticipant(ParticipantDtoRequest request) {
        Participant newParticipants = new Participant();
        updateParticipants(newParticipants, request);
        participantRepository.save(newParticipants);

        // Handle pets if included in the request
        if (request.getDisciplin() != null) {
            request.getDisciplin().forEach(disciplinRequest -> {
                Disciplin disciplin = new Disciplin();
                disciplin.setParticipant(newParticipants);
                disciplinRepository.save(disciplin);
            });
        }

        return new ParticipantDtoResponse(newParticipants);
    }

    /**
     * Edits an participant
     *
     * @param request The request containing the data
     * @param id      The id of the participant
     * @return The edited participant
     */
    public ParticipantDtoResponse editParticipant(ParticipantDtoRequest request, int id) {
        Participant participants = participantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Participant not found"));
        updateParticipants(participants, request);
        participantRepository.save(participants);
        return new ParticipantDtoResponse(participants);
    }

    /**
     * Deletes an participant
     * @param id The id of the participant
     * @return A response entity
     */
    public ResponseEntity<Void> deleteParticipant(int id) {
        Participant participants = participantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Participant not found"));
        participantRepository.delete(participants);
        return ResponseEntity.ok().build();
    }

    /**
     *
     * @param participants The participant to update
     * @param request The request containing the data
     */

    private void updateParticipants(Participant participants, ParticipantDtoRequest request) {
        participants.setName(request.getName());
        participants.setAge(request.getAge());
    }
}
