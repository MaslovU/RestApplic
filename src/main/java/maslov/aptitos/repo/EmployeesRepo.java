package maslov.aptitos.repo;

import maslov.aptitos.domain.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {
    List<Employees> findByNameContaining(String name);
    @Query("select e from Employees e where e.division.text =:text")
    List<Employees> findByDivision(String text);
}