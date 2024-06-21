package dat3.project.api;

import dat3.project.dto.ResultDtoRequest;
import dat3.project.dto.ResultDtoResponse;
import dat3.project.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResultControllerTest {

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllResults() {
        // Arrange
        ResultDtoResponse result = new ResultDtoResponse();
        when(resultService.getAllResults()).thenReturn(Collections.singletonList(result));

        // Act
        List<ResultDtoResponse> response = resultController.getAllResults();

        // Assert
        assertNotNull(response);

        verify(resultService, times(1)).getAllResults();
    }

    @Test
    void getResultById() {
        // Arrange
        int id = 1;
        ResultDtoResponse result = new ResultDtoResponse();
        when(resultService.getResultById(id)).thenReturn(result);

        // Act
        ResultDtoResponse response = resultController.getResultById(id);

        // Assert
        assertNotNull(response);
        verify(resultService, times(1)).getResultById(id);
    }

    @Test
    void addResult() {
        // Arrange
        ResultDtoRequest request = new ResultDtoRequest();
        ResultDtoResponse responseDto = new ResultDtoResponse();
        when(resultService.addResult(request)).thenReturn(responseDto);

        // Act
        ResultDtoResponse response = resultController.addResult(request);

        // Assert
        assertNotNull(response);
        verify(resultService, times(1)).addResult(request);
    }

    @Test
    void editResult() {
        // Arrange
        int id = 1;
        ResultDtoRequest request = new ResultDtoRequest();
        ResultDtoResponse responseDto = new ResultDtoResponse();
        when(resultService.editResult(request, id)).thenReturn(responseDto);

        // Act
        ResultDtoResponse response = resultController.editResult(request, id);

        // Assert
        assertNotNull(response);
        verify(resultService, times(1)).editResult(request, id);
    }

    @Test
    void deleteResult() {
        // Arrange
        int id = 1;
        doNothing().when(resultService).deleteResult(id);

        // Act
        ResponseEntity<Void> response = resultController.deleteResult(id);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(resultService, times(1)).deleteResult(id);
    }
}
