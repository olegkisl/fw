/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import fw2003.*;

public class A1_HILL_2 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    static final double high = 2.5;
    static final double high0 = 0.0;
    static final double diff = 2.5;
    static final double diff0 = 2.5;
    
    public A1_HILL_2() {
        super("A1_HILL_2", 2, 20);
    }

    private double fff(double x) {
        double a1= Math.cos(params[10]+x * (params[0]+0.1));
        double a2= Math.cos(params[11]+x * params[1]*2);
        double a3= Math.cos(params[12]+x * params[13]*3);
        double x1 = x + a1 + a2+ a3;
        double y1 = Math.cos(x1 * params[2]);
        double y2 = Math.cos(x1 * params[3]);
        y2 = y2 * y2;
        return high0 * params[7] + high * params[5] * y1 * y1 + high * params[6] * y2 * y2 ;
    }
    
    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        Block_Prototype_Fxy h = (Block_Prototype_Fxy) sons[1];
        if (g == null || h== null) {
            return 0.5;
        }
        //double c = params[9];
        double a = fff(x);
        double b = a - diff * params[8] - diff0;
        if (y >= a ) {
            return g.f(x, y);
        }
        if ( y <= b) {
            return h.f(x, y);
        }
        double r = 1.0 - (a - y) / (a - b);
        double ry = r * r;
        double y1 = b + ry * (a - b);
        double bright = r  - 1;
        return bright * h.f(x, y1);
    }
}

