package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.data.Department;
import africa.semicolon.employeemanagementsystems.data.Dept;
import lombok.Data;

@Data
public class DepartmentRequest {
    private Department department;
}
