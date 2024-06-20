package dat3.project.repository;

import dat3.project.entity.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Integer> {
}
