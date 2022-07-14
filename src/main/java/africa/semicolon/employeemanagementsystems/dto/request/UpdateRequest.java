package africa.semicolon.employeemanagementsystems.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int age;
    private String phoneNumber;
}
