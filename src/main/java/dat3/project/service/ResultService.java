package dat3.project.service;

import dat3.project.dto.ResultDtoRequest;
import dat3.project.dto.ResultDtoResponse;
import dat3.project.entity.Participant;
import dat3.project.entity.Result;
import dat3.project.repository.ResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    /**
     * Gets all Results
     * @return A list of Results
     */

    public List<ResultDtoResponse> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return results.stream()
                .map(ResultDtoResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets a Result by id
     * @param id The id of the Result
     * @return The Result
     */

    public ResultDtoResponse getResultById(int id) {
        Result result = resultRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Result not found"));
        return new ResultDtoResponse(result);
    }



    /**
     * Adds a Result
     * @param request The request containing the Result data
     * @return The added Result
     */

    public ResultDtoResponse addResult(ResultDtoRequest request) {
        Result newResult = new Result();
        updateResult(newResult, request);
        resultRepository.save(newResult);
        return new ResultDtoResponse(newResult);
    }

    /**
     * Edits a Result
     * @param request The request containing the Result data
     * @param id The id of the Result
     * @return The edited Result
     */

    public ResultDtoResponse editResult(ResultDtoRequest request, int id) {
        Result result = resultRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Result not found"));
        updateResult(result, request);
        resultRepository.save(result);
        return new ResultDtoResponse(result);
    }

    /**
     * Deletes a Result
     * @param id The id of the Result
     * @return The deleted Result
     */

    public ResponseEntity<Void> deleteResult(int id) {
        Result result = resultRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Result not found"));
        resultRepository.delete(result);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates a Result
     * @param result The Result to update
     * @param request The request containing the Result data
     */

    private void updateResult(Result result, ResultDtoRequest request) {
        result.setResultValue(request.getResultValue());
        result.setDate(request.getDate());
        result.setResultType(request.getResultType());
        // If you have additional relationships, handle them here
    }
}
