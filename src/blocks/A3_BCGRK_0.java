
package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRK_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000000;
    double alfa = 1.0;

    public A3_BCGRK_0() {
        super("A3_BCGRK_0", 0, 40);
        // set_param();
    }
    int a1 = 46713, a2 = 21713, a3 = 71893, a4 = 31713, a5 = 12753, a6 = 5117;

    private void set_param() {
        a1 = (int) (10000 * params[0]);
        a2 = (int) (10000 * params[2]);
        a3 = (int) (10000 * params[3]);
        a4 = (int) (10000 * params[4]);
        a5 = (int) (10000 * params[5]);
        a6 = (int) (10000 * params[6]);
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
    }
    
//////////////////////////////////////////////////    

    private double fff(double x, double y, double size) {
        mod=50000;
        //Jenkins's one_at_a_time hash
        int nx = 0, ny = 0;
        nx = (int) (x * (alfa * size));
        ny = (int) (y * (alfa * size));
        int hash = 0;

        hash += nx;
        hash += hash << 10;
        hash ^= hash >> 6;
        hash += ny;
        hash += hash << 10;
        hash ^= hash >> 6;
        hash += hash << 3;
        hash ^= hash >> 11;
        hash += hash << 15;
        return (hash % mod)/(mod+1.0);
    }

    public double f(double x, double y) {
        double res = 1.0;
        double cc = 3.0;
        double p = 0.6;
        
        double rrr=0;
        for (int j = 0; j < 10; j++) {
            //cc = cc * 2.0;
            double x1= x*(1.3+params[2])+y*(-0.5+params[5]) ;
            double y1= y*(1.3+ params[3])+x*(-0.5+params[5]) ;
            res = fff(x, y, 1.0);
            if (res>params[0])
                rrr=0.5*rrr+0.5*params[1];
                if (rrr>1.0)rrr=1.0;
              //rrr+= Math.sin(x*3.141592653)*Math.sin(y*3.141592653);
            x=x1;
            y=y1;
        }
        
       
        return rrr;
    }
}

