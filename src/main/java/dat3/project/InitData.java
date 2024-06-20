package dat3.project;

import dat3.project.entity.Participant;
import dat3.project.entity.Disciplin;
import dat3.project.entity.Result;
import dat3.project.Enum.ResultType;
import dat3.project.repository.ParticipantRepository;
import dat3.project.repository.DisciplinRepository;
import dat3.project.repository.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

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
        // Create and save participants
        Participant participant1 = new Participant("abdiox", "Mand", 22, "Rødovre AtletikKlub");
        participantRepository.save(participant1);

        Participant participant2 = new Participant("Mendy", "Mand", 28, "Rødovre AtletikKlub");
        participantRepository.save(participant2);

        // Create and save disciplines
        Disciplin disciplin1 = new Disciplin("200-meterløb", ResultType.TIME, participant1);
        disciplinRepository.save(disciplin1);

        Disciplin disciplin2 = new Disciplin("200-meterløb", ResultType.TIME, participant2);
        disciplinRepository.save(disciplin2);

        Disciplin disciplin3 = new Disciplin("400-meterløb", ResultType.TIME, participant2);
        disciplinRepository.save(disciplin3);

        // Create and save results
        Result result1 = new Result(10234, LocalDateTime.now(), ResultType.TIME, disciplin1); // Time in milliseconds
        resultRepository.save(result1);

        Result result2 = new Result(780, LocalDateTime.now(), ResultType.DISTANCE, disciplin1); // Distance in centimeters
        resultRepository.save(result2);

        Result result3 = new Result(95, LocalDateTime.now(), ResultType.POINTS, disciplin2); // Points
        resultRepository.save(result3);

        Result result4 = new Result(12045, LocalDateTime.now(), ResultType.TIME, disciplin3); // Time in milliseconds
        resultRepository.save(result4);

        // Ensure participants have the disciplines assigned
        participant1.setDisciplins(Set.of(disciplin1, disciplin2));
        participant2.setDisciplins(Set.of(disciplin3));

        participantRepository.save(participant1);
        participantRepository.save(participant2);
    }
}
