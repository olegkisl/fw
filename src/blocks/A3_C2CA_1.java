package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_C2CA_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_C2CA_1() {
        super("A3_C2CA_1", 1, 40);
        // set_param();
    }
    //////////////////////////////////////////////////

    private double get_c(double c) {
        double res = c;
        int k = (int) (params[15] * 7.0);
        switch (k) {
            case 0: {
                res=(params[0]-params[1])*c*c+c;
            }
            break;

            case 1: {
                if(c<-1 || c>1) 
                    res=c;
                else 
                    res= Math.pow(c*c, 1+5*params[0]);
                
            }
            break;

            case 2: {
                 if(c<-1 || c>1) 
                    res=c;
                else if (c>=0)
                    res= Math.sin(params[1]*3.142*c);
                else
                    res= Math.sin(params[1]*3.142*(-c)); 
            }
            break;

            case 3: {
               res= c+c*params[0];
            }
            break;
                
            case 4: {
               res= c*c-params[10]*c+params[0]*Math.sin(params[1]*3.142*c);
            }
            break;
                
            case 5: {
               res= -res;
            }
            break;

            default: {
                res = c;
            }
        }
        return res;
    }
  
//////////////////////////////////////////////////    

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double h = g.f(x, y); 
        return get_c(h);
    }
}

