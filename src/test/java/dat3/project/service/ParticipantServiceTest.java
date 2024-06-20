package dat3.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat3.project.api.ParticipantController;
import dat3.project.dto.ParticipantDtoRequest;
import dat3.project.dto.ParticipantDtoResponse;
import dat3.project.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ParticipantController.class)
public class ParticipantServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }
    // Test to get all participants
    @Test
    void getAllParticipants() throws Exception {
        ParticipantDtoResponse participant = new ParticipantDtoResponse(1, "John Doe", "Mand", 25, "ClubName");
        List<ParticipantDtoResponse> allParticipants = List.of(participant);

        when(participantService.getAllParticipants()).thenReturn(allParticipants);

        mockMvc.perform(MockMvcRequestBuilders.get("/participants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("John Doe")));
    }

    // Test to get a single participant by ID
    @Test
    void getParticipantById() throws Exception {
        ParticipantDtoResponse participant = new ParticipantDtoResponse(1, "John Doe", "Mand", 25, "ClubName");

        when(participantService.getParticipantById(1)).thenReturn(participant);

        mockMvc.perform(MockMvcRequestBuilders.get("/participants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    // Test to add a new participant
    @Test
    void addParticipant() throws Exception {
        ParticipantDtoRequest newParticipant = new ParticipantDtoRequest();
        newParticipant.setName("John Doe");
        newParticipant.setGender("Mand");
        newParticipant.setAge(25);
        newParticipant.setClub("ClubName");

        ParticipantDtoResponse returnedParticipant = new ParticipantDtoResponse(1, "John Doe", "Mand", 25, "ClubName");

        when(participantService.addParticipant(any(ParticipantDtoRequest.class))).thenReturn(returnedParticipant);

        mockMvc.perform(MockMvcRequestBuilders.post("/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    // Test to update a participant
    @Test
    void editParticipant() throws Exception {
        ParticipantDtoRequest updateInfo = new ParticipantDtoRequest();
        updateInfo.setName("Updated Name");

        ParticipantDtoResponse updatedParticipant = new ParticipantDtoResponse(1, "Updated Name", "Mand", 25, "ClubName");

        when(participantService.editParticipant(any(ParticipantDtoRequest.class), eq(1))).thenReturn(updatedParticipant);

        mockMvc.perform(MockMvcRequestBuilders.put("/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Name")));
    }

    // Test to delete a participant
    @Test
    void deleteParticipant() throws Exception {
        doNothing().when(participantService).deleteParticipant(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/participants/1"))
                .andExpect(status().isOk());
    }
}
