package chapter3.part2;

public class Condition {

    public void caseSwitch() {
        var score = 100;
        var quotient = score / 10;
        var level = '\0'; // 8 radix Unicode

        switch (quotient) {
            case 10:
            case 9:
                level = 'A';
                break;
            case 8:
                level = 'B';
                break;
            case 7:
                level = 'C';
                break;
            case 6:
                level = 'D';
                break;
            default:
                level = 'E';
        }

        System.out.println(level);

        // java SE 14
        var level2 = switch (quotient) {
            case 10, 9 -> 'A';
            case 8 -> 'B';
            case 7 -> 'C';
            case 6 -> 'D';
            default -> 'E';
        };

        System.out.println(level2);

        // java SE 14 with block
        score = 70;
        quotient = score / 10;
        var level3 = switch (quotient) {
            case 10, 9 :
                yield 'A';
            case 8 :
                yield 'B';
            case 7 :
                 System.out.print("1");
            case 6 :
                yield 'D';
            default :
                yield 'E';
        };

        System.out.println(level3);

        // -> , yield
        score = 44;
        quotient = score / 10;
        var level4 = switch (quotient) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> {
                String message = "E" + " 不及格了";
                yield message;
            }
        };

        System.out.println(level4);


        // Alger test
        score = 100;
        quotient = score / 10;
        switch (quotient) {
            case 10, 9 -> System.out.println("yo man");
            case 8 -> level ='B';
            case 7 ->level = 'C';
            case 6 -> level ='D';
            default -> {
                System.out.println("haha");
            }
        };
    }
}
