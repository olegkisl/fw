
package blocks;

import fw2003.*;

public class A2_F2_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    static final double high = 2.5;
    static final double high0 = 0.0;
    static final double diff = 2.5;
    static final double diff0 = 2.5;
    
    public A2_F2_1() {
        super("A2_F2_1", 1, 20);
    }

    private double fx(double x, double y) {
        double a1= Math.cos(params[0]*x)+Math.cos(params[1]*x);
        double a2= a1*Math.cos(params[2]+params[3]*y);
        return a2 ;
    }
    
    private double fy(double x, double y) {
        double a1= Math.cos(params[5]*x)+Math.cos(params[6]*y);
        double a2= Math.cos(params[3]*y+params[4]*a1)*a1;
        return y+a2 ;
    }
    
     private double br(double x, double y) {
       if(params[19]>0.5)  return 1.0; 
       double a1= Math.cos(params[2]*y*Math.cos(x));
       // double a2= Math.cos(params[7]+y *x* params[5]*3*a1+x*0.3);
       // return a2*a2*2.0 ;
         return a1*a1*2;
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        
        return br(x,y) * g.f(fx(x,y), fy(x,y));
    }
}

