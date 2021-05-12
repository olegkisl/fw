
package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_XY2XYA_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_XY2XYA_1() {
        super("A3_XY2XYA_1", 1, 40);
        // set_param();
    }
    int high = 5;
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
    }

////////////////////////////////////////////////// 

     private double xa(double x, double y) {
        double x1 = x * 2.5 * (0.55 + params[16]) +(0.5+ params[11]) * y*Math.sin(1+4*params[12] * y);
        return (x1);
    }

    private double ya(double x, double y) {
        double y1 =  y * 2.5 * (0.55 + params[17]) +(0.5+params[13]) * x*Math.sin(1+4*params[14] * x);
        return (0.5 + params[15]) * Math.cos(y1);
    }
    
    private double xb(double x, double y) {
        return x + params[17]*ptrn1(x, y,  30, 1);
    }

    private double yb(double x, double y) {
        return  y + params[19]*ptrn1(x, y,  30, 4);
    }

    ////////////////////////////////////////////////  
    private double x1(double x, double y) {
        return x + y*params[17]*ptrn2(x, y,  30, 1);
    }

    private double y1(double x, double y) {
        return y + x*Math.sin( params[17]*ptrn2(x, y,  30, 4)); 
    }

    private double x2(double x, double y) {
        double a = x*(y*y*params[3]+10*params[1])/(y*y*params[4]+10*params[5]+0.001);
        return x+a;
    }

    private double y2(double x, double y) {
        double a = y*(x*x*params[13]+10*params[11])/(x*x*params[14]+10*params[15]+0.001);;
        return y+a;
    }

    private double ptrn1(double x, double y, double size, int j) {
        int nx = 0, ny = 0;
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        nx = (int) (x * (alfa * size));
        if (nx < 0) {
            nx = 0;
        }
        ny = (int) (y * (alfa * size));
        if (ny < 0) {
            ny = 0;
        }
        j = nx * (nx + ny) + ny * j + 1 + j;
        int res = (nx + ny + j) % divider;
        if (res < 0) {
            res = 0;
        }
        return uuu[res];
    }

    private double ptrn2(double x, double y, double size, int j) {
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        double x1 = 0.5 * j + params[21] * x * (alfa * size);
        double y1 = 0.5 * j + params[23] * x * (alfa * size);
        int nx = 0, ny = 0;
        nx = (int) (x1);
        ny = (int) (y1);
        j = nx * (nx + ny) + ny * j + 1 + j;
        int r = (nx + ny + j) % divider;
        if (r < 0) {
            r = 0;
        }
        ////
        double xx = Math.sin(3.1459 * x1);
        double yy = Math.sin(3.1459 * y1);
        double res = xx + yy;
        if (res < 0) {
            res = 0;
        }
        if (res > 1) {
            res = 1;
        }
        ////
        return res + uuu[r];
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double cc = 1.0;
        double rrr = 0.5, rrr1;
        if (params[13] > 0.75) {
            rrr = g.f(x1(x, y), y1(x, y));
        }else if (params[13] > 0.5) {
            rrr = g.f(xa(x, y), ya(x, y));
        } else if (params[13] > 0.25) {
             rrr = g.f(xb(x, y), yb(x, y));
        } else{
            rrr = g.f(x2(x, y), y2(x, y));
        }
        //rrr = 1.5*fff(x,  y, 10)*ptrn3(xa(x, y), ya(x, y),100,3);
        if (params[10] > 0.75) {
            rrr = 1 - rrr;
        }
        return rrr;
    }
}

