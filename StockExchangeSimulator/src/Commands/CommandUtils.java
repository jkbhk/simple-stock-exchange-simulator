package Commands;

import java.math.BigDecimal;

public class CommandUtils {

    public static boolean isValidCurrencyAmount(String s) {

        if (s.charAt(0) != '$' || s.equals("")) {
            return false;
        }

        double temp;

        try {
            temp = Double.parseDouble(s.substring(1, s.length()));
        } catch (NumberFormatException nfe) {
            return false;
        }

        return BigDecimal.valueOf(temp).scale() <= 2;
    }

    public static BigDecimal getAmountFromString(String s) {
        try {
            return new BigDecimal(s.substring(1, s.length()));
        } catch (Exception e) {
            return new BigDecimal("0.00");
        }

    }
}
