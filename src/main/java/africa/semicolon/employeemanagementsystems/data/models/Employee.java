package africa.semicolon.employeemanagementsystems.data.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int age;
    private String phoneNumber;
    private Dept dept;
    private SchoolQualification schoolQualifications;
    private JobLevel jobLevel;


    private Boolean isSuspended;
    private BigDecimal employeeSalary;
    private LocalDateTime dateTime = LocalDateTime.now();

}
