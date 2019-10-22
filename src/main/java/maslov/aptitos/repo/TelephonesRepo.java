package maslov.aptitos.repo;

import maslov.aptitos.domain.Telephones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelephonesRepo extends JpaRepository<Telephones, Long> {
    List<Telephones> findByTextContaining(String text);
}