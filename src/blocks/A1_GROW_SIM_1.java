package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A1_GROW_SIM_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    double sn = 0.5;
    double cs = 0.5;

    public A1_GROW_SIM_1() {
        super("A1_GROW_SIM_1", 1, 3);
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
        x+=-0.2+0.4*params[0];
        double a = g.f(x, y);
        double b = g.f(-x, y);
        return fff(a,b);
    }
}

