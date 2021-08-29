package chapter3;

import chapter3.strings.StringUtilKt;

import java.util.ArrayList;


public class UseKtMethod {
    ArrayList<Integer> arrayList;

    public void useExtProperty() {
        StringUtilKt.getLastChar("Java");
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public String useKtMethod() {
        return StringFunctions.joinToString(arrayList, "");
    }

    public void interoperateKotlin() {
        Main.Companion.getTest();
        TestObject.INSTANCE.getTest();
        MainKt.getUnix0();
        String str = MainKt.UNIX_LINE_SEPARATOR;
    }

    public void callKotlinExtendFun() {
        char c = StringUtilKt.lastChar("Java");
        System.out.println(c);
    }

    public void extWithoutOverride() {
        View view = new Button();
        ExtensionsKt.showOff(view);
    }
}
