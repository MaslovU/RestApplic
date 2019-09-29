package maslov.aptitos.repo;

import maslov.aptitos.domain.Divisions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionsRepo extends JpaRepository<Divisions, Long> {
}