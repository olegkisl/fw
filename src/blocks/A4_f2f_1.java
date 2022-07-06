
package blocks;

/**
 * f(x,y)= g(f1(x,y) )
 * @author okislyuk
 */
import fw2003.*;

public class A4_f2f_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A4_f2f_1() {
        super("A4_f2f_1", 1, 20);
        // set_param();
    }
  

////////////////////////////////////////////////// 

    private double ff(double f) {
        double z = (-params[1]) * Math.sin(6.0*params[2] * f) + (params[3]) * Math.sin(6*params[4] * f);
        return z;
    }


    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double cc = 3.0;       
        return cc*ff(g.f(x,y));
    }
}


