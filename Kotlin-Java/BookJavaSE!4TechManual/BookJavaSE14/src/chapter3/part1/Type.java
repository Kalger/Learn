package chapter3.part1;

public class Type {

    void varLocalInfer() {
        var age = 10;
        var pi = 3.14;
        var upper = 10000000000000L;
        var tau = 3.1415F;
        var isLower = true;
    }

    void conversion() {
        double PI = 3.14;
        float PI2 = 3.14f;
        long num = 2147483648L;
//        long num2 = 2147483648;
        short a = 1;
        short b = 2;
        short c = (short) (a + b);
    }
}
