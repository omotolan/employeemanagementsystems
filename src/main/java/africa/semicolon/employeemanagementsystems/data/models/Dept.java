package africa.semicolon.employeemanagementsystems.data.models;

public enum Dept {
    FINANCE("Finance"),
    ACCOUNT("Account"),
    RESEARCH_AND_DEVELOPMENT("Research and dev"),
    PURCHASING("Purchasing"),
    MARKETING("Marketing"),
    HUMAN_RESOURCE_MANAGEMENT("HRM"),
    PRODUCTION("Production");

    private final String type;

    private Dept(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return type;
    }
}
