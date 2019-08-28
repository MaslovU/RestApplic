package maslov.aptitos.repo;

import maslov.aptitos.domain.Telephones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephonesRepo extends JpaRepository<Telephones, Long> {
}
