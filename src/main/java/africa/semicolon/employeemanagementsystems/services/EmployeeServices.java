package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Employee;
import africa.semicolon.employeemanagementsystems.enums.JobLevel;
import africa.semicolon.employeemanagementsystems.dto.reponse.RegisterResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.Response;
import africa.semicolon.employeemanagementsystems.dto.reponse.SuspensionStatusResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.UpdateResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;
import africa.semicolon.employeemanagementsystems.dto.request.UpdateRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeServices {
    RegisterResponse registerEmployee(Register registerRequest);
    List<Employee> getAllEmployee();

    List<Employee> getEmployeeByDepartment(DepartmentRequest departmentRequest);

    BigDecimal setEmployeeSalaryUsingJobLevel(JobLevel jobLevel);

    List<Employee> getEmployeeByJobLevel(JobLevel jobLevel);

    Boolean isEmployeeSuspendedById(String employeeId);
    SuspensionStatusResponse unSuspendEmployeeById(String employeeId);

    SuspensionStatusResponse suspendEmployeeByEmailAddress(String emailAddress);
    SuspensionStatusResponse suspendEmployeeById(String employeeId);

    Boolean isEmployeeSuspendedByEmail(String emailAddress);

    SuspensionStatusResponse unSuspendEmployeeEmailAddress(String emailAddress);

    Response deleteEmployeeByEmailAddress(String emailAddress);

    Long getEmployeeIdByEmailAddress(String emailAddress);

    Response deleteEmployeeById(String employeeId);

    Response deleteAllEmployee();

    Optional<Employee> getEmployeeById(String employeeId);

    UpdateResponse updateEmployeeDetails(String employeeId, UpdateRequest updateRequest);
}
