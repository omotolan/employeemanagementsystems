package africa.semicolon.employeemanagementsystems.data.models;
public enum JobLevel {
    INTERNSHIP("Internship"), ENTRY("Entry"), MIDDLE("Middle"),
    SENIOR("Senior");
    private final String type;

    JobLevel(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

}
