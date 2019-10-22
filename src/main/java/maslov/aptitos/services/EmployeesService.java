package maslov.aptitos.services;

import maslov.aptitos.controller.EmployeeController;
import maslov.aptitos.domain.Employees;
import maslov.aptitos.repo.EmployeesRepo;
import maslov.aptitos.repo.TelephonesRepo;
import maslov.aptitos.services.TelephonesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    private final EmployeesRepo employeeRepo;
    private  final TelephonesRepo telephonesRepo;

    public EmployeesService(EmployeesRepo employeeRepo, TelephonesRepo telephonesRepo) {
        this.employeeRepo = employeeRepo;
        this.telephonesRepo = telephonesRepo;
    }

    public List<Employees> getEmployeeByName(String name) {
        return employeeRepo.findByNameContaining(name);
    }

    public Optional<Employees> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }
/*
Переделать метод создания сотрудника с автоматическим добавлением id подразделения и id телефона
 */
    @Transactional
    public synchronized Employees createNewEmployee(EmployeeController.EmployeeResp employee) {
        Employees employees = new Employees();
        employees.setName(employee.name);
        String tel = employee.newTelephone;
//        telephonesRepo.findByTextContaining(employee.newTelephone);
        if (telephonesRepo.findByTextContaining(employee.newTelephone) == null) {
//            создать новыу запись в базе
        } else {

        }
//        employees.setTelephone(employee.newTelephone);
//        employees.setDivision(employee.division);
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