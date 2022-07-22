package africa.semicolon.employeemanagementsystems.enums;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
@AllArgsConstructor
public enum SchoolQualification {
    BSC("Bachelor_Degree"),
    PD("Postgraduate_Diploma"),
    MSC("Masters_Degree"),
    PHD("Doctorate_Degree");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private final String type;

    SchoolQualification(String type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return type;
    }
}
