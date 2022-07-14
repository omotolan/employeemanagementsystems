package africa.semicolon.employeemanagementsystems.data.models;

public enum JobLevel {
    INTERNSHIP("Internship"), ENTRY_LEVEL("Entry_level"), MIDDLE_LEVEL("Middle_level"),
    SENIOR_LEVEL("Senior_level");
    private String type;

    JobLevel(String type) {
        this.type = type;
    }

}
