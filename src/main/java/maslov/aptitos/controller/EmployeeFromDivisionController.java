package maslov.aptitos.controller;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employeeFromDiv")
public class EmployeeFromDivisionController {

    private final EmployeesRepo employeeRepo;

    public EmployeeFromDivisionController(EmployeesRepo employeesRepo) {
        this.employeeRepo = employeesRepo;
    }

    @GetMapping
    List<Employees> findEmployeeFromDivision(@RequestParam String text)
    {
        return employeeRepo.findByDivision(text);
    }
}