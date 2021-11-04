package chapter3.part1;

public class Variable {
    double score2;
    final double PI = 3.14159;

    void underLineAndBinary() {
        int number1 = 123_456;
        double num2 = 3.141_592_653;
        System.out.println(number1);
        System.out.println(num2);

        // binary
        int mask1 = 0b1010;
        int mask2 = 0b0000_0001;
        System.out.println(mask1);
        System.out.println(mask2);
    }

    void unicodeHello() {
        System.out.println("\u0048\u0065\u006C\u006C\u006F");
    }

    void literalConstant() {
        int number1 = 12;
        int number2 = 0xC;
        int number3 = 014; // 8 進位

        double float1 = 0.00123;
        double float2 = 1.23e-3;

        char size = 's';
        char lastName = '林';
        char symbol = '\'';
    }

    void localVariable() {
        double local;
        System.out.println(score2);
        // need init value
//        System.out.println(local);
    }

    void caseFinal() {
//        PI = 3.14;
    }
}
