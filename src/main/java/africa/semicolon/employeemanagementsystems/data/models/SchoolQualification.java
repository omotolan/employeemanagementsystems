package africa.semicolon.employeemanagementsystems.data.models;

public enum SchoolQualification {
    BSC("Bachelor_Degree"),
    PD("Postgraduate_Diploma"),
    MSC("Masters_Degree"),
    PHD("Doctorate_Degree");

    private final String type;

    SchoolQualification(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
