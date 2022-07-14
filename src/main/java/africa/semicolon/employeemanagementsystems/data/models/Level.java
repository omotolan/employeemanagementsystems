package africa.semicolon.employeemanagementsystems.data.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Level(JobLevel level) {
        this.level = level;
    }

    @Enumerated(EnumType.STRING)
    private JobLevel level;

}
