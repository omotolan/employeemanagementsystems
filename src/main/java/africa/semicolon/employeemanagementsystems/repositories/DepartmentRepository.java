package africa.semicolon.employeemanagementsystems.repositories;

import africa.semicolon.employeemanagementsystems.data.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
