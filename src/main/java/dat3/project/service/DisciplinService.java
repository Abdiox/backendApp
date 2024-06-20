package dat3.project.service;

import dat3.project.Enum.DisciplineType;
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

    public List<DisciplinDtoResponse> getAllDisciplins() {
        List<Disciplin> disciplins = disciplinRepository.findAll();
        return disciplins.stream()
                .map(DisciplinDtoResponse::new)
                .collect(Collectors.toList());
    }

    public DisciplinDtoResponse getDisciplinById(int id) {
        Disciplin disciplin = findDisciplinById(id);
        return new DisciplinDtoResponse(disciplin);
    }

    public DisciplinDtoResponse addDisciplin(DisciplinDtoRequest request) {
        Disciplin newDisciplin = new Disciplin();
        newDisciplin.setName(request.getName());
        newDisciplin.setResultType(request.getResultType());
        newDisciplin.setDisciplineType(request.getDisciplineType()); // Ensure you set the DisciplineType

        disciplinRepository.save(newDisciplin);
        return new DisciplinDtoResponse(newDisciplin);
    }

    public DisciplinDtoResponse editDisciplin(DisciplinDtoRequest request, int id) {
        Disciplin disciplin = findDisciplinById(id);
        updateDisciplin(disciplin, request);
        disciplinRepository.save(disciplin);
        return new DisciplinDtoResponse(disciplin);
    }

    public ResponseEntity<Void> deleteDisciplin(int id) {
        Disciplin disciplin = findDisciplinById(id);
        disciplinRepository.delete(disciplin);
        return ResponseEntity.ok().build();
    }

    private Disciplin findDisciplinById(int id) {
        return disciplinRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Disciplin not found"));
    }

    private void updateDisciplin(Disciplin disciplin, DisciplinDtoRequest request) {
        disciplin.setName(request.getName());
        disciplin.setResultType(request.getResultType());
        disciplin.setDisciplineType(request.getDisciplineType()); // Ensure you update the DisciplineType
    }
}
