package dat3.project;

import dat3.project.Enum.DisciplineType;
import dat3.project.Enum.ResultType;
import dat3.project.entity.Participant;
import dat3.project.entity.Disciplin;
import dat3.project.entity.Result;
import dat3.project.repository.ParticipantRepository;
import dat3.project.repository.DisciplinRepository;
import dat3.project.repository.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final ParticipantRepository participantRepository;
    private final DisciplinRepository disciplinRepository;
    private final ResultRepository resultRepository;

    public InitData(ParticipantRepository participantRepository, DisciplinRepository disciplinRepository, ResultRepository resultRepository) {
        this.participantRepository = participantRepository;
        this.disciplinRepository = disciplinRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void run(String... args) {
        // Participants
        Participant participant1 = new Participant("abdiox", "Mand", 22, "Rødovre AtletikKlub");
        Participant participant2 = new Participant("Mendy", "Mand", 28, "Real Madrid AtletikKlub");
        Participant participant3 = new Participant("Mbappe", "Mand", 25, "PSG AtletikKlub");

        participantRepository.saveAll(Arrays.asList(participant1, participant2, participant3));

        // Disciplines
        Disciplin disciplin1 = new Disciplin("100-meterløb", DisciplineType.LØB_100_METER, ResultType.TIME);
        Disciplin disciplin2 = new Disciplin("200-meterløb", DisciplineType.LØB_200_METER, ResultType.TIME);
        Disciplin disciplin3 = new Disciplin("400-meterløb", DisciplineType.LØB_400_METER, ResultType.TIME);

        disciplinRepository.saveAll(Arrays.asList(disciplin1, disciplin2, disciplin3));

        // Link participants and disciplines
        participant1.setDisciplins(new HashSet<>(Arrays.asList(disciplin1)));
        participant2.setDisciplins(new HashSet<>(Arrays.asList(disciplin2)));
        participant3.setDisciplins(new HashSet<>(Arrays.asList(disciplin3)));

        participantRepository.saveAll(Arrays.asList(participant1, participant2, participant3));

        // Results
        Result result1 = new Result(9500, LocalDateTime.now(), ResultType.TIME, disciplin1, participant1); // 9.5 seconds for 100m
        Result result2 = new Result(19500, LocalDateTime.now(), ResultType.TIME, disciplin2, participant2); // 19.5 seconds for 200m
        Result result3 = new Result(45000, LocalDateTime.now(), ResultType.TIME, disciplin3, participant3); // 45 seconds for 400m

        resultRepository.saveAll(Arrays.asList(result1, result2, result3));
    }
}
