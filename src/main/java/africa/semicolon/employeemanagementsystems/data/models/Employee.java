package africa.semicolon.employeemanagementsystems.data.models;

import africa.semicolon.employeemanagementsystems.enums.Department;
import africa.semicolon.employeemanagementsystems.enums.JobLevel;
import africa.semicolon.employeemanagementsystems.enums.SchoolQualification;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;


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
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(EnumType.STRING)
    //@OneToMany
    private SchoolQualification schoolQualification;
    @Enumerated(EnumType.STRING)
    private JobLevel jobLevel;
    private Boolean isSuspended;
    private BigDecimal employeeSalary;
    private String employeeId;
    private Instant timeCreated = Instant.now();

}
