package dat3.project.service;

import dat3.project.Enum.ResultType;
import dat3.project.dto.DisciplinDtoRequest;
import dat3.project.dto.DisciplinDtoResponse;
import dat3.project.entity.Disciplin;
import dat3.project.repository.DisciplinRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinService {

    private final DisciplinRepository disciplinRepository;

    public DisciplinService(DisciplinRepository disciplinRepository) {
        this.disciplinRepository = disciplinRepository;
    }

    /**
     * Gets all disciplins
     * @return A list of disciplins
     */
    public List<DisciplinDtoResponse> getAllDisciplins() {
        List<Disciplin> disciplins = disciplinRepository.findAll();
        return disciplins.stream()
                .map(DisciplinDtoResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets a disciplin by id
     * @param id The id of the disciplin
     * @return The disciplin
     */
    public DisciplinDtoResponse getDisciplinById(int id) {
        Disciplin disciplins = disciplinRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Disciplin not found"));
        return new DisciplinDtoResponse(disciplins);
    }

    /**
     * Adds a disciplin
     * @param request The request containing the disciplin data
     * @return The added disciplin
     */
    public DisciplinDtoResponse addDisciplin(DisciplinDtoRequest request) {
        Disciplin newDisciplin = new Disciplin();
        updateDisciplin(newDisciplin, request);
        disciplinRepository.save(newDisciplin);
        return new DisciplinDtoResponse(newDisciplin);
    }

    /**
     * Edits a disciplin
     * @param request The request containing the disciplin data
     * @param id The id of the disciplin
     * @return The edited disciplin
     */
    public DisciplinDtoResponse editDisciplin(DisciplinDtoRequest request, int id) {
        Disciplin disciplins = disciplinRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Disciplin not found"));
        updateDisciplin(disciplins, request);
        disciplinRepository.save(disciplins);
        return new DisciplinDtoResponse(disciplins);
    }

    /**
     * Deletes a disciplin
     * @param id The id of the disciplin
     * @return A response entity
     */
    public ResponseEntity<Void> deleteDisciplin(int id) {
        Disciplin disciplins = disciplinRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Disciplin not found"));
        disciplinRepository.delete(disciplins);
        return ResponseEntity.ok().build();
    }

    /**
     *
     * @param disciplin
     * @param request
     */

    private void updateDisciplin(Disciplin disciplin, DisciplinDtoRequest request) {
        disciplin.setName(request.getName());
        disciplin.setResultType((ResultType) request.getResultType());
        // Add logic here if you want to set the disciplin
        // Disciplin.setOwner(request.getOwner());
    }
}
