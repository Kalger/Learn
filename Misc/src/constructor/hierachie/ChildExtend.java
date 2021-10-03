package constructor.hierachie;

import javax.naming.Context;
import javax.swing.text.AttributeSet;

public class ChildExtend extends PareTwoCon{

    static String aStatic = "static";

    ChildExtend(String ctx) {
//        super(ctx);
    }

    ChildExtend(Context ctx, AttributeSet attr) {
        super(ctx, attr);
    }

}
