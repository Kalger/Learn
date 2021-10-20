package chapter3.part2;

public class Loop {
    public void caseFor() {
        for (var j= 1; j < 10; j++) {
            for (var i = 2; i < 10; i++) {
                System.out.printf("%d*%d=%2d ", i, j, i * j);
            }
            System.out.println();
        }

        // ninja code for multiplication table
        System.out.println("ninja code below this line%n");

        for (int i = 2, j = 1; j < 10; i = (i==9)?((++j/j)+1):(i+1)) {
            System.out.printf("%d*%d=%2d%c", i, j, i*j, (i == 9 ? '\n':' '));
        }
    }
}
