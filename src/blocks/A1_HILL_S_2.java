
/*
 * Sequence of obgects in X direction
 */
package blocks;

import fw2003.*;

public class A1_HILL_S_2 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    static final double high = 1.5;
    static final double high0 = 0.0;
    static final double fr = 8.0;
    public A1_HILL_S_2() {
        super("A1_HILL_S_2", 2, 25);
    }

    private double fu(double x) {
        double a1 = Math.cos(params[1] + x * (params[0] + 0.1));
        double a2 = Math.cos(params[3] + x * params[2] * 2.5);
        double a3 = Math.cos(params[5] + x * params[4] * 1.5);
        double x1 = x + a2 + a3;
        double x2 = x + a1 * a1 + a3;
        double y1 = Math.cos(fr*x1 * params[6]);
        double y2 = Math.cos(fr*x2 * params[7]);
        y2 = y2 * y2;
        return high0 * params[8] + high * params[9] * y1 + high * params[10] * y2;
    }

    private double fd(double x) {
        double a1 = Math.cos(params[11] + x * (params[12] + 0.1));
        double a2 = Math.cos(params[13] + x * params[14] * 2);
        double a3 = Math.cos(params[15] + x * params[16] * 3);
        double x1 = x + a1 + a2 + a3;
        double x2 = x + a1 * a1 + a3;
        double y1 = Math.cos(fr*x1 * params[17]);
        double y2 = Math.cos(fr*x2 * params[18]);
        y2 = y2 * y2;
        return high0 * params[8] + high * params[19] * y1 + high * params[20] * y2;
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        Block_Prototype_Fxy h = (Block_Prototype_Fxy) sons[1];
        if (g == null || h == null) {
            return 0.5;
        }
        //double c = params[9];
        double a = fu(x);
        double b = fd(x);
        if (b > a) {
            return g.f(x, y);
        }
        if (y >= a) {
            return g.f(x, y);
        }
        if (y <= b) {
            return g.f(x, y);
        }
        double r = 1.0 - (a - y) / (a - b);
        if (params[23] > 0.7) {
            double ry = r * r;
            return h.f(x, ry);
        }
        if (params[24] > 0.7) {
            double ry = r * r;
            double y1 = b + ry * (a - b);
            double bright = r - 1;
            return bright * g.f(x, y1);
        }
        double ry = r * r;
        double y1 = b + ry * (a - b);
        double bright = r - 1;
        return bright * h.f(x, y1) + (1 - bright) * g.f(x, y1);
    }
}
