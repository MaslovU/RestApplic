package maslov.aptitos.repo;

import maslov.aptitos.domain.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {

    List<Employees> findByName(String name);
}
