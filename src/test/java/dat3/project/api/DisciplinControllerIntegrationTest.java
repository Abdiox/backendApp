package dat3.project.api;

import dat3.project.Enum.ResultType;
import dat3.project.dto.DisciplinDtoRequest;
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
        DisciplinDtoRequest request = new DisciplinDtoRequest();
        request.setName("Test Disciplin");
        request.setResultType(ResultType.valueOf("TIME"));

        mockMvc.perform(post("/disciplins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Disciplin\", \"resultType\": \"TIME\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Disciplin"))
                .andExpect(jsonPath("$.resultType").value("TIME"));
    }

    @Test
    void getAllDisciplins() throws Exception {
        DisciplinDtoRequest request1 = new DisciplinDtoRequest();
        request1.setName("Disciplin 1");
        request1.setResultType(ResultType.valueOf("TIME"));

        DisciplinDtoRequest request2 = new DisciplinDtoRequest();
        request2.setName("Disciplin 2");
        request2.setResultType(ResultType.valueOf("DISTANCE"));

        disciplinRepository.save(request1);
        disciplinRepository.save(request2);

        mockMvc.perform(get("/disciplins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Disciplin 1"))
                .andExpect(jsonPath("$[1].name").value("Disciplin 2"));
    }

    @Test
    void deleteDisciplin() throws Exception {
        DisciplinDtoRequest request = new DisciplinDtoRequest();
        request.setName("Test Disciplin");
        request.setResultType(ResultType.valueOf("TIME"));

        int id = disciplinRepository.save(request).getId();

        mockMvc.perform(delete("/disciplins/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/disciplins/{id}", id))
                .andExpect(status().isNotFound());
    }
}
