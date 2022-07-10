package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.*;
import africa.semicolon.employeemanagementsystems.dto.reponse.RegisterResponse;
import africa.semicolon.employeemanagementsystems.dto.reponse.Response;
import africa.semicolon.employeemanagementsystems.dto.reponse.SuspensionStatusResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;
import africa.semicolon.employeemanagementsystems.exceptions.EmailAlreadyExist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServicesImplTest {
    @Autowired
    private EmployeeServices employeeServices;

    private Department department;
    private SchoolQualification schoolQualification;

    @Test
    public void testThatEmployeeCanRegister() {
        Register registerRequest = new Register();
        registerRequest.setFirstName("tolani");
        registerRequest.setLastName("akinsola");
        registerRequest.setAge(34);
        registerRequest.setEmailAddress("akinsolatolani@yahoo.com");
        registerRequest.setPhoneNumber("08043432323");
        registerRequest.setIsSuspended(false);
//        registerRequest.setDepartment(new Department(Dept.ACCOUNT));
//        registerRequest.setQualification(schoolQualification.);
        RegisterResponse registerResponse = employeeServices.register(registerRequest);
        assertEquals("tolani has been registered", registerResponse.toString());
        // assertEquals(" ", EmailAlreadyExist.class.);
        Exception me = assertThrows(EmailAlreadyExist.class, () -> employeeServices.register(registerRequest),
                "Email already exist");
        assertEquals("Email already exist", me.getMessage());
//        var me =  assertThrows(EmailAlreadyExist.class, ()->employeeServices.register(registerRequest),
//                "Email already exist");
        assertTrue(me.getMessage().contains("Email already exist"));
    }
    @Test
    public void testToGetEmployeeIdByEmailAddress(){
        Long id = employeeServices.findEmployeeIdByEmailAddress("akinsolatolani@yahoo.com");
        assertEquals(5, id);
    }
    @Test
    public void testToGetAllEmployee() {
        List<Employee> employeeList = employeeServices.getAllEmployee();
        assertThat(employeeList.size()).isEqualTo(1);
    }
    @Test
    public void testToGetEmployeesByDepartment(){
        DepartmentRequest departmentRequest = new DepartmentRequest();
        List<Employee> employees = employeeServices.findEmployeeByDepartment(departmentRequest);
        assertThat(employees.size()).isEqualTo(3);
    }
    @Test
    public void testToGetEmployeeByJobLevel(){
        Level level = new Level();
        List<Employee> employeeList = employeeServices.findEmployeeByJobLevel(level);
        assertThat(employeeList.size()).isEqualTo(3);
    }
    @Test
    public void testThatEmployeeCanBeSuspended(){
        SuspensionStatusResponse suspensionStatusResponse = employeeServices.suspendEmployee("akinsolatolani@yahoo.com");
        assertEquals(" ", suspensionStatusResponse.toString());
    }
    @Test
    public void testThatEmployeeCanBeUnsuspended(){
        SuspensionStatusResponse suspensionStatusResponse = employeeServices.unsuspendEmployee("akinsolatolani@yahoo.com");
        assertEquals(" ", suspensionStatusResponse.toString());
    }
    @Test
    public void testToCheckEmployeeStatus(){
        Boolean status = employeeServices.isEmployeeSuspended("akinsolatolani@yahoo.com");
        assertTrue(status);
    }
    @Test
    public void testThatEmployeeStatusCanBeGotten(){

    }
    @Test
    public void testThatEmployeeCanBeDeletedByEmailAddress(){
        Response response = employeeServices.deleteEmployeeByEmailAddress("akinsolatolani@yahoo.com");
        assertEquals(" ", response);
    }
    @Test
    public void testThatEmployeeCanBeDeletedById(){
        Response response = employeeServices.deleteEmployeeById(3L);
        assertEquals(" ", response);
    }
    @Test
    public void testToDeleteAllEmployee(){
        Response response = employeeServices.deleteAllEmployee();
    }

}