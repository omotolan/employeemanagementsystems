package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.models.Dept;
import lombok.Data;

@Data
public class DepartmentRequest {
    private Dept dept;
}
