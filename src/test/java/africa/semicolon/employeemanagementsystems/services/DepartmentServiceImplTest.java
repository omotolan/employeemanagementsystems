package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.models.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DepartmentServiceImplTest {
    @Autowired
    private DepartmentService departmentService;
    @Test
    public void testToGetListOfDepartments(){
        List<Department> departmentList = departmentService.listAllDepartment();
    }
    @Test
    public void testToGetDepartmentByName(){

        Optional<Department> department = departmentService.findDepartmentById(1L);
       // department.setDept(Dept.FINANCE);
    }

}