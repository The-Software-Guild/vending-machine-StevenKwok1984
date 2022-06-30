package src.main.java.ui;
import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    final private Scanner console = new Scanner(System.in);

    // display message
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    // method for an answer from the user to return.
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;

        while (invalidInput) {
            try {
                // print message
                String stringInput = this.readString(prompt);
                // obtain and parse input
                num = Integer.parseInt(stringInput);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Error: Please enter an integer");
            }
        }
        return num;
    }
    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Error: please input a long number");
            }
        }
    }


    @Override
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }


    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Error: Please enter a float number.");
            }
        }
    }


    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }


    @Override
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Error. Please enter a double number.");
            }
        }
    }


    @Override
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }


    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal bigDecimalInput = null;
        boolean invalidInput = true;
        //need to check that the input is an integer first
        while (invalidInput) {
            try {
                //print the msgPrompt
                System.out.println(prompt);
                //Get the input line, try to convert it to a big decimal
                String stringInput = console.nextLine();
                bigDecimalInput = new BigDecimal(stringInput);
                invalidInput = false;
            } catch (NumberFormatException e) {
                //If it explodes, it'll go here and do this.
                this.print("Error. Please enter a number.");
            }
        }
        return bigDecimalInput;
    }

}
