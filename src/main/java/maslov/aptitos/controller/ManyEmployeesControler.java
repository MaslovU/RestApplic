package maslov.aptitos.controller;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employees")
public class ManyEmployeesControler {

    private EmployeesService employeesService;

    public ManyEmployeesControler(EmployeesRepo employeesRepo) {
        employeesService = new EmployeesService(employeesRepo);
    }

    @GetMapping
    public List<Employees> getAllEmp() {
        return employeesService.findAllEmployees();
    }
}