package chapter3.part1;

import java.math.BigDecimal;

public class Range {
    public static void main(String[] args) {
        System.out.printf("%d ~ %d%n", Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.printf("%d ~ %d%n", Short.MIN_VALUE, Short.MAX_VALUE);
        System.out.printf("%d ~ %d%n", Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("%d ~ %d%n", Long.MIN_VALUE, Long.MAX_VALUE);

        System.out.printf("%d ~ %d%n", Float.MIN_EXPONENT, Float.MAX_EXPONENT);
        System.out.printf("%d ~ %d%n", Double.MIN_EXPONENT, Double.MAX_EXPONENT);

        System.out.printf("%h ~ %h%n", Character.MIN_VALUE, Character.MAX_VALUE);

        System.out.printf("%b ~ %b%n", Boolean.TRUE, Boolean.FALSE);

        // format
        System.out.printf("example: %.2f%n", 19.235);
        // limit float width = 8
        System.out.printf("example:%8.2f%n", 19.235);
    }
}
