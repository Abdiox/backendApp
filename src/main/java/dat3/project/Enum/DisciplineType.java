package dat3.project.Enum;

public enum DisciplineType {
    LØB_100_METER("100-meterløb"),
    LØB_200_METER("200-meterløb"),
    LØB_400_METER("400-meterløb"),
    LØB_800_METER("800-meterløb"),
    LØB_1500_METER("1500-meterløb"),
    HÆKKELØB("Hækkeløb"),
    HØJDESPIRING("Højdespring"),
    STANGSPRING("Stangspring"),
    LÆNGDESPRING("Længdespring"),
    KUGLESTØD("Kuglestød"),
    DISKOSKAST("Diskoskast"),
    SPYDKAST("Spydkast");

    private final String name;

    DisciplineType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
