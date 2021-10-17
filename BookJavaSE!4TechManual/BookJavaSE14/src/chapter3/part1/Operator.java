package chapter3.part1;

public class Operator {

    public void caseRadix10To2() {
        byte num = -128;
        String s1 = String.format("%8s", Integer.toBinaryString(num & 0xFF)).replace(' ', '0');
        System.out.println(s1);
    }

    public void caseShift() {
        int number = 1;
        System.out.printf("2 的 0 次方: %d%n", number);
        System.out.printf("2 的 1 次方: %d%n", number << 1);
        System.out.printf("2 的 2 次方: %d%n", number << 2);
        System.out.printf("2 的 3 次方: %d%n", number << 3);

        int num = -128;
        System.out.printf("%d / 2 = %d%n", num, num >> 1);
        int num2 = Integer.MIN_VALUE;
        System.out.println((num2 >>> 1));
    }

    public void caseBitwise() {
        System.out.println("AND");
        System.out.printf("0 & 0%5d%n", 0 & 0);
        System.out.printf("0 & 1%5d%n", 0 & 1);
        System.out.printf("1 & 0%5d%n", 1 & 0);
        System.out.printf("1 & 1%5d%n", 1 & 1);

        System.out.println("OR");
        System.out.printf("0 | 0%5d%n", 0 | 0);
        System.out.printf("0 | 1%5d%n", 0 | 1);
        System.out.printf("1 | 0%5d%n", 1 | 0);
        System.out.printf("1 | 1%5d%n", 1 | 1);

        System.out.println("XOR");
        System.out.printf("0 ^ 0%5d%n", 0 ^ 0);
        System.out.printf("0 ^ 1%5d%n", 0 ^ 1);
        System.out.printf("1 ^ 0%5d%n", 1 ^ 0);
        System.out.printf("1 ^ 1%5d%n", 1 ^ 1);

        // 00000001 -> 11111110
        System.out.println("Complement");
        byte number = 0;
        System.out.printf("~0%5d%n", ~number);

    }

    public void ternary() {
        int num = 7;
        System.out.printf("%d is even? %c%n", num,(num % 2 == 0) ? 'Y' : 'N');
    }

    public void compareToBoolean() {
        System.out.printf("10 > 5: %b%n", 10 > 5);
    }
}
