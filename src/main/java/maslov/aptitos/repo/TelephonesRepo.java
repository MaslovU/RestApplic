package maslov.aptitos.repo;

import maslov.aptitos.domain.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelephonesRepo extends JpaRepository<Telephone, Long> {
    Telephone findByText(String text);

    Optional<Telephone> findById(Long id);

    List<Telephone> findByTextLike(String text);
}