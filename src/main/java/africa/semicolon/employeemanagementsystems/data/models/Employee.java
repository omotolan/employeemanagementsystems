package africa.semicolon.employeemanagementsystems.data.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "employees")
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column( nullable = false)
    private String firstName;
    @Column( nullable = false)
    private String lastName;
    @Column(unique = true)
    private String emailAddress;
    private int age;
    @Column( nullable = false)
    private String phoneNumber;
    @OneToOne
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Level level;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Qualification> qualification;
    @OneToOne
    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;
    private Boolean isSuspended;
    private BigDecimal employeeSalary;
    private LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return id != null && Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
