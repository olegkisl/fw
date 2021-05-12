package blocks;

/**
 * Vertical nonlinear shifts
 *
 * @author okislyuk
 */
import fw2003.*;

public class A1_TRANSZ_SURF extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    static final double fr1 = 2;
    static final double fr2 = 3;
    static final double fr3 = 5;
    static final double fr4 = 2;
    static final double fr5 = 2;
    static final double high0 = 2.0;

    public A1_TRANSZ_SURF() {
        super("A1_TRANSZ_SURF", 1, 25);
    }

    private double dy1(double x, double y) {
        double a1 = Math.sin(fr1 * x * params[11]);
        double a2 = Math.sin(fr1 * x * params[12]);
        double a3 = Math.sin(fr1 * x * params[13]);

        double a4 = Math.sin(fr2 * x * params[12] + params[0] * y + Math.sin(x * params[1]));

        return (a1 + a2 + a3) * a4;
    }

    private double dy2(double x, double y) {
        double a2 = Math.sin(fr3 * y * params[16] + fr4 * params[2] * x + params[17] * Math.sin(y * params[1]));
        return a2;
    }

    private double dx1(double x, double y) {
        double a2 = Math.sin(params[5] * fr5 * x + params[18] * Math.sin(y * params[6]));
        return a2;
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double dy1 = dy1(x, y);
        double dy2 = dy2(x, y);
        double dx1 = dx1(x, y);
        double clr = 1.0;
        if (dy2 < 0) {
            return g.f(x, y);
        }
        if (dy1 > 0 && dy1 < 1) {
            clr = dy1;
        }
        double z = g.f(x, y + params[9] * dy1 + params[10] * dy2);
        double res = z * clr;//+params[8]*(1.0-clr); 
        return res;
    }
}
