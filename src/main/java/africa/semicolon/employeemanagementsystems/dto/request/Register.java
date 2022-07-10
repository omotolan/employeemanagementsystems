package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.data.Department;
import africa.semicolon.employeemanagementsystems.data.Level;
import africa.semicolon.employeemanagementsystems.data.Qualification;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Register {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int age;
    private String phoneNumber;
    private Level level;
    private List<Qualification> qualification;
    private Department department;
    private Boolean isSuspended;
    private LocalDateTime dateTime = LocalDateTime.now();
}
