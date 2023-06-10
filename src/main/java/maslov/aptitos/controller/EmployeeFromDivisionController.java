package maslov.aptitos.controller;

import maslov.aptitos.domain.Employee;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employeeFromDiv")
public class EmployeeFromDivisionController {

    private EmployeesService employeesService;

    public EmployeeFromDivisionController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    List<Employee> findEmployeeFromDivision(@RequestParam String text) {
        return employeesService.findEmployeeInfoByDivision(text);
    }
}