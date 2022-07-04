package src.main.java.ui;

import java.math.BigDecimal;

public interface UserIO {

    void print(String msg);

    String readString(String msgPrompt);

    int readInt(String msgPrompt);

    int readInt(String msgPrompt, int min, int max);

    long readLong(String msgPrompt);

    long readLong(String msgPrompt, long min, long max);

    float readFloat(String msgPrompt);

    float readFloat(String msgPrompt, float min, float max);

    double readDouble(String msgPrompt);

    double readDouble(String msgPrompt, double min, double max);

    BigDecimal readBigDecimal(String prompt);


}
