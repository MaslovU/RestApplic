package maslov.aptitos.controller;

import maslov.aptitos.domain.Divisions;
import maslov.aptitos.domain.Employees;
import maslov.aptitos.domain.Telephones;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private EmployeesService employeesService;

    public EmployeeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<Employees> getByName(@RequestParam String name) {
        return employeesService.getEmployeeByName(name);
    }

    @GetMapping("{id}")
    public Optional<Employees> getEmployee(@PathVariable Long id) {
        return employeesService.getEmployeeById(id);
    }

    public static class EmployeeResp {
        public String name;
        public String newTelephone;
//        public Divisions division;
    }

    @PostMapping
    public Employees createEmployee(@RequestBody EmployeeResp employee) {
        return employeesService.createNewEmployee(employee);
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