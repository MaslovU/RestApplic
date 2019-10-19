package maslov.aptitos.services;

import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {
    private int counter = 1;

    private final EmployeesRepo employeeRepo;

    public EmployeesService(EmployeesRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employees> getEmployeeByName(String name) {
        return employeeRepo.findByNameContaining(name);
    }

    public Optional<Employees> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Transactional
    public synchronized Employees createNewEmployee(Employees employees) {
        return employeeRepo.save(employees);
    }

    @Transactional
    public Employees editEmployee(Employees employees, Employees employeesFromDB) {
        BeanUtils.copyProperties(employees, employeesFromDB, "id");
        return employeeRepo.save(employeesFromDB);
    }

    @Transactional
    public void deleteEmployeeByID(Long id) {
        employeeRepo.deleteById(id);
    }

    public List<Employees> findEmployeeInfoByDivision(String text) {
        return employeeRepo.findByDivision(text);
    }

    public List<Employees> findAllEmployees() {
        return employeeRepo.findAll();
    }
}