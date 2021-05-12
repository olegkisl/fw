package blocks;

/**
 * Background: Fractal structure
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRA_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A3_BCGRA_0() {
        super("A3_BCGRA_0", 0, 40);
        // set_param();
    }
    private double[] uuu = {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1,1,0};
    private int divider=10;
    private void set_param() {
        for (int i = 0; i < uuu.length; i++) {
            uuu[i] = params[i] + params[i + 1];
        }
        divider=(int)(4+(uuu.length-6)*params[15]);
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
    }

    private double ptrn1(double x, double y, double size, int j) {
        int nx = 0, ny = 0;
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        nx = (int) (x * (size));
        if (nx < 0) {
            nx = 0;
        }
        ny = (int) (y * (size));
        if (ny < 0) {
            ny = 0;
        }
        j = nx * ny + nx * j + 1;
        int res = (nx + ny + j) % divider;
        if(res<0) res=0;
        return uuu[res];
    }

    public double f(double x, double y) {
//   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
//   if(g == null) return 0.5;
//   double t = g.f(x,y), xx, yy, tt;
        double res = 1.0;
        double cc = 1.0;

        double rrr = 1;
        for (int j = 0; j < 7; j++) {
            //cc = cc * 2.0;
            double x1 = x;
            double y1 = y;
            double h = ptrn1(x1, y1, cc, j);
            rrr *= h; 
            x = y1 * 2.5 * (0.55 + params[16]) + params[11] * Math.sin(10*params[12] * y);
            y = x1 * 2.5 * (0.55 + params[17]) + params[13] * Math.sin(10*params[14] * x);

        }
        if (rrr < 0) {
            rrr = 0;
        }
        if (rrr > 5) {
            rrr = 5;
        }
        rrr = Math.pow(rrr, 8 * params[9]);
        if (params[10] > 0.5) {
            rrr =1-rrr;
        }
        return rrr;
    }
}

