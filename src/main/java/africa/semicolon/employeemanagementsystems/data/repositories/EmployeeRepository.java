package africa.semicolon.employeemanagementsystems.data.repositories;

import africa.semicolon.employeemanagementsystems.data.models.*;
import africa.semicolon.employeemanagementsystems.enums.Department;
import africa.semicolon.employeemanagementsystems.enums.JobLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAddress(String emailAddress);

    List<Employee> findByJobLevel(JobLevel type);

    void delete(Employee employee);

    List<Employee> findByDepartment(Department type);

    Optional<Employee> findByEmployeeId(String employeeId);
}
