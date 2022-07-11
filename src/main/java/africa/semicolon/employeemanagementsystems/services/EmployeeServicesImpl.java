package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Employee;
import africa.semicolon.employeemanagementsystems.data.models.EmployeeSalary;
import africa.semicolon.employeemanagementsystems.data.models.JobLevel;
import africa.semicolon.employeemanagementsystems.data.models.Level;
import africa.semicolon.employeemanagementsystems.dto.reponse.RegisterResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.Response;
import africa.semicolon.employeemanagementsystems.dto.reponse.SuspensionStatusResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;
import africa.semicolon.employeemanagementsystems.exceptions.EmailAlreadyExist;
import africa.semicolon.employeemanagementsystems.exceptions.NoEmployeeInThisDepartment;
import africa.semicolon.employeemanagementsystems.data.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServicesImpl implements EmployeeServices {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public EmployeeServicesImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        // this.mapper = mapper;
    }


    @Override
    public RegisterResponse register(Register registerRequest) {
        if (registerRequest == null) {
            throw new IllegalArgumentException("Employee records can not be empty");
        }
        Employee employee = employeeRepository.findByEmailAddress(registerRequest.getEmailAddress());
        if (employee != null) {
            throw new EmailAlreadyExist(registerRequest.getEmailAddress() + " Email already exist");
        }
        employee = mapper.map(registerRequest, Employee.class);

        employeeRepository.save(employee);

        return new RegisterResponse(employee.getFirstName() + " has been registered");
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            throw new IllegalArgumentException("Employee list is empty");
        }
        return employeeList;
    }


    @Override
    public List<Employee> findEmployeeByDepartment(DepartmentRequest departmentRequest) {
        List<Employee> employee = employeeRepository.findByDepartment(departmentRequest.getDepartment());
        if (employee == null) {
            throw new NoEmployeeInThisDepartment("No employee in this department");
        }

        return employee;

    }

    @Override
    public void setEmployeeSalaryUsingJobLevel(Level level) {
        Employee employee = new Employee();
        if (level.getLevel() == JobLevel.INTERNSHIP) {
            employee.setEmployeeSalary(EmployeeSalary.INTERNSHIP_EMPLOYEE_SALARY);
        } else if (level.getLevel() == JobLevel.ENTRY_LEVEL) {
            employee.setEmployeeSalary(EmployeeSalary.INTERNSHIP_EMPLOYEE_SALARY);

        } else if (level.getLevel() == JobLevel.MIDDLE_LEVEL) {
            employee.setEmployeeSalary(EmployeeSalary.MIDDLE_LEVEL_EMPLOYEE_SALARY);

        } else employee.setEmployeeSalary(EmployeeSalary.SENIOR_LEVEL_EMPLOYEE_SALARY);

        employeeRepository.save(employee);

    }

    @Override
    public List<Employee> findEmployeeByJobLevel(Level level) {
        List<Employee> employee = new ArrayList<>();
        employee = employeeRepository.findByJobLevel(level);
        if (employee == null) {
            throw new IllegalArgumentException("No employee in this" + level.toString() + " level");
        }

        return employee;
    }

    @Override
    public SuspensionStatusResponse suspendEmployee(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new IllegalArgumentException("employee with " + emailAddress + " does not exist");
        }
        employee.setIsSuspended(true);
        employeeRepository.save(employee);

        return new SuspensionStatusResponse(employee.getFirstName() +
                " " + employee.getLastName() + " is suspended");
    }

    @Override
    public Boolean isEmployeeSuspended(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new IllegalArgumentException("employee with " + emailAddress + " does not exist");
        }

        return employee.getIsSuspended();
    }

    @Override
    public SuspensionStatusResponse unsuspendEmployee(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new IllegalArgumentException("employee with " + emailAddress + " does not exist");
        }
        if (employee.getIsSuspended() == Boolean.FALSE) {
            throw new IllegalArgumentException("Employee suspension status is false");
        }
        employee.setIsSuspended(Boolean.FALSE);
        employeeRepository.save(employee);

        return new SuspensionStatusResponse(employee.getFirstName() + " " + employee.getLastName() + " is" +
                " unsuspended");
    }

    @Override
    public Response deleteEmployeeByEmailAddress(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new IllegalArgumentException("employee with " + emailAddress + " does not exist");
        }
        employeeRepository.delete(employee);

        return new Response(employee.getFirstName() + " " + employee.getLastName() + " has been" +
                " deleted");
    }

    @Override
    public Long findEmployeeIdByEmailAddress(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new IllegalArgumentException("employee with " + emailAddress + " does not exist");
        }

        return employee.getId();
    }

    @Override
    public Response deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("employee with " + id + " does not exist");
        }
        employeeRepository.delete(employee);
        return new Response("Employee with id number: " + id + " has been deleted");
    }

    @Override
    public Response deleteAllEmployee() {
        employeeRepository.deleteAll();
        return new Response("All employee have been deleted");
    }

    @Override
    public Optional<Employee> findEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("employee with " + id + " does not exist");
        }

        return employee;
      /*  return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("employee with " + id + " does not exist")));**/
    }

}
