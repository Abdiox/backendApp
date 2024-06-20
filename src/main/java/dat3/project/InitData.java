package dat3.project;

import dat3.project.Enum.DisciplineType;
import dat3.project.Enum.ResultType;
import dat3.project.entity.Participant;
import dat3.project.entity.Disciplin;
import dat3.project.repository.ParticipantRepository;
import dat3.project.repository.DisciplinRepository;
import dat3.project.repository.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static dat3.project.Enum.DisciplineType.LØB_100_METER;

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

        Participant participant2 = new Participant("Mendy", "Mand", 28, "Real Madrid AtletikKlub");
        participantRepository.save(participant2);

        Participant participant3 = new Participant("Mbappe", "Mand", 25, "PSG AtletikKlub");
        participantRepository.save(participant3);

       /* Disciplin disciplin1 = new Disciplin("100-meterløb", LØB_100_METER, ResultType.TIME, participant1);
        disciplinRepository.save(disciplin1);

        */


        List<Disciplin> discipliner = Arrays.asList(
                new Disciplin("100-meterløb", DisciplineType.LØB_100_METER, ResultType.TIME, null),
                new Disciplin("200-meterløb", DisciplineType.LØB_200_METER, ResultType.TIME, null),
                new Disciplin("400-meterløb", DisciplineType.LØB_400_METER, ResultType.TIME, null),
                new Disciplin("800-meterløb", DisciplineType.LØB_800_METER, ResultType.TIME, null),
                new Disciplin("1500-meterløb", DisciplineType.LØB_1500_METER, ResultType.TIME, null),
                new Disciplin("Hækkeløb", DisciplineType.HÆKKELØB, ResultType.TIME, null),
                new Disciplin("Højdespring", DisciplineType.HØJDESPIRING, ResultType.DISTANCE, null),
                new Disciplin("Stangspring", DisciplineType.STANGSPRING, ResultType.DISTANCE, null),
                new Disciplin("Længdespring", DisciplineType.LÆNGDESPRING, ResultType.DISTANCE, null),
                new Disciplin("Kuglestød", DisciplineType.KUGLESTØD, ResultType.DISTANCE, null),
                new Disciplin("Diskoskast", DisciplineType.DISKOSKAST, ResultType.DISTANCE, null),
                new Disciplin("Spydkast", DisciplineType.SPYDKAST, ResultType.POINTS, null)
        );

        disciplinRepository.saveAll(discipliner);
    }

    }

