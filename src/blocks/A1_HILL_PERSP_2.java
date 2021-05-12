package blocks;

import fw2003.*;

public class A1_HILL_PERSP_2 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_HILL_PERSP_2() {
        super("A1_HILL_PERSP_2", 2, 20);
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        Block_Prototype_Fxy h = (Block_Prototype_Fxy) sons[1];
        if (g == null || h == null) {
            return 0.5;
        }

        double a1 = 4.0 + 4 * params[3];
        double b1 = 2.0 + 2 * params[4];
        if (y < 0.0001 && y > (-a1)) {
            y = -y;
            double y1 = (a1 - y) * b1 / y;
            double c = (b1 + y1) / b1;
            double x1 = c * x;
            double bright = (a1 + y) / (a1 + (1.0 + 0.3 * params[5]) * y);
            return bright * g.f(x1, y1);
        }
        if (y <= (-a1)) {
            return g.f(x, 0.0);
        }

        double a2 = 4.0 + 4 * params[1];
        double b2 = 2.0 + 2 * params[2];
        if (y > 0.0001 && y < a2) {
            double y1 = (a2 - y) * b2 / y;
            double c = (b2 + y1) / b2;
            double x1 = c * x;
            return h.f(x1, y1);
        }
        if (y > a2) {
            return h.f(x, 0.0);
        }
        return 0.0;
    }
}
