package africa.semicolon.employeemanagementsystems.dto.request;

import africa.semicolon.employeemanagementsystems.data.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Register {
    @NotNull(message = "first name can not be null")
    private String firstName;
    @NotNull(message = "last name can not be null")
    private String lastName;
    @NotNull(message = "email can not be null")
    @Email
    private String emailAddress;
    private int age;
    @NotNull(message = "phone number can not be null")
    private String phoneNumber;
    private JobLevel jobLevel;
    private Dept dept;
    private SchoolQualification schoolQualification;
    private Boolean isSuspended = false;

}
