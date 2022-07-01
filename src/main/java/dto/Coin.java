package src.main.java.dto;

public enum Coin {
    //Enum is a special class that represents a group of constants, like final variables
    QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
    private final int VALUE;

    Coin (int value) {
        this.VALUE = value;
    }

    private int getValue() {
        return VALUE;
    }

    public String toString() {
        switch (this) {
            case QUARTER:
                return "25";
            case DIME:
                return "10";
            case NICKEL:
                return "5";
            case PENNY:
                return "1";
        }
        return null;
    }
}
