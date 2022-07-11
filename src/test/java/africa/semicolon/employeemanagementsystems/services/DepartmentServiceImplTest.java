package africa.semicolon.employeemanagementsystems.services;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DepartmentServiceImplTest {
    @Autowired
    private DepartmentService departmentService;
    @Test
    public void testToGetListOfDepartments(){
        List<Department> departmentList = departmentService.listAllDepartments();
    }
    @Test
    public void testToGetDepartmentByName(){
        Department department = departmentService.findDepartmentByName("frrger");
    }

}