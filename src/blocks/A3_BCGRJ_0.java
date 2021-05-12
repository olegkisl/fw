package blocks;

/**
 * PseudoTree
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRJ_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_BCGRJ_0() {
        super("A3_BCGRJ_0", 0, 40);
        // set_param();
    }
  /*  int high = 5;
    int width = 3;
    int layers = 6;
    int parts = 5;
    private int[] mask = {1, 1, 1, 1, 1,
        1, 1, 1, 1, 1,
        1, 1, 0, 1, 1,
        1, 1, 1, 1, 1,
        1, 1, 1, 1, 1,
        1, 1, 1, 0, 1,
        1, 1, 1, 1, 1,
        1, 1, 1, 1, 1,};
    private double[] uuu = {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0};
    private int divider = 10;

    private void set_param() {
        for (int i = 0; i < 35; i += 2) {
            mask[i] = 1;
            if (params[i] > 0.75) {
                mask[i] = 0;
            }
        }

        for (int i = 0; i < uuu.length; i++) {
            //if(params[i]>0.5) uuu[i]=1;
            // else uuu[i]=0;
            uuu[i] = params[i] * 2.5;
        }
        divider = (int) (4 + (uuu.length - 6) * params[15]);
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
    }*/

////////////////////////////////////////////////// 
    private double res(double x, double y) {
        if (y < 0) {
            return -1;
        }
        double r = 1 - x * x*(1+y/(params[1]*50)+1);
        if (r < 0) {
            r = -1;
        }

        return r;
    }


    private double ya(double x, double y, double a) {
        return y;
    }

    private double xb(double x, double y, double a1, double a2, double b, double c) {
        if (y < a1) {
            return x;
        }
        if (y < a2) {
            double x1 = y - a1;
            return x - b * x1 * (1 + params[12] * Math.sin((0.1 + params[11]) * x));
        }
        double x1 = y - a1;
        double x2 = y - a2;
        return x - b * x1 * (1 + params[12] * Math.sin((0.1 + params[11]) * x)) + c *params[11]* x2;

    }

    public double f(double x, double y) {
        double cc = 10.0;
        double rrr = 0.5, rrr1;
        x=cc*x;
        y=cc*y;
        rrr = res(x, y);
        for (int j = 0; j < 10; j++) {
            double u = ((j % 2) - 0.5) * 3 * params[j];
            double u1 = ((j % 2) - 0.5);
            rrr1 = res(xb(x, y, j * 5, j * 5 + 4, u, u1), ya(x, y, 0));
            if (rrr1 > rrr) {
                rrr = rrr1;
            }
            rrr1 = res(xb(x, y, j * 5, j * 5 + 4, u, 0), ya(x, y, 0));
            if (rrr1 > rrr) {
                rrr = rrr1;
            }
        }
        if (rrr < -0.5) {
            rrr = 0;//ptrn3(x,y,2,1);
        }
        if (rrr > 1) {
            rrr = 1;
        }
        if (rrr < 0) {
            rrr = 0;
        }
        if (params[10] > 0.75) {
            rrr = 1 - rrr;
        }
        return rrr;
    }
}

