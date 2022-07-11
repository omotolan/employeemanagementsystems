package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> listAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentByName(String frrger) {
        Department department = departmentRepository.findByDepartmentName(frrger);
        if (department == null){
            throw new IllegalArgumentException("Department does not exist");
        }
        return department;
    }
}
