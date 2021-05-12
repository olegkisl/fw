package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A1_GROW_4_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    double sn = 0.5;
    double cs = 0.5;

    public A1_GROW_4_1() {
        super("A1_GROW_4_1", 1, 5);
    }

    private double fff(double a, double b) {
        if (params[1] < 0.5) {

            if (a > 0 && b > 0) {
                return (a + b) * 0.5;
            }
            if (a > 0) {
                return a;
            }
            if (b > 0) {
                return b;
            }
            return b;
        } else {
            if (a > 0) {
                return a;
            }
            return b;
        }
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double x1, y1;
        double a = 1 + params[2];
        double b = 0.5 + 0.5 * params[3];
        double c = 0.8 + 0.2 * params[4];
        x1 = x + 0.1 - 0.1*params[0];
        y1 = y;
        double bright=1.0;
        double sum = g.f(x1, y1);
        for (int n = 0; n < 3; n++) {
            x1 = -x1 * a;
            y1 = (y1 - b) * a;
            bright = bright*c;
            double c1= g.f(x1, y1)*bright;
            sum = fff(c1, sum);
        }
        return sum;
    }
}
