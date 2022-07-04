package src.main.java.ui;

import java.math.BigDecimal;

public class TestUserIO {

    public static void main(String[] args) {
        UserIO userIO = new UserIOConsoleImpl();
        BigDecimal smallNum = userIO.readBigDecimal("Give me a small number :");
        BigDecimal bigNum = userIO.readBigDecimal("Now give me a much bigger number! :");
        if(bigNum.compareTo(smallNum) < 0 ){
            userIO.print("Hey! " + smallNum +" is BIGGER than " + bigNum);
            userIO.print("I guess I can fix it.");
            BigDecimal swapNum = bigNum;
            bigNum = smallNum;
            smallNum = swapNum;
        }

        BigDecimal betweenNum = userIO.readBigDecimal("Now give me one in between! : ", smallNum, bigNum);
        userIO.print("I like the number "+betweenNum+"!");
    }
}
