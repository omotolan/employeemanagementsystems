package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Employee;
import africa.semicolon.employeemanagementsystems.data.models.EmployeeSalary;
import africa.semicolon.employeemanagementsystems.data.models.JobLevel;
import africa.semicolon.employeemanagementsystems.dto.reponse.RegisterResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.Response;
import africa.semicolon.employeemanagementsystems.dto.reponse.SuspensionStatusResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.UpdateResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;
import africa.semicolon.employeemanagementsystems.dto.request.UpdateRequest;
import africa.semicolon.employeemanagementsystems.exceptions.EmailAlreadyExist;
import africa.semicolon.employeemanagementsystems.exceptions.EmployeeDoesNotExist;
import africa.semicolon.employeemanagementsystems.exceptions.NoEmployeeInThisDepartment;
import africa.semicolon.employeemanagementsystems.data.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServicesImpl implements EmployeeServices {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeServicesImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public RegisterResponse registerEmployee(Register registerRequest) {
        if (emailExists(registerRequest.getEmailAddress())) {
            throw new EmailAlreadyExist(registerRequest.getEmailAddress() + " Email already exist");
        }

        Employee employee = mapper.map(registerRequest, Employee.class);
        employee.setEmployeeSalary(setEmployeeSalaryUsingJobLevel(registerRequest.getJobLevel()));
        employee.setTimeCreated(Instant.now());
        employeeRepository.save(employee);

        log.info(employee.getFirstName() + " created at " + Instant.now());
        return new RegisterResponse(employee.getFirstName() + " has been registered");
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            throw new IllegalStateException("Employee list is empty");
        }
        return employeeList;
    }


    @Override
    public List<Employee> getEmployeeByDepartment(DepartmentRequest departmentRequest) {
        List<Employee> employee = employeeRepository.findByDepartment(departmentRequest.getType());
        if (employee == null) {
            throw new NoEmployeeInThisDepartment("No employee in this department");
        }

        return employee;
    }

    @Override
    public BigDecimal setEmployeeSalaryUsingJobLevel(JobLevel jobLevel) {
        Employee employee = new Employee();
        if (jobLevel == JobLevel.INTERNSHIP) {
            employee.setEmployeeSalary(EmployeeSalary.INTERNSHIP_EMPLOYEE_SALARY);
        } else if (jobLevel == JobLevel.ENTRY) {
            employee.setEmployeeSalary(EmployeeSalary.INTERNSHIP_EMPLOYEE_SALARY);

        } else if (jobLevel == JobLevel.MIDDLE) {
            employee.setEmployeeSalary(EmployeeSalary.MIDDLE_LEVEL_EMPLOYEE_SALARY);

        } else employee.setEmployeeSalary(EmployeeSalary.SENIOR_LEVEL_EMPLOYEE_SALARY);

        employeeRepository.save(employee);

        return employee.getEmployeeSalary();
    }

    @Override
    public List<Employee> getEmployeeByJobLevel(JobLevel jobLevel) {
        List<Employee> employee = employeeRepository.findByJobLevel(jobLevel);
        if (employee == null) {
            throw new IllegalArgumentException("No employee in this" + jobLevel.toString() + " level");
        }

        return employee;
    }

    @Override
    public SuspensionStatusResponse suspendEmployeeByEmailAddress(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new EmployeeDoesNotExist("employee with email address " + emailAddress + " does not exist");
        }
        employee.setIsSuspended(true);
        employeeRepository.save(employee);

        return new SuspensionStatusResponse(employee.getFirstName() +
                " " + employee.getLastName() + " is suspended");
    }
    @Override
    public SuspensionStatusResponse suspendEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeDoesNotExist("employee with id: " + id + " does not exist");
        }
        employee.get().setIsSuspended(true);
        employeeRepository.save(employee.get());

        return new SuspensionStatusResponse(employee.get().getFirstName() +
                " " + employee.get().getLastName() + " is suspended");
    }

    @Override
    public Boolean isEmployeeSuspendedByEmail(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new EmployeeDoesNotExist("employee with id " + emailAddress + " does not exist");
        }

        return employee.getIsSuspended();
    }
    @Override
    public Boolean isEmployeeSuspendedById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeDoesNotExist("employee with id " + id + " does not exist");
        }

        return employee.get().getIsSuspended();
    }

    @Override
    public SuspensionStatusResponse unSuspendEmployeeEmailAddress(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new EmployeeDoesNotExist("employee with email address" + emailAddress + " does not exist");
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
    public SuspensionStatusResponse unSuspendEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeDoesNotExist("employee with id: " + id + " does not exist");
        }
        if (employee.get().getIsSuspended() == Boolean.FALSE) {
            throw new IllegalArgumentException("Employee suspension status is false");
        }
        employee.get().setIsSuspended(Boolean.FALSE);
        employeeRepository.save(employee.get());

        return new SuspensionStatusResponse(employee.get().getFirstName() + " " + employee.get().getLastName() + " is" +
                " unsuspended");
    }

    @Override
    public Response deleteEmployeeByEmailAddress(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new EmployeeDoesNotExist("employee with email address " + emailAddress + " does not exist");
        }
        employeeRepository.delete(employee);

        return new Response(employee.getFirstName() + " " + employee.getLastName() + " has been" +
                " deleted");
    }

    @Override
    public Long getEmployeeIdByEmailAddress(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        if (employee == null) {
            throw new EmployeeDoesNotExist("employee with email address " + emailAddress + " does not exist");
        }

        return employee.getId();
    }

    @Override
    public Response deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("employee with " + id + " does not exist");
        }
        employeeRepository.delete(employee.get());
        return new Response("Employee with id number: " + id + " has been deleted");
    }

    @Override
    public Response deleteAllEmployee() {
        employeeRepository.deleteAll();
        return new Response("All employee have been deleted");
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeDoesNotExist("employee with id: " + id + " does not exist");
        }

        return employee;
      /*  return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("employee with " + id + " does not exist")));**/
    }

    @Override
    public UpdateResponse updateEmployeeDetails(long id, UpdateRequest updateRequest) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeDoesNotExist("employee with id: " + id + " does not exist");
        }
        if (updateRequest.getFirstName().length() != 0) {
            employee.get().setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName().length() != 0) {
            employee.get().setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getAge() > 0) {
            employee.get().setAge(updateRequest.getAge());
        }
        if (updateRequest.getPhoneNumber().length() != 0) {
            employee.get().setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if (!emailExists(updateRequest.getEmailAddress()) && updateRequest.getEmailAddress().length() != 0) {
            employee.get().setEmailAddress(updateRequest.getEmailAddress());
        }

        employeeRepository.save(employee.get());

        log.info("employee with id: " +
                employee.get().getId() + " details was updated at " + Instant.now());

        return new UpdateResponse("employee with id number " + " details has been updated");
    }

    private Boolean emailExists(String emailAddress) {
        Employee employee = employeeRepository.findByEmailAddress(emailAddress);
        return employee != null;
    }

}
