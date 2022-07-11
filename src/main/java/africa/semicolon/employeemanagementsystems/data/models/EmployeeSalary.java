package africa.semicolon.employeemanagementsystems.data.models;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class EmployeeSalary {
    public static final BigDecimal INTERNSHIP_EMPLOYEE_SALARY = BigDecimal.valueOf(80000);
    public static final BigDecimal ENTRY_LEVEL_EMPLOYEE_SALARY = BigDecimal.valueOf(150000);
    public static final BigDecimal MIDDLE_LEVEL_EMPLOYEE_SALARY = BigDecimal.valueOf(400000);
    public static final BigDecimal SENIOR_LEVEL_EMPLOYEE_SALARY = BigDecimal.valueOf(600000);
}
