package maslov.aptitos.repo;

import maslov.aptitos.domain.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {
}
