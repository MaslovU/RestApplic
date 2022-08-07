package maslov.aptitos.services;

import maslov.aptitos.controller.EmployeeController;
import maslov.aptitos.domain.Division;
import maslov.aptitos.domain.Employee;
import maslov.aptitos.domain.Telephone;
import maslov.aptitos.model.EmployeeDO;
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
    private final TelephonesRepo telephonesRepo;
    private final DivisionsRepo divisionsRepo;

    public EmployeesService(EmployeesRepo employeeRepo, TelephonesRepo telephonesRepo, DivisionsRepo divisionsRepo) {
        this.employeeRepo = employeeRepo;
        this.telephonesRepo = telephonesRepo;
        this.divisionsRepo = divisionsRepo;
    }

    public List<Employee> getEmployeeByName(String name) {
        return employeeRepo.findByNameContaining(name);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Transactional
    public Employee createNewEmployee(EmployeeController.EmployeeResp newEmployee) {
        Employee employee = new Employee();

        var tel = checkIfTelExist(getTelephoneText(newEmployee.getNewTelephone()), newEmployee);
        var div = checkIfDivisionExist(getDivisionText(newEmployee.getNewDivision()), newEmployee);

        employee.setName(newEmployee.getName());
        employee.setTelephone(tel);
        employee.setDivision(div);

        return employeeRepo.save(employee);
    }

    @Transactional
    public Employee editEmployee(EmployeeDO employee, Employee employeesFromDB) {
        BeanUtils.copyProperties(employee, employeesFromDB, "id");
        return employeeRepo.save(employeesFromDB);
    }

    @Transactional
    public void deleteEmployeeByID(Long id) {
        employeeRepo.deleteById(id);
    }

    public List<Employee> findEmployeeInfoByDivision(String text) {
        return employeeRepo.findByDivision(text);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    private String getTelephoneText(Telephone tel) {
        try {
            return tel.getText();
        } catch (NullPointerException e) {
            return "";
        }
    }

    private Telephone checkIfTelExist(String newTelText, EmployeeController.EmployeeResp newEmployee) {
        var savedTel = telephonesRepo.findByText(newTelText);
        if (savedTel.getText().isEmpty() && !newTelText.isEmpty()) {
            return telephonesRepo.save(newEmployee.getNewTelephone());
        }
        return savedTel;
    }

    private Division checkIfDivisionExist(String newDivText, EmployeeController.EmployeeResp newEmployee) {
        var savedDiv = divisionsRepo.findByText(newDivText);
        if (savedDiv.getText().isEmpty()) {
            return divisionsRepo.save(newEmployee.getNewDivision());
        }
        return savedDiv;
    }

    private String getDivisionText(Division div) {
        try {
            return div.getText();
        } catch (NullPointerException e) {
            return "";
        }
    }
}