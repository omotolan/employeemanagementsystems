package africa.semicolon.employeemanagementsystems.repositories;

import africa.semicolon.employeemanagementsystems.data.Department;
import africa.semicolon.employeemanagementsystems.data.Employee;
import africa.semicolon.employeemanagementsystems.data.Level;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAddress(String emailAddress);

    List<Employee> findByDepartment(Department department);

    List<Employee> findByJobLevel(Level level);

    void delete(Optional<Employee> employee);
}
