package src.main.java.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Change {

    public static BigDecimal changeDutInPennies(BigDecimal itemCost, BigDecimal money) {
        BigDecimal changeDueInPennies = (money.subtract(itemCost)).multiply(new BigDecimal("100"));
        System.out.println("Change due: $" + (changeDueInPennies));
        return changeDueInPennies;
    }

    public static Map<BigDecimal, BigDecimal> changeDuePerCoin (BigDecimal itemCost, BigDecimal money) {
        Coin[] coinEnumArray = Coin.values();
        ArrayList <String> coinStringList = new ArrayList<>();
        for (Coin coin : coinEnumArray) {
            coinStringList.add(coin.toString());
        }

        ArrayList<BigDecimal> coins = new ArrayList<BigDecimal>();
        for (String coin : coinStringList) {
            coins.add(new BigDecimal(coin));
        }

        BigDecimal changeDueInPennies = changeDutInPennies(itemCost, money);
        // Calculate the number of quarters, dimes, nickels and pennies
        // for returning to the user
        BigDecimal noOfCoins;
        BigDecimal zero = BigDecimal.ZERO;
        // Map <coin, noOfCoins
        Map <BigDecimal, BigDecimal> amountPerCoin = new HashMap<>();

        // for every coin in the array:
        for (BigDecimal coin : coins) {
            //if the change is greater than or equal to the coin amount
            if (changeDueInPennies.compareTo(coin) >= 0) {
                if (!changeDueInPennies.remainder(coin).equals(zero)) {
                    //the number of coins of coin[i] required will be the floor division of change amount/coin
                    noOfCoins = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    // add coin type
                    amountPerCoin.put(coin, noOfCoins);
                    // remaining amounts is updated
                    changeDueInPennies = changeDueInPennies.remainder(coin);
                    if (changeDueInPennies.compareTo(zero) < 0){
                        break;
                    }
                    //if the change divided by the coin is an exact number/integer
                } else if (changeDueInPennies.remainder(coin).equals(zero)) {
                    noOfCoins = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    amountPerCoin.put(coin, noOfCoins);
                    // if change less than or equal to zero, stop
                    if ((changeDueInPennies.compareTo(zero)) < 0) {
                        break;
                    }
                }
            } else {
                ;
            }
        } // loop end here

        return amountPerCoin;
    }
}
