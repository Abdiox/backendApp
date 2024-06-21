package dat3.project.api;

import dat3.project.Enum.DisciplineType;
import dat3.project.Enum.ResultType;
import dat3.project.dto.DisciplinDtoRequest;
import dat3.project.dto.DisciplinDtoResponse;
import dat3.project.entity.Disciplin;
import dat3.project.repository.DisciplinRepository;
import dat3.project.service.DisciplinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
class DisciplinControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        disciplinRepository.deleteAll();
    }

    @Test
    void getAllDisciplins() throws Exception {
        Disciplin disciplin1 = new Disciplin("Disciplin 1", DisciplineType.HØJDESPIRING, ResultType.TIME);
        Disciplin disciplin2 = new Disciplin("Disciplin 2", DisciplineType.HØJDESPIRING, ResultType.DISTANCE);

        disciplinRepository.save(disciplin1);
        disciplinRepository.save(disciplin2);

        mockMvc.perform(get("/disciplins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Disciplin 1"))
                .andExpect(jsonPath("$[1].name").value("Disciplin 2"));
    }

    @Test
    void getDisciplinById() throws Exception {
        Disciplin disciplin = new Disciplin("Disciplin 1", null, ResultType.TIME);
        int id = disciplinRepository.save(disciplin).getId();

        mockMvc.perform(get("/disciplins/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Disciplin 1"))
                .andExpect(jsonPath("$.resultType").value("TIME"));
    }

    @Test
    void addDisciplin() throws Exception {
        mockMvc.perform(post("/disciplins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Disciplin 1\", \"resultType\": \"TIME\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Disciplin 1"))
                .andExpect(jsonPath("$.resultType").value("TIME"));
    }

    @Test
    void editDisciplin() throws Exception {
        Disciplin disciplin = new Disciplin("Disciplin 1", null, ResultType.TIME);
        int id = disciplinRepository.save(disciplin).getId();

        mockMvc.perform(put("/disciplins/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Disciplin\", \"resultType\": \"DISTANCE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Disciplin"))
                .andExpect(jsonPath("$.resultType").value("DISTANCE"));
    }

    @Test
    void deleteDisciplin() throws Exception {
        Disciplin disciplin = new Disciplin("Disciplin 1", null, ResultType.TIME);
        int id = disciplinRepository.save(disciplin).getId();

        mockMvc.perform(delete("/disciplins/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/disciplins/{id}", id))
                .andExpect(status().isNotFound());
    }
}
