package blocks;

/**
 * Vertical nonlinear shifts
 *
 * @author okislyuk
 */
import fw2003.*;

public class A1_TRANS_GROW_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_TRANS_GROW_1() {
        super("A1_TRANS_GROW_1", 1, 5);
    }

    private double fff(double x, double y) {
        double a1 = 0.01 + 0.3 * params[1] + x * x * params[2];
        double a2 = 1.0 + x * x * params[2];
        return y * (a1 / a2);
    }

    private double fff1(double x, double y) {
        double a1 = 0.01 + 0.3 * params[1] + x * x * params[2];
        double a2 = 1.0 + x * x * params[2];
        if (y > 0) {
            return y * (a1 / a2);
        }
        return y;
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double c = params[3];
        if (c < 0.5) {
            return g.f(x, fff(x, y));
        }
        return g.f(x, fff1(x, y));
    }
}
