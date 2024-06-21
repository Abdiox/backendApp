package dat3.project.api;

import dat3.project.Enum.ResultType;
import dat3.project.dto.DisciplinDtoRequest;
import dat3.project.entity.Disciplin;
import dat3.project.repository.DisciplinRepository;
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
public class DisciplinControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @BeforeEach
    void setUp() {
        disciplinRepository.deleteAll();
    }

    @Test
    void addDisciplin() throws Exception {
        mockMvc.perform(post("/disciplins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Disciplin\", \"resultType\": \"TIME\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Disciplin"))
                .andExpect(jsonPath("$.resultType").value("TIME"));
    }

    @Test
    void getAllDisciplins() throws Exception {
        Disciplin disciplin1 = new Disciplin("Disciplin 1", null, ResultType.TIME);
        Disciplin disciplin2 = new Disciplin("Disciplin 2", null, ResultType.DISTANCE);

        disciplinRepository.save(disciplin1);
        disciplinRepository.save(disciplin2);

        mockMvc.perform(get("/disciplins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Disciplin 1"))
                .andExpect(jsonPath("$[1].name").value("Disciplin 2"));
    }

    @Test
    void deleteDisciplin() throws Exception {
        Disciplin disciplin = new Disciplin("Test Disciplin", null, ResultType.TIME);
        int id = disciplinRepository.save(disciplin).getId();

        mockMvc.perform(delete("/disciplins/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/disciplins/{id}", id))
                .andExpect(status().isNotFound());
    }
}
