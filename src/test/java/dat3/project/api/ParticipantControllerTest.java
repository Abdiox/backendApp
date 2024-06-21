package dat3.project.api;

import dat3.project.dto.ParticipantDtoRequest;
import dat3.project.dto.ParticipantDtoResponse;
import dat3.project.service.ParticipantService;
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

class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllParticipants() {
        // Arrange
        ParticipantDtoResponse participant = new ParticipantDtoResponse();
        when(participantService.getAllParticipants()).thenReturn(Collections.singletonList(participant));

        // Act
        List<ParticipantDtoResponse> response = participantController.getAllParticipants();

        // Assert
        assertNotNull(response);
        verify(participantService, times(1)).getAllParticipants();
    }

    @Test
    void getParticipantById() {
        // Arrange
        int id = 1;
        ParticipantDtoResponse participant = new ParticipantDtoResponse();
        when(participantService.getParticipantById(id)).thenReturn(participant);

        // Act
        ParticipantDtoResponse response = participantController.getParticipantById(id);

        // Assert
        assertNotNull(response);
        verify(participantService, times(1)).getParticipantById(id);
    }

    @Test
    void addParticipant() {
        // Arrange
        ParticipantDtoRequest request = new ParticipantDtoRequest();
        ParticipantDtoResponse responseDto = new ParticipantDtoResponse();
        when(participantService.addParticipant(request)).thenReturn(responseDto);

        // Act
        ParticipantDtoResponse response = participantController.addParticipant(request);

        // Assert
        assertNotNull(response);
        verify(participantService, times(1)).addParticipant(request);
    }

    @Test
    void editParticipant() {
        // Arrange
        int id = 1;
        ParticipantDtoRequest request = new ParticipantDtoRequest();
        ParticipantDtoResponse responseDto = new ParticipantDtoResponse();
        when(participantService.editParticipant(request, id)).thenReturn(responseDto);

        // Act
        ResponseEntity<ParticipantDtoResponse> response = participantController.editParticipant(request, id);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(participantService, times(1)).editParticipant(request, id);
    }

    @Test
    void deleteParticipant() {
        // Arrange
        int id = 1;
        doNothing().when(participantService).deleteParticipant(id);

        // Act
        ResponseEntity<Void> response = participantController.deleteParticipant(id);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(participantService, times(1)).deleteParticipant(id);
    }
}
