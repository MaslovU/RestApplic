package maslov.aptitos.controller;

import maslov.aptitos.domain.Employee;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employees")
public class ManyEmployeesControler {

    private EmployeesService employeesService;

    public ManyEmployeesControler(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<Employee> getAllEmp() {
        return employeesService.findAllEmployees();
    }
}