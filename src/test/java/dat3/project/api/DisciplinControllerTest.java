package dat3.project.api;

import dat3.project.dto.DisciplinDtoRequest;
import dat3.project.dto.DisciplinDtoResponse;
import dat3.project.service.DisciplinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisciplinControllerTest {

    @Mock
    private DisciplinService disciplinService;

    @InjectMocks
    private DisciplinController disciplinController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDisciplins() {
        // Arrange
        DisciplinDtoResponse disciplin1 = new DisciplinDtoResponse();
        disciplin1.setId(1);
        disciplin1.setName("100-meterløb");

        DisciplinDtoResponse disciplin2 = new DisciplinDtoResponse();
        disciplin2.setId(2);
        disciplin2.setName("200-meterløb");

        List<DisciplinDtoResponse> expectedDisciplins = Arrays.asList(disciplin1, disciplin2);
        when(disciplinService.getAllDisciplins()).thenReturn(expectedDisciplins);

        // Act
        ResponseEntity<List<DisciplinDtoResponse>> response = disciplinController.getAllDisciplins();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedDisciplins, response.getBody());
        verify(disciplinService, times(1)).getAllDisciplins();
    }

    @Test
    void getDisciplinById() {
        // Arrange
        int id = 1;
        DisciplinDtoResponse expectedDisciplin = new DisciplinDtoResponse();
        expectedDisciplin.setId(id);
        expectedDisciplin.setName("100-meterløb");
        when(disciplinService.getDisciplinById(id)).thenReturn(expectedDisciplin);

        // Act
        ResponseEntity<DisciplinDtoResponse> response = disciplinController.getDisciplinById(id);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedDisciplin, response.getBody());
        verify(disciplinService, times(1)).getDisciplinById(id);
    }

    @Test
    void addDisciplin() {
        // Arrange
        DisciplinDtoRequest request = new DisciplinDtoRequest();
        request.setName("100-meterløb");

        DisciplinDtoResponse expectedDisciplin = new DisciplinDtoResponse();
        expectedDisciplin.setId(1);
        expectedDisciplin.setName("100-meterløb");
        when(disciplinService.addDisciplin(request)).thenReturn(expectedDisciplin);

        // Act
        ResponseEntity<DisciplinDtoResponse> response = disciplinController.addDisciplin(request);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(expectedDisciplin, response.getBody());
        verify(disciplinService, times(1)).addDisciplin(request);
    }

    @Test
    void editDisciplin() {
        // Arrange
        int id = 1;
        DisciplinDtoRequest request = new DisciplinDtoRequest();
        request.setName("100-meterløb");

        DisciplinDtoResponse expectedDisciplin = new DisciplinDtoResponse();
        expectedDisciplin.setId(id);
        expectedDisciplin.setName("100-meterløb");
        when(disciplinService.editDisciplin(request, id)).thenReturn(expectedDisciplin);

        // Act
        ResponseEntity<DisciplinDtoResponse> response = disciplinController.editDisciplin(request, id);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedDisciplin, response.getBody());
        verify(disciplinService, times(1)).editDisciplin(request, id);
    }



    @Test
    void deleteDisciplin() {
        // Arrange
        int id = 1;
        when(disciplinService.deleteDisciplin(id)).thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<Void> response = disciplinController.deleteDisciplin(id);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(disciplinService, times(1)).deleteDisciplin(id);
    }


}
