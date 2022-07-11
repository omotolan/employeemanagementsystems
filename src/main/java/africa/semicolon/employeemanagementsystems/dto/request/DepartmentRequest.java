package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import lombok.Data;

@Data
public class DepartmentRequest {
    private Department department;
}
