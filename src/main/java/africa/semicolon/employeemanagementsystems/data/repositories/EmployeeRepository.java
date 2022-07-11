package africa.semicolon.employeemanagementsystems.data.repositories;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.models.Employee;
import africa.semicolon.employeemanagementsystems.data.models.Level;
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
