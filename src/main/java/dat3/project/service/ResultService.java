package dat3.project.service;

import dat3.project.dto.ResultDtoRequest;
import dat3.project.dto.ResultDtoResponse;
import dat3.project.entity.Disciplin;
import dat3.project.entity.Participant;
import dat3.project.entity.Result;
import dat3.project.repository.DisciplinRepository;
import dat3.project.repository.ParticipantRepository;
import dat3.project.repository.ResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplinRepository disciplinRepository;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DisciplinRepository disciplinRepository) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.disciplinRepository = disciplinRepository;
    }

    /**
     * Gets all results
     * @return A list of results
     */

    public List<ResultDtoResponse> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return results.stream().map(ResultDtoResponse::new).collect(Collectors.toList());
    }

    /**
     * Gets a result by id
     * @param id The id of the result
     * @return The result
     */

    public ResultDtoResponse getResultById(int id) {
        Result result = resultRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Result not found"));
        return new ResultDtoResponse(result);
    }

    /**
     * Adds a result
     * @param request The request containing the result data
     * @return The added result
     */

    public ResultDtoResponse addResult(ResultDtoRequest request) {
        Result newResult = new Result();
        updateResult(newResult, request);
        resultRepository.save(newResult);
        return new ResultDtoResponse(newResult);
    }

    /**
     * Edits a result
     * @param request The request containing the result data
     * @param id The id of the result
     * @return The edited result
     */


    public ResultDtoResponse editResult(ResultDtoRequest request, int id) {
        Result result = resultRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Result not found"));
        updateResult(result, request);
        resultRepository.save(result);
        return new ResultDtoResponse(result);
    }

    /**
     * Deletes a result
     * @param id The id of the result
     * @return A response entity
     */

    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }

    private void updateResult(Result result, ResultDtoRequest request) {
        Participant participant = participantRepository.findById(request.getParticipantId())
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        Disciplin disciplin = disciplinRepository.findById(request.getDisciplinId())
                .orElseThrow(() -> new RuntimeException("Disciplin not found"));

        result.setParticipant(participant);
        result.setDisciplin(disciplin);
        result.setResultType(request.getResultType());
        result.setResultValue(request.getResultValue());
        result.setDate(request.getDate());
    }
}
