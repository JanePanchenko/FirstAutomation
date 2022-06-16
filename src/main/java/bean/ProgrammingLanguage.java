package bean;

public enum ProgrammingLanguage {

    JAVA("Java", 1),
    VB("VB", 2),
    SQL("SQL", 3),
    C("C", 4),
    C_PLUS_PLUS("C++", 5);

    private String name;
    private int order;

    ProgrammingLanguage(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
