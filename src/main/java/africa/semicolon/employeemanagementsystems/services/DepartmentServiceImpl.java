package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> listAllDepartment() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()){
            throw new IllegalArgumentException("Department does not exist");
        }
        return department;
    }
}
