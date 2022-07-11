package africa.semicolon.employeemanagementsystems.data.repositories;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentName(String frrger);
}
