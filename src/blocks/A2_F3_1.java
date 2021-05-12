
package blocks;

import fw2003.*;

public class A2_F3_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    static final double s1 = 5;
    static final double s2 = 0.50;
    static final double h1 = 0.05;
    static final double diff0 = 2.5;
    
    public A2_F3_1() {
        super("A2_F3_1", 1, 20);
    }

   
    
    private double dy(double x, double y) {
        double a1= Math.cos(s1*params[0]*x)+Math.cos(s1*params[1]*x);
        double a2= Math.cos(s2*params[3]*y);
        return h1*a2*a2*a1 ;
    }
    
     private double br(double x, double y) {
       if(params[19]>0.5)  return 1.0; 
        double a1= Math.cos(s1*params[0]*x)+Math.cos(s1*params[1]*x);
        double a2= Math.cos(s2*params[3]*y);
        return 0.1+2.3*params[9]*a2*a2*a1 ;
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        
        return br(x,y) * g.f(x, y+dy(x,y));
    }
}


