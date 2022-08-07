package maslov.aptitos.repo;

import maslov.aptitos.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionsRepo extends JpaRepository<Division, Long> {
    Division findByText(String text);

    List<Division> findByTextLike(String text);
}