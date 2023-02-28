package util;

public enum ShipDigit {
    PRESENT(1),
    ABSENT(0);

    private final int digit;

    ShipDigit(int digit) {
        this.digit = digit;
    }

    public int intValue() {
        return digit;
    }
}
