package dat3.project.repository;

import dat3.project.entity.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinRepository extends JpaRepository<Disciplin, Integer> {
}
