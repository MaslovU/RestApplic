package maslov.aptitos.repo;

import maslov.aptitos.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContaining(String name);

    @Query("select e from Employee e where e.division.text =:text")
    List<Employee> findByDivision(String text);
}