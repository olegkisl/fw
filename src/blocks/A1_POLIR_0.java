package blocks;

import fw2003.*;

// Polynom
public class A1_POLIR_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_POLIR_0() {
        super("A1_POLIR_0", 0, 30);
    }
    double val = -0.5;
    double min=0;
    double max=2.0;

    public double f(double x, double y) {
        double x2 = x * x;
        double x3 = x2 * x;
        double x4 = x3 * x;
        double x5 = x4 * x;
        double x6 = x5 * x;
        double y2 = y * y;
        double y3 = y2 * y;
        double y4 = y3 * y;
        double y5 = y4 * y;
        double y6 = y5 * y;
        double ax = params[18] * x6 + params[0] * x5 + params[1] * x4;
        double ax1 = params[2] * x3 + params[3] * x2 + params[4] * x + params[11];
        double ay = params[19] * y6 + params[5] * y5 + params[6] * y4;
        double ay1 =params[7] * y3 + params[8] * y2 + params[9] * y + params[12];
        double axy = params[13] * y * x3 + params[14] * y3 * x + params[15] * y2 * x2 + params[16] * y3 * x2 + params[17] * y2 * x3;
        double r = params[21] *ax*ay +params[22] *ax1 + params[23] *ay1+   params[24] *axy/(ax*ax+ 1)+ params[25] *ax*ay1*ax1;
        if (r > min && r<max) {
            return r;
        }
        return val;
    }
}