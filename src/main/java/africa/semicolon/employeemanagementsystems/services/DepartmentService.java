package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> listAllDepartments();

    Department findDepartmentByName(String frrger);
}
