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
import africa.semicolon.employeemanagementsystems.exceptions.NoEmployeeInThisDepartment;
import africa.semicolon.employeemanagementsystems.data.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

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

        //log.info(employee.getFirstName() + " has been registered");
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
        List<Employee> employee = employeeRepository.findByDept(departmentRequest.getDept());
        if (employee == null) {
            throw new NoEmployeeInThisDepartment("No employee in this department");
        }

        return employee;

    }

    @Override
    public void setEmployeeSalaryUsingJobLevel(JobLevel jobLevel) {
        Employee employee = new Employee();
        if (jobLevel == JobLevel.INTERNSHIP) {
            employee.setEmployeeSalary(EmployeeSalary.INTERNSHIP_EMPLOYEE_SALARY);
        } else if (jobLevel == JobLevel.ENTRY_LEVEL) {
            employee.setEmployeeSalary(EmployeeSalary.INTERNSHIP_EMPLOYEE_SALARY);

        } else if (jobLevel == JobLevel.MIDDLE_LEVEL) {
            employee.setEmployeeSalary(EmployeeSalary.MIDDLE_LEVEL_EMPLOYEE_SALARY);

        } else employee.setEmployeeSalary(EmployeeSalary.SENIOR_LEVEL_EMPLOYEE_SALARY);

        employeeRepository.save(employee);

    }

    @Override
    public List<Employee> findEmployeeByJobLevel(JobLevel jobLevel) {
        List<Employee> employee = employeeRepository.findByJobLevel(jobLevel);
        if (employee == null) {
            throw new IllegalArgumentException("No employee in this" + jobLevel.toString() + " level");
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

//    @Override
//    public Response deleteEmployeeById(Long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isEmpty()) {
//            throw new IllegalArgumentException("employee with " + id + " does not exist");
//        }
//        employeeRepository.delete(employee);
//        return new Response("Employee with id number: " + id + " has been deleted");
//    }

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

    @Override
    public UpdateResponse updateEmployeeDetails(long id, UpdateRequest updateRequest) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employee1 = employeeRepository.findByEmailAddress(updateRequest.getEmailAddress());
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("employee with " + id + " does not exist");
        }
        if (updateRequest.getFirstName().length() != 0) {
            employee.get().setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName().length() != 0) {
            employee.get().setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getAge() <= 0) {
            employee.get().setAge(updateRequest.getAge());
        }
        if (updateRequest.getPhoneNumber().length() != 0) {
            employee.get().setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if (employee1 == null && updateRequest.getEmailAddress().length() != 0) {
            employee.get().setEmailAddress(updateRequest.getEmailAddress());
        }
        Employee employee2 = employeeRepository.save(employee);


        return new UpdateResponse("employee with id number " + employee2.getId() + " details has been updated");
    }

}
