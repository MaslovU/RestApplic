package maslov.aptitos.repo;

import maslov.aptitos.domain.Divisions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionsRepo extends JpaRepository<Divisions, Long> {
    List<Divisions> findByText(String text);

    List<Divisions> findByTextLike(String text);
}