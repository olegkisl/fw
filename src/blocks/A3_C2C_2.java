package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_C2C_2 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_C2C_2() {
        super("A3_C2C_2", 2, 40);
        // set_param();
    }
    //////////////////////////////////////////////////

    private double get_c(double c,double ca) {
        double res = c;
        int k = (int) (params[15] * 8);
        switch (k) {
            case 0: {
                if(c>params[0])
                  res=ca;
                else
                  res=c;
            }
            break;

            case 1: {
                if(c<-1 || c>1) 
                    res=c;
                else res =c*(1-ca);   
            }
            break;

            case 2: {
                 if(c<-1 || c>1) 
                    res=c;
                 else if( ca>c )
                     res=ca;
                 else
                     res=c;
            }
            break;

            case 3: {
               res= c+params[0]*Math.sin(params[1]*10*ca);
            }
            break;
                
            case 4: {
               res= c*c-params[0]*Math.sin(params[1]*4*ca);
            }
            break;
                
            case 5: {
               if(c<-1 || c>1) 
                    res=c;
                 else if( ca<c )
                     res=ca;
                 else
                     res=c;
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
        Block_Prototype_Fxy gg = (Block_Prototype_Fxy) sons[1];
        if (gg == null) {
            return 0.5;
        }
        double h = g.f(x, y); 
        double h1 = gg.f(x, y);
        return get_c(h,h1);
    }
}

