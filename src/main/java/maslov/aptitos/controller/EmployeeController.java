package maslov.aptitos.controller;

import lombok.Getter;
import maslov.aptitos.domain.Division;
import maslov.aptitos.domain.Employee;
import maslov.aptitos.domain.Telephone;
import maslov.aptitos.model.EmployeeDO;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeesService employeesService;

    public EmployeeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Getter
    public static class EmployeeResp {
        private String name;
        public Telephone newTelephone;
        public Division newDivision;
    }

    @GetMapping
    public List<Employee> getByName(@RequestParam String name) {
        return employeesService.getEmployeeByName(name);
    }

    @GetMapping("{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) {
        return employeesService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeResp employee) {
        return employeesService.createNewEmployee(employee);
    }

    @PutMapping("{id}")
    public Employee changeEmployee(
            @PathVariable("id") Employee employeesFromDB,
            @RequestBody EmployeeDO employee) {
        return employeesService.editEmployee(employee, employeesFromDB);
    }

    @DeleteMapping
    public void delEmployee(@PathVariable Long id) {
        employeesService.deleteEmployeeByID(id);
    }
}