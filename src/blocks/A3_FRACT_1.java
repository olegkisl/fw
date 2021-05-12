package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_FRACT_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_FRACT_1() {
        super("A3_FRACT_1", 1, 40);
        // set_param();
    }

//////////////////////////////////////////////////    
    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }

        double cc = 1.0;
        double x2 = x * Math.sin(1 + 2 * params[22] * y);
        double y2 = y * Math.sin(1 + 2 * params[23] * x);
        x = x2;
        y = y2;
        double rrr = 0;//fff(x, y, 100);
        int nn = (int) (params[7] * 7+1);
        for (int j = 0; j < nn; j++) {
            //cc = cc * 2.0;
            double x1 = x;
            double y1 = y;
            // double h = ptrn1(x1, y1, cc, j);
            double h = g.f(x1, y1);
            if(params[2]>0.6)
               rrr = params[11]*rrr+ (1-params[11])*h;
            else if(params[2]>0.3)
               rrr=(rrr+h)-(int)rrr;
            else {
                if(rrr<0.01) rrr=1;
                rrr=rrr*h;
            }
            //if (rrr>1) rrr = 1;    

            x = x1 * 2.5 * (0.55 + params[16]) + (0.5 + params[11]) * y1 * Math.sin(1 + 4 * params[12] * y1);
            y = y1 * 2.5 * (0.55 + params[17]) + (0.5 + params[13]) * x1 * Math.sin(1 + 4 * params[14] * x1);

        }
        if (rrr < 4 && rrr > -4) {
            if (rrr > 0) {
                rrr = Math.pow(rrr, 8 * params[9]);
            } else {
                rrr = -Math.pow(-rrr, 5 * params[9]);
            }
        }
        if (params[10] > 0.5) {
            rrr = 1 - rrr;
        }
        return rrr;
    }
}
