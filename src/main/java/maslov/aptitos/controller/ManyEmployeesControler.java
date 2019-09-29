package maslov.aptitos.controller;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employees")
public class ManyEmployeesControler {

    private final EmployeesRepo employeesRepo;

    public ManyEmployeesControler(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    @GetMapping
    public List<Employees> getAllEmp(){
        return employeesRepo.findAll();
    }
}