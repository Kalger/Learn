package chapter3.part2;

public class BreakContinue {
    public void breakWithLabel() {
        BACK : {
            for (var i = 0; i< 10; i++) {
                if (i == 9) {
                    System.out.println("break");
                    break BACK;
                }
            }
            System.out.println("test");
        }
    }

    public void continueWithLabel() {
        BACK1:
        for (var i = 0; i < 4; i++) {
            BACK2:
            for (var j = 0; j < 4; j++) {
                if (j == 2) {
                    continue BACK1;
                }
                System.out.printf("inner j = %d when i = %d%n", j, i);
            }
            System.out.println("outer");
        }
    }
}
