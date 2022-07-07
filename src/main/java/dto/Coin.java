package src.main.java.dto;

public enum Coin {

    // a new variable with four types of values
    QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
    private final int value;

    // getters and setters
    Coin (int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }

    // return the b=value of each variable in string, for changing in future
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
