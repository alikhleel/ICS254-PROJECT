import java.math.BigInteger;

public class Helper {
    static long exponentMod(long base, long exp, long m) {
        BigInteger big1, big2, big3, result;
        big1 = BigInteger.valueOf(base);
        big2 = BigInteger.valueOf(exp);
        big3 = BigInteger.valueOf(m);
        //perform modPow operation on big1 using big2 and exp
        result = big1.modPow(big2, big3);
        return result.longValue();
    }

    public static String letterToDecimal(char letter) {
        int value = (int) letter;
        return String.format("%03d", value); // add leading zeros
    }

    public static char decimalToLetter(int letter) {
        return (char) letter;
    }
}
