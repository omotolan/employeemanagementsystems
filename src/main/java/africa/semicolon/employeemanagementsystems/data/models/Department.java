package africa.semicolon.employeemanagementsystems.data.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Dept dept;
    @CreationTimestamp
    private LocalDateTime creationDate = LocalDateTime.now();
    public Department(Dept dept) {
        this.dept = dept;
    }

    public Department() {

    }
}
