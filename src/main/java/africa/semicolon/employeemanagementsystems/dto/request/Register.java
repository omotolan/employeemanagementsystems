package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.data.models.Department;
import africa.semicolon.employeemanagementsystems.data.models.Level;
import africa.semicolon.employeemanagementsystems.data.models.Qualification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
