package chapter15.regex;


public class RegexMethod {

    public void cellPhoneInTaiwan() {
        String[] numberList = {"09123456789", "30912345678", "0112345678", "0912345678"};
        String pattern1 = "09[0-9]{8}";
        String pattern2 = "^09[0-9]{8}";
        String pattern3 = "09[0-9]{8}$";
        for (var num : numberList) {
            System.out.println(num + " is legal cellphone with pattern1: " + num.matches(pattern1));
            System.out.println("pattern1: " + num.replaceAll(pattern1, " phone "));
            System.out.println("pattern2: " + num.replaceAll(pattern2, " phone "));
            System.out.println("pattern3: " + num.replaceAll(pattern3, " phone "));
        }
    }

    public void extensionNotation() {
        String str = "Justin Lin, Monica Huang";
        System.out.println(str.replaceAll("\\w+(?= Lin)", "Irene"));

        String str2 = "data-h1, cat-address, data-pre";
        System.out.println(str2.replaceAll("(?<=data)-\\w+", "xxx"));
    }

    public void border() {
        String str = "Justin dog Monica doggie Irene";
        for (var s : str.split("dog")) {
            System.out.println(s.trim());
        }
        System.out.println("-------");

        for (var s : str.split("\\bdog\\b")) {
            System.out.println(s.trim());
        }
    }
    
    public void quantifier() {
        String[] regexs = {".*foo", ".*?foo", "x*+foo", ".*+foo"};
        for(var regex: regexs) {
            System.out.println("xfooxxxxxxfoo".replaceAll(regex, "Orz"));
        }
    }

    public void blank() {
        String str = "Justin\rMonica\rIrene\rBush";
        System.out.println(str);
        System.out.println("-------");

        // for blank character
        String[] items = str.split("\\s");
        for (var item: items) {
            System.out.println(item);
        }
    }

    public void characterClass() {
        String str = "Justin1Monica2Irene3Bush";
        String[] items = str.split("[123]");
        for (var item: items) {
            System.out.println(item);
        }
    }

    public void backslash() {
        String str = "Justin\\Monica\\Irene";
        System.out.println(str);
        String[] items = str.split("\\\\");
        for (var item: items) {
            System.out.println(item);
        }
    }

    public void vertical2() {
        String[] items = "Justin||Monica||Irene".split("\\|\\|");
        for (var item: items) {
            System.out.println(item);
        }
    }

    public void plus() {
        String[] items = "Justin+Monica+Irene".split("\\+");
        for (var item: items) {
            System.out.print(item);
        }
    }

    public void newLine() {
        String[] names =  "Justin\nMonica\nIrene".split("\n");
        for (var name: names) {
            System.out.print(name);
        }
    }

    public void dot() {
        String[] items = "12.345-6.A".split("\\.");
        for (var item: items) {
            System.out.print(item);
        }
    }
}
