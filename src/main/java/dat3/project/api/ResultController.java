package dat3.project.api;

import dat3.project.dto.ResultDtoRequest;
import dat3.project.dto.ResultDtoResponse;
import dat3.project.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<ResultDtoResponse> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public ResultDtoResponse getResultById(@PathVariable int id) {
        return resultService.getResultById(id);
    }

    @PostMapping
    public ResultDtoResponse addResult(@RequestBody ResultDtoRequest request) {
        return resultService.addResult(request);
    }

    @PutMapping("/{id}")
    public ResultDtoResponse editResult(@RequestBody ResultDtoRequest request, @PathVariable int id) {
        return resultService.editResult(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return ResponseEntity.ok().build();
    }
}
