package constructor;

import kotlin.jvm.internal.DefaultConstructorMarker;

public class ConstructorMain {
    public static void main(String[] args) {
        new HasANoParameterConstructor();
        // without no-parameter constructor
//        new WithoutNoParam();
    }
}
