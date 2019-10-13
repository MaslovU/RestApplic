package maslov.aptitos.controller;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private EmployeesService employeesService;

    public EmployeeController(EmployeesRepo employeesRepo) {
        employeesService = new EmployeesService(employeesRepo);
    }

    @GetMapping
    public List<Employees> getByName(@RequestParam String name) {
        return employeesService.getEmployeeByName(name);
    }

    @GetMapping("{id}")
    public Optional<Employees> getEmployee(@PathVariable Long id) {
        return employeesService.getEmployeeById(id);
    }

    @PostMapping
    public Employees createEmployee(@RequestBody Employees employees) {
        return employeesService.createNewEmployee(employees);
    }

    @PutMapping("{id}")
    public Employees changeEmployee(
            @PathVariable("id") Employees employeesFromDB,
            @RequestBody Employees employees) {
        return employeesService.editEmployee(employees, employeesFromDB);
    }

    @DeleteMapping
    public void delEmployee( @PathVariable Long id) {
        employeesService.deleteEmployeeByID(id);
    }
}