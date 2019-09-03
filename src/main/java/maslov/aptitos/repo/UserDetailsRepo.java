package maslov.aptitos.repo;

import maslov.aptitos.domain.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<SuperUser, String> {
}
