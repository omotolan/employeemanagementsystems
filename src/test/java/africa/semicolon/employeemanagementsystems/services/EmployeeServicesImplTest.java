package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.*;
import africa.semicolon.employeemanagementsystems.data.repositories.EmployeeRepository;
import africa.semicolon.employeemanagementsystems.dto.reponse.RegisterResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.Response;
import africa.semicolon.employeemanagementsystems.dto.reponse.SuspensionStatusResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.UpdateResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;
import africa.semicolon.employeemanagementsystems.dto.request.UpdateRequest;
import africa.semicolon.employeemanagementsystems.exceptions.EmailAlreadyExist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServicesImplTest {

    private final EmployeeServices employeeServices;
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServicesImplTest(EmployeeServices employeeServices, EmployeeRepository repository) {
        this.employeeServices = employeeServices;
        this.repository = repository;
    }


    @Test
    public void testThatEmployeeCanRegister() {
        Register registerRequest = Register.builder()
                .firstName("tolani")
                .lastName("akinsola")
                .age(34)
                .emailAddress("abakinsolatolanni@gmaiill.com")
                .phoneNumber("08043432323")
                .isSuspended(false)
                .department(Department.FINANCE)
                .jobLevel(JobLevel.MIDDLE)
                .schoolQualification(SchoolQualification.BSC)
                .schoolQualification(SchoolQualification.MSC)
                .build();

        RegisterResponse registerResponse = employeeServices.registerEmployee(registerRequest);
        assertTrue(repository.count() > 0);
        assertEquals("tolani has been registered", registerResponse.toString());

    }

    @Test
    public void testThatRegistrationMethodThrowsExceptions() {
        Register registerRequest = Register.builder()
                .firstName("tolani")
                .lastName("akinsola")
                .age(34)
                .emailAddress("abakinsolatolanni@gmaiill.com")
                .phoneNumber("08043432323")
                .isSuspended(false)
                .jobLevel(JobLevel.MIDDLE)
                .schoolQualification(SchoolQualification.BSC)
                .schoolQualification(SchoolQualification.MSC)
                .build();
        assertThatThrownBy(() ->
                employeeServices.registerEmployee(registerRequest)
        )
                .isInstanceOf(EmailAlreadyExist.class)
                // .hasMessageContaining(registerRequest.getEmailAddress() + " Email already exist");
                .hasMessage(registerRequest.getEmailAddress() + " Email already exist");

    }

    @Test
    public void testToFindEmployeeById() {
        Optional<Employee> employee = employeeServices.getEmployeeById(1L);
        assertThat(employee).isNotNull();
        assertEquals(" ", employee.get().getEmailAddress());
    }

    @Test
    public void testToGetEmployeeIdByEmailAddress() {
        Long id = employeeServices.getEmployeeIdByEmailAddress("akinsolatolani@yahoo.com");
        assertEquals(5, id);
    }

    @Test
    public void testToGetAllEmployee() {
        List<Employee> employeeList = employeeServices.getAllEmployee();
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(1);
    }

    @Test
    public void testToGetEmployeesByDepartment() {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        List<Employee> employees = employeeServices.getEmployeeByDepartment(departmentRequest);
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(3);
    }

    @Test
    public void testToGetEmployeeByJobLevel() {
        List<Employee> employeeList = employeeServices.getEmployeeByJobLevel(JobLevel.MIDDLE);
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(3);
    }

    @Test
    public void testThatEmployeeCanBeSuspended() {
        SuspensionStatusResponse suspensionStatusResponse = employeeServices.suspendEmployeeByEmailAddress("akinsolatolani@yahoo.com");
        assertEquals(" ", suspensionStatusResponse.toString());
    }

    @Test
    public void testThatEmployeeCanBeUnsuspended() {
        SuspensionStatusResponse suspensionStatusResponse = employeeServices.unSuspendEmployeeEmailAddress("akinsolatolani@yahoo.com");
        assertEquals(" ", suspensionStatusResponse.toString());
    }

    @Test
    public void testToCheckEmployeeStatus() {
        Boolean status = employeeServices.isEmployeeSuspendedByEmail("akinsolatolani@yahoo.com");
        assertTrue(status);
    }

    @Test
    public void testThatEmployeeCanBeDeletedByEmailAddress() {
        Response response = employeeServices.deleteEmployeeByEmailAddress("akinsolatolani@yahoo.com");
        assertEquals(" ", response.toString());
    }

    @Test
    public void testThatEmployeeCanBeDeletedById() {
        Response response = employeeServices.deleteEmployeeById(3L);
        assertEquals(" ", response.toString());
    }

    @Test
    public void testToDeleteAllEmployee() {
        Response response = employeeServices.deleteAllEmployee();
        assertEquals(" ", response.toString());
    }

    @Test
    public void testToUpdateEmployeeDetails() {
        UpdateRequest updateRequest = new UpdateRequest();
        UpdateResponse updateResponse = employeeServices.updateEmployeeDetails(1L, updateRequest);
    }

    @Test
    public void testThatEmployeeSalaryCanBeSetWithJobLevel() {
        //  employeeServices.setEmployeeSalaryUsingJobLevel(JobLevel.MIDDLE_LEVEL);
        Optional<Employee> employee = repository.findById(26L);
        //assertThat(repository.findByJobLevel(JobLevel.MIDDLE_LEVEL))
        assertEquals(new BigDecimal(2), employee.get().getEmployeeSalary());
    }

}