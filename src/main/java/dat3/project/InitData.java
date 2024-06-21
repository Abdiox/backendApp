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
        Participant participant4 = new Participant("Yamal", "Mand", 9, "La Masia AtletikKlub");
        Participant participant5 = new Participant("Musiala", "Mand", 15, "Bayern AtletikKlub");
        Participant participant6 = new Participant("Hakan", "Mand", 19, "Inter AtletikKlub");
        Participant participant7 = new Participant("Schmeicel", "Mand", 64, "Retired AtletikKlub");




        participantRepository.saveAll(Arrays.asList(participant1, participant2, participant3, participant4, participant5, participant6, participant7));

        // Disciplines
        Disciplin disciplin1 = new Disciplin("100-meterløb", DisciplineType.LØB_100_METER, ResultType.TIME);
        Disciplin disciplin2 = new Disciplin("200-meterløb", DisciplineType.LØB_200_METER, ResultType.TIME);
        Disciplin disciplin3 = new Disciplin("400-meterløb", DisciplineType.LØB_400_METER, ResultType.TIME);
        Disciplin displin4 = new Disciplin("800-meterløb", DisciplineType.LØB_800_METER, ResultType.TIME);
        Disciplin displin5 = new Disciplin("1500-meterløb", DisciplineType.LØB_1500_METER, ResultType.TIME);
        Disciplin displin6 = new Disciplin("Hækkeløb", DisciplineType.HÆKKELØB, ResultType.TIME);
        Disciplin displin7 = new Disciplin("Højdespring", DisciplineType.HØJDESPIRING, ResultType.DISTANCE);
        Disciplin displin8 = new Disciplin("Stangspring", DisciplineType.STANGSPRING, ResultType.DISTANCE);
        Disciplin displin9 = new Disciplin("Længdespring", DisciplineType.LÆNGDESPRING, ResultType.DISTANCE);
        Disciplin displin10 = new Disciplin("Kuglestød", DisciplineType.KUGLESTØD, ResultType.DISTANCE);
        Disciplin displin11 = new Disciplin("Diskoskast", DisciplineType.DISKOSKAST, ResultType.DISTANCE);
        Disciplin displin12 = new Disciplin("Spydkast", DisciplineType.SPYDKAST, ResultType.DISTANCE);
        Disciplin disciplin13 = new Disciplin("Hepthlon", DisciplineType.HEPTATHLON, ResultType.POINTS);


        disciplinRepository.saveAll(Arrays.asList(disciplin1, disciplin2, disciplin3, displin4, displin5, displin6, displin7, displin8, displin9, displin10, displin11, displin12, disciplin13));

        // Link participants and disciplines
        participant1.setDisciplins(new HashSet<>(Arrays.asList(disciplin1)));
        participant2.setDisciplins(new HashSet<>(Arrays.asList(disciplin2)));
        participant3.setDisciplins(new HashSet<>(Arrays.asList(disciplin3)));
        participant4.setDisciplins(new HashSet<>(Arrays.asList(displin9)));
        participant5.setDisciplins(new HashSet<>(Arrays.asList(displin10)));
        participant6.setDisciplins(new HashSet<>(Arrays.asList(displin12)));
        participant7.setDisciplins(new HashSet<>(Arrays.asList(disciplin13)));


        participantRepository.saveAll(Arrays.asList(participant1, participant2, participant3, participant4, participant5, participant6));

        // Results
        Result result1 = new Result(ResultType.TIME, LocalDateTime.now(), 19500, disciplin1, participant1);
        Result result2 = new Result(ResultType.TIME, LocalDateTime.now(), 25000, disciplin2, participant2);
        Result result3 = new Result(ResultType.TIME, LocalDateTime.now(), 30000, disciplin3, participant3);
        Result result4 = new Result(ResultType.DISTANCE, LocalDateTime.now(), 500, displin9, participant4);
        Result result5 = new Result(ResultType.DISTANCE, LocalDateTime.now(), 1000, displin10, participant5);
        Result result6 = new Result(ResultType.DISTANCE, LocalDateTime.now(), 2000, displin12, participant6);
        Result result7 = new Result(ResultType.POINTS, LocalDateTime.now(), 5000, disciplin13, participant6);


        resultRepository.saveAll(Arrays.asList(result1, result2, result3, result4, result5, result6, result7));
    }
}


