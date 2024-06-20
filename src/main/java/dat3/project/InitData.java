package dat3.project;

import dat3.project.entity.Participant;
import dat3.project.repository.DisciplinRepository;
import dat3.project.repository.ParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final DisciplinRepository disciplinRepository;
    private final ParticipantRepository participantRepository;

    public InitData(DisciplinRepository disciplinRepository, ParticipantRepository participantRepository) {
        this.disciplinRepository = disciplinRepository;
        this.participantRepository = participantRepository;
    }


    @Override
    public void run(String... args) {
        // Create and save owners
        Participant participant1 = new Participant("abdiox", "Mand", 22, "Rødovre AtletikKlub" );
        participantRepository.save(participant1);

        Participant participant2 = new Participant("Mendy", "Mand", 28, "Rødovre AtletikKlub" );
        participantRepository.save(participant2);


    }
}
