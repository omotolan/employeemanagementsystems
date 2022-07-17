package africa.semicolon.employeemanagementsystems.data.repositories;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void testThatEmployeeCanBeSaved(){
        Employee employee = new Employee();
        employee.setFirstName("tolani");
        employee.setLastName("akinsola");
        employee.setEmailAddress("adonimawobe@yahoo.com");
        employee.setPhoneNumber("0983843943");
        employeeRepository.save(employee);

        assertTrue(employeeRepository.count() > 0);

    }
}