package maslov.aptitos.controller;

import lombok.Getter;
import maslov.aptitos.domain.Division;
import maslov.aptitos.domain.Employee;
import maslov.aptitos.domain.Telephone;
import maslov.aptitos.model.EmployeeDO;
import maslov.aptitos.services.EmployeesService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeesService employeesService;

    public EmployeeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    public static class EmployeeResp {
        private String name;
        private Telephone newTelephone;
        private Division newDivision;

        public String getName() {
            return name;
        }

        public Telephone getNewTelephone() {
            return newTelephone;
        }

        public Division getNewDivision() {
            return newDivision;
        }
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