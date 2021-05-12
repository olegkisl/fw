package blocks;

/**
 * RANDOM BACKGROUND
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGR_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 50000;
    double alfa = 300.0;

    public A3_BCGR_0() {
        super("A3_BCGR_0", 0, 40);
    }


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
        //System.out.println(hash%1000);
        //if (hash % mod < mod * probability) {
        //    return 1.0;
        //}
        return (hash % mod)/(mod+1.0);
    }

    public double f(double x, double y) {
        x+=0.0;
        y+=0.0;
        double res = fff(x, y, 1.0);
        double rr=0;
        if(params[6]>0.5){
         if (res<0.6+0.4*params[7])
           rr=1;
         return rr;
        }
        
        if (res>0.3+0.3*params[2])
           rr=1;
        else if (res>0.1+0.25*params[1])
           rr=0.66;
        else if (res>0.0+0.06*params[0])
           rr=0.33;
        
        if(params[4]>0.5)
            rr=1-rr;
        if(params[5]>0.6 && rr>0.1)
            rr=1;
        return rr; //1-res;
    }
}

