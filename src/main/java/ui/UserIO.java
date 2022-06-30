package src.main.java.ui;

import java.math.BigDecimal;

public interface UserIO {
    void print(String msg);

    // method for an answer from the user to return.
    String readString(String prompt);

    int readInt(String prompt);

    long readLong(String msgPrompt);

    long readLong(String msgPrompt, long min, long max);

    float readFloat(String msgPrompt);

    float readFloat(String msgPrompt, float min, float max);

    double readDouble(String msgPrompt);

    double readDouble(String msgPrompt, double min, double max);

    BigDecimal readBigDecimal(String prompt);
}
