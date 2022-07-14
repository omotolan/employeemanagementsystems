package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> listAllDepartment();

    Optional<Department> findDepartmentById(Long id);
}
