package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.Employee;
import africa.semicolon.employeemanagementsystems.data.Level;
import africa.semicolon.employeemanagementsystems.dto.reponse.RegisterResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.Response;
import africa.semicolon.employeemanagementsystems.dto.reponse.SuspensionStatusResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;

import java.util.List;

public interface EmployeeServices {
    RegisterResponse register(Register registerRequest);
    List<Employee> getAllEmployee();

    List<Employee> findEmployeeByDepartment(DepartmentRequest departmentRequest);

    void setEmployeeSalaryUsingJobLevel(Level level);

    List<Employee> findEmployeeByJobLevel(Level level);

    SuspensionStatusResponse suspendEmployee(String emailAddress);

    Boolean isEmployeeSuspended(String emailAddress);

    SuspensionStatusResponse unsuspendEmployee(String emailAddress);

    Response deleteEmployeeByEmailAddress(String emailAddress);

    Long findEmployeeIdByEmailAddress(String emailAddress);

    Response deleteEmployeeById(Long id);

    Response deleteAllEmployee();
}
