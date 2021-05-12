package blocks;

import fw2003.*;

// Polynom
public class A1_POLIA_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_POLIA_0() {
        super("A1_POLIA_0", 0, 30);
    }
    double val = -0.5;
    double min = 0;
    double max = 2.0;

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
        double ay1 = params[7] * y3 + params[8] * y2 + params[9] * y + params[12];
        double axy = params[13] * y * x3 + params[14] * y3 * x + params[15] * y2 * x2 + params[16] * y3 * x2 + params[17] * y2 * x3;
        double c1 = axy / (ax * ax + ay * ay + params[21] + 0.1);
        double c2 = ax + ay / (ax1 * ax1 + ay1 * ay1 + params[22] + 0.1);
        double c3 = ax * ay1 * params[23] + ax1 * ay * params[24];
        double c4 = axy * (ax + 0.1) * (ay + 0.1);
        double r = params[25] * c1 + params[26] * c2 + params[27] * c3 + params[28] * c4;
        if (r > min && r < max) {
            return r;
        }
        return val;
    }
}