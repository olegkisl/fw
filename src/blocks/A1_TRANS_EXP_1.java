package blocks;

/**
 * Horizontal nonlinear shifts
 *
 * @author okislyuk
 */
import fw2003.*;

public class A1_TRANS_EXP_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_TRANS_EXP_1() {
        super("A1_TRANSY_1", 1, 15);
    }

    private double fff(double x, double y) {
        double x2 = x * x;
        double x3 = x2 * x;
        double x4 = x3 * x;
        double x5 = x4 * x;
        double x6 = x5 * x;
        double ax = params[8] * x6 + params[0] * x5 + params[1] * x4;
        double ax1 = params[2] * x3 + params[3] * x2 + params[4] * x + params[10];
        return y + ax1 + ax;
    }

    private double fff1(double x, double y) {
        double x2 = x * x;
        double x3 = x2 * x;
        double x4 = x3 * x;
        double x5 = x4 * x;
        double x6 = x5 * x;
        double ax = params[8] * x6 + params[0] * x5 + params[1] * x4;
        double ax1 = params[2] * x3 + params[3] * x2 + params[4] * x + params[10];
        return y + ax1 * Math.cos(ax);
    }

    private double fff2(double x, double y) {
        double x2 = x * x;
        double x3 = x2 * x;
        double x4 = x3 * x;
        double x5 = x4 * x;
        double x6 = x5 * x;
        double ax = params[8] * x6 + params[0] * x5 + params[1] * x4;
        double ax1 = params[2] * x3 + params[3] * x2 + params[4] * x + params[10];
        return y + ax1 /(1.1+Math.cos(ax));
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double c = params[1];
        if (c < 0.5) {
            return g.f(x, fff(x, y));
        }
        if (c < 0.75) {
            return g.f(x, fff1(x, y));
        };
        return g.f(x, fff2(x, y));
    }
}
