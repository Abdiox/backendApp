package dat3.project.api;

import dat3.project.entity.Participant;
import dat3.project.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ParticipantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
        participantRepository.deleteAll();
    }

    @Test
    void getAllParticipants() throws Exception {
        Participant participant1 = new Participant("Abdiox", "Mand", 22, "FCK");
        Participant participant2 = new Participant("Kim K", "Kvinde", 33, "Brøndby");

        participantRepository.save(participant1);
        participantRepository.save(participant2);

        mockMvc.perform(get("/participants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Abdiox"))
                .andExpect(jsonPath("$[1].name").value("Kim K"));
    }

    @Test
    void getParticipantById() throws Exception {
        Participant participant = new Participant("Abdiox", "Mand",  22, "FCK");
        int id = participantRepository.save(participant).getId();

        mockMvc.perform(get("/participants/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Abdiox"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.club").value("FCK"))
                .andExpect(jsonPath("$.gender").value("Mand"));
    }


}
