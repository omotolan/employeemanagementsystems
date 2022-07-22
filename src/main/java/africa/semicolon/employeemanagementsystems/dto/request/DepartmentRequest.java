package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.enums.Department;
import lombok.Data;

@Data
public class DepartmentRequest {
    private Department type;
}
