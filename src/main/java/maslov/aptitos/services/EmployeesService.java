package maslov.aptitos.services;

import maslov.aptitos.controller.EmployeeController;
import maslov.aptitos.domain.Divisions;
import maslov.aptitos.domain.Employees;
import maslov.aptitos.domain.Telephones;
import maslov.aptitos.repo.DivisionsRepo;
import maslov.aptitos.repo.EmployeesRepo;
import maslov.aptitos.repo.TelephonesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    private final EmployeesRepo employeeRepo;
    private  final TelephonesRepo telephonesRepo;
    private  final DivisionsRepo divisionsRepo;

    public EmployeesService(EmployeesRepo employeeRepo, TelephonesRepo telephonesRepo, DivisionsRepo divisionsRepo) {
        this.employeeRepo = employeeRepo;
        this.telephonesRepo = telephonesRepo;
        this.divisionsRepo = divisionsRepo;
    }

    public List<Employees> getEmployeeByName(String name) {
        return employeeRepo.findByNameContaining(name);
    }

    public Optional<Employees> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Transactional
    public synchronized Employees createNewEmployee(EmployeeController.EmployeeResp newEmployee) {
        Employees employee = new Employees();

        Telephones tel = newEmployee.newTelephone;
        Divisions div = newEmployee.newDivision;

        String newText = tel.getText();
        String newDiv = div.getText();

        if (telephonesRepo.findByText(newText).isEmpty()) {
//            создать новую запись в базе
            telephonesRepo.save(tel);
        }
        if (divisionsRepo.findByText(newDiv).isEmpty()) {
            divisionsRepo.save(div);
        }

        Telephones telFromDB = telephonesRepo.findByText(tel.getText()).get(0);
        Divisions divFromDB = divisionsRepo.findByText(div.getText()).get(0);

        employee.setName(newEmployee.name);
        employee.setTelephone(telFromDB);
        employee.setDivision(divFromDB);

        return employeeRepo.save(employee);
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