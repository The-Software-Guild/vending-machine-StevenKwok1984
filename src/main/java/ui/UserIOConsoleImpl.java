package src.main.java.ui;
import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    final private Scanner console = new Scanner(System.in);

    /**
     * A very simple method that takes in a message to display on the console
     * and then waits for a integer answer from the user to return.
     *
     * @param msg - String of information to display to the user.
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and then waits for an answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as string
     */
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter an integer
     * to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @return the answer to the message as integer
     */
    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(prompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    /**
     * A slightly more complex method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter an integer
     * within the specified min/max range to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @param min    - minimum acceptable value for return
     * @param max    - maximum acceptable value for return
     * @return an integer value as an answer to the message prompt within the min/max range
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a long
     * to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @return the answer to the message as long
     */
    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a double
     * within the specified min/max range to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @param min    - minimum acceptable value for return
     * @param max    - maximum acceptable value for return
     * @return an long value as an answer to the message prompt within the min/max range
     */
    @Override
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a float
     * to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @return the answer to the message as float
     */
    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a float
     * within the specified min/max range to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an float value as an answer to the message prompt within the min/max range
     */
    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a double
     * to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @return the answer to the message as double
     */
    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a double
     * within the specified min/max range to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @param min    - minimum acceptable value for return
     * @param max    - maximum acceptable value for return
     * @return an double value as an answer to the message prompt within the min/max range
     */
    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        } while (result < min || result > max);
        return result;
    }

    /**
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
                bigDecimalInput = new BigDecimal(stringInput); // if its not a number, it'll break
                invalidInput = false;
            } catch (NumberFormatException e) {
                //If it explodes, it'll go here and do this.
                this.print("Error Please enter a number.");

            }
        }
        return bigDecimalInput;
    }

    /**
     * A slightly more complex method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a BigDecimal
     * within the specified min/max range to be returned as the answer to that message.
     *
     * @param prompt - String explaining what information you want from the user.
     * @param min    - minimum acceptable value for return
     * @param max    - maximum acceptable value for return
     * @return an double value as an answer to the message prompt within the min/max range
     */


    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal result = new BigDecimal(prompt);
        boolean between = isBetween(result, min, max);
        while(!between) {
            result = new BigDecimal(prompt);
            between = isBetween(result, min, max);
        }

        return result;
    }
    boolean isBetween(BigDecimal num, BigDecimal min, BigDecimal max){
        return num.compareTo(min) > 0 && num.compareTo(max) < 0;
    }




}

