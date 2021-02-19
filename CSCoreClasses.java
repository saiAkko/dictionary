
public enum CSCoreClasses {
    CSC210(1, "Introduction to Computer Programming", "CSC 210", 3),
    CSC220(2, "Data Structures", "CSC 220", 3),
    CSC340(3, "Programming Methodology", "CSC 340", 3),
    CSC413(4, "Software Development", "CSC 413", 3),
    CSC600(5, "Programming Language Design", "CSC 600", 3),
    CSC000();
    private final String generalNote = "SFSU 2018 Catalog";
    private int cNum;
    private String cDesc;
    private String cTittle;
    private int cNumUnits;

    private CSCoreClasses() {
        this.cTittle = "CSC000";
    }

    private CSCoreClasses(int cNum, String cDesc, String cTittle, int cNumUnits) {
        this.cNum = cNum;
        this.cDesc = cDesc;
        this.cTittle = cTittle;
        this.cNumUnits = cNumUnits;
    }

    public int getcNum() {
        return cNum;
    }

    public String getcDesc() {
        return cDesc;
    }

    public String getcTittle() {
        return cTittle;
    }

    public void setcTittle(String cTittle) {
        this.cTittle = cTittle;
    }

    public int getcNumUnits() {
        return cNumUnits;
    }

    public void setcNumUnits(int cNumUnits) {
        this.cNumUnits = cNumUnits;
    }

    @Override
    public String toString() {
        return this.cNum + ", " + this.cDesc + ", "
                + this.cTittle + ", " + this.cNumUnits;
    }

    public static void main(String[] args) {
        System.out.println("\n valueOf()--------------------------------------");
        CSCoreClasses CSC999 = CSCoreClasses.valueOf("CSC220");
        CSC220.setcNumUnits(4);
        CSC999.setcTittle(CSC220.cTittle + "-999");
        System.out.println(CSC999);
        System.out.println(CSC220);
        System.out.println("\n values()----------------------------------------");
        System.out.println(CSCoreClasses.values().length); // 6
        for (CSCoreClasses entry : CSCoreClasses.values()) {
            System.out.println(entry);
        }
        System.out.println("\n static-----------------------------------------");
        System.out.println(CSCoreClasses.CSC210.generalNote);
        System.out.println(CSCoreClasses.CSC210.cDesc);
        System.out.println(CSCoreClasses.CSC210);
        System.out.println("\n Constructor------------------------------------");
        System.out.println(CSCoreClasses.CSC000.generalNote);
        System.out.println(CSCoreClasses.CSC000.cDesc);
        System.out.println(CSCoreClasses.CSC000);
        System.out.println("\n name()-----------------------------------------");
        System.out.println(CSC999.name());
        System.out.println(CSC210.name().length());
        System.out.println("\n equals() and compareTo()-----------------------");
        System.out.println(CSC210.equals(CSC210)); // true
        System.out.println(CSC210.equals(CSC220)); // false
        System.out.println(CSC210.compareTo(CSC220)); // -1
        System.out.println(CSC210.compareTo(CSC340)); // -2
        System.out.println(CSC210.compareTo(CSC413)); // -3
        System.out.println(CSC210.compareTo(CSC600)); // -4
    }
}
