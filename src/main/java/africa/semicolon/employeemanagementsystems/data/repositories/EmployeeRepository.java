package africa.semicolon.employeemanagementsystems.data.repositories;

import africa.semicolon.employeemanagementsystems.data.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAddress(String emailAddress);

    List<Employee> findByDept(Dept dept);

    List<Employee> findByJobLevel(JobLevel jobLevel);

    //void delete(Optional<Employee> employee);
    void delete(Employee employee);
    Employee save(Optional<Employee> employee);
}
