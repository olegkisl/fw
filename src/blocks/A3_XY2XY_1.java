
package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_XY2XY_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_XY2XY_1() {
        super("A3_XY2XY_1_0", 1, 40);
        // set_param();
    }
  

////////////////////////////////////////////////// 

    private double xa(double x, double y) {
        double x1 = params[1] * Math.sin(params[2] * x) + params[3] * Math.cos(params[4] * y) + x;
        return (0.5 + params[5]) * Math.cos(x1);
    }

    private double ya(double x, double y) {
        double y1 = params[11] * Math.sin(params[12] * x) + params[13] * Math.cos(params[14] * y) + y;
        return (0.5 + params[15]) * Math.cos(y1);
    }

    private double xb(double x, double y) {
        return x * Math.sin(params[11] * y);

    }

    private double yb(double x, double y) {
        return x * Math.cos(params[12] * y);
    }
    
    private double x1(double x, double y) {
        double a = params[12] + 0.5;
        return Math.sqrt( a * y*y + params[25] *x*x );
    }

    private double y1(double x, double y) {
        if(x<0.0001 && x >-0.0001) x=0.0001;
        return Math.tan( y/x);
    }

    private double x2(double x, double y) {
        double a = x*(y*y*params[3]+10*params[1])/(y*y*params[4]+10*params[5]+0.001);
        return a;
    }

    private double y2(double x, double y) {
        double a = y*(x*x*params[13]+10*params[11])/(x*x*params[14]+10*params[15]+0.001);;
        return a;
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

