package dat3.project;

import dat3.project.entity.Owners;
import dat3.project.entity.Pets;
import dat3.project.repository.OwnersRepository;
import dat3.project.repository.PetsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final PetsRepository petsRepository;
    private final OwnersRepository ownersRepository;

    public InitData(PetsRepository petsRepository, OwnersRepository ownersRepository) {
        this.petsRepository = petsRepository;
        this.ownersRepository = ownersRepository;
    }

    @Override
    public void run(String... args) {
        // Create and save owners
        Owners owner1 = new Owners("Abdiox", 22);
        ownersRepository.save(owner1);

        Owners owner2 = new Owners("Mendy", 28);
        ownersRepository.save(owner2);

        // Create and save pets with owners
        Pets pet1 = new Pets("Alfred", "Dog", "White");
        pet1.setOwner(owner1);
        petsRepository.save(pet1);

        Pets pet2 = new Pets("Bella", "Cat", "Black");
        pet2.setOwner(owner2);
        petsRepository.save(pet2);

        Pets pet3 = new Pets("Charlie", "Bird", "Green");
        pet3.setOwner(owner1);
        petsRepository.save(pet3);
    }
}
