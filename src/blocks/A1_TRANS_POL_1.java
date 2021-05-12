/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import fw2003.*;

public class A1_TRANS_POL_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_TRANS_POL_1() {
        super("A1_TRANS_POL_1", 1, 5);
    }

    private double fff(double x, double y) {
        double alfa = 5.0;
        if (x < 0.000001 && x > -0.000001) {
            x = 0.000001;
        }
        return params[1] * Math.cos(Math.atan(y / x) * params[0] * alfa);
    }

    private double fff1(double x, double y) {
        return y =  params[1] * Math.cos(x * params[0]);
    }

    private double fff2(double x, double y) {
        return y =  params[1] * Math.pow(x * x, 0.1 + params[2]);
    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double c = params[1];
        if (c < 1.1111111111) {
            double e= fff(x, y);
            e=1/(e*e+params[2]*2+0.1);
            return g.f(x*e, y*e);
        }
        if (c < 0.75) {
            return g.f(x, fff1(x, y));
        };
        return g.f(x, fff2(x, y));
    }
}
