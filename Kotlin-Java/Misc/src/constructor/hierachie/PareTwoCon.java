package constructor.hierachie;

import javax.naming.Context;
import javax.swing.text.AttributeSet;

public class PareTwoCon {
    private Context ctx;
    private AttributeSet attr;

    PareTwoCon(Context ctx) {
        this.ctx = ctx;
    }

    PareTwoCon(Context ctx, AttributeSet attr) {
        this.ctx = ctx;
        this.attr = attr;
    }

    public PareTwoCon() {
        System.out.println("no argument constructor.hierachie.PareTwoCon()");
    }
}
