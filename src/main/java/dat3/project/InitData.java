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
        participantRepository.save(participant1);

        Participant participant2 = new Participant("Mendy", "Mand", 28, "Real Madrid AtletikKlub");
        participantRepository.save(participant2);

        Participant participant3 = new Participant("Mbappe", "Mand", 25, "PSG AtletikKlub");
        participantRepository.save(participant3);

        // Disciplines
        List<Disciplin> discipliner = Arrays.asList(
                new Disciplin("100-meterløb", DisciplineType.LØB_100_METER, ResultType.TIME, participant1),
                new Disciplin("200-meterløb", DisciplineType.LØB_200_METER, ResultType.TIME, participant2),
                new Disciplin("400-meterløb", DisciplineType.LØB_400_METER, ResultType.TIME, participant3)
                // More disciplines...
        );
        disciplinRepository.saveAll(discipliner);

        // Results
        List<Result> results = Arrays.asList(
                new Result(9500, LocalDateTime.now(), ResultType.TIME, discipliner.get(0), participant1), // 9.5 seconds for 100m
                new Result(19500, LocalDateTime.now(), ResultType.TIME, discipliner.get(1), participant2), // 19.5 seconds for 200m
                new Result(45000, LocalDateTime.now(), ResultType.TIME, discipliner.get(2), participant3) // 45 seconds for 400m
                // More results...
        );
        resultRepository.saveAll(results);
    }
}
