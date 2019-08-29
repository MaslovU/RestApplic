package maslov.aptitos.controller;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeesController {
    private int counter = 1;

    private final EmployeesRepo employeesRepo;

    public EmployeesController(EmployeesRepo employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

//    @GetMapping
//    public List<Employees> getAllEmp(){
//        return employeesRepo.findAll();
//    }

    @GetMapping
    public List<Employees> getName(
            @RequestParam String name
    ){
        return employeesRepo.findByName(name);
    }

    @GetMapping("{id}")
    public Employees getEmployee(
            @PathVariable("id") Employees employees
    ){
        return employees;
    }

    @PostMapping
    public Employees createEmployee(
            @RequestBody Employees employees
    ){
        employees.setId((long) counter++);
        return employeesRepo.save(employees);
    }

    @PutMapping("{id}")
    public Employees changeEmployee(
            @PathVariable("id") EmployeesRepo employeesFromDB,
            @RequestBody Employees employees
    ){
        BeanUtils.copyProperties(employees, employeesFromDB, "id");
        return employeesRepo.save(employees);
    }

    @DeleteMapping
    public void delEmployee(
            @PathVariable("id") Employees employees
    ){
        employeesRepo.delete(employees);
    }
}
