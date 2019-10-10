package maslov.aptitos.controller;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private int counter = 1;

    private final EmployeesRepo employeeRepo;

    public EmployeeController(EmployeesRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping
    public List<Employees> getName(
            @RequestParam String name
    ){
        return employeeRepo.findByNameContaining(name);
    }

    @GetMapping("{id}")
    public Optional<Employees> getEmployee(@PathVariable Long id) {
        return employeeRepo.findById(id);
    }

    @PostMapping
    public Employees createEmployee(
            @RequestBody Employees employees
    ){
        employees.setId((long) counter++);
        return employeeRepo.save(employees);
    }

    @PutMapping("{id}")
    public Employees changeEmployee(
            @PathVariable("id") Employees employeesFromDB,
            @RequestBody Employees employees
    ){
        BeanUtils.copyProperties(employees, employeesFromDB, "id");
        return employeeRepo.save(employeesFromDB);
    }

    @DeleteMapping
    public void delEmployee(
            @PathVariable("id") Employees employees
    ){
        employeeRepo.delete(employees);
    }
}
