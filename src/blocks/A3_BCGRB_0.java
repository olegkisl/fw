package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRB_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_BCGRB_0() {
        super("A3_BCGRB_0", 0, 40);
        // set_param();
    }
    private double[] uuu = {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1,1,0};
    private int divider=10;
    private void set_param() {
        for (int i = 0; i < uuu.length; i++) {
            //if(params[i]>0.5) uuu[i]=1;
            // else uuu[i]=0;
            uuu[i] = params[i]*2.5;
        }
        divider=(int)(4+(uuu.length-6)*params[15]);
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
    }

//////////////////////////////////////////////////    
    private double fff(double x, double y, double size,int j) {
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
       
       // return (hash % mod) / (mod + 1.0);
       
        int res  = (hash+j);
        if(res<0) res=-res;
        res = (res+1) % divider;
        if(res<0) res=0;
        return uuu[res];
    }

    public double f(double x, double y) {
//   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
//   if(g == null) return 0.5;
//   double t = g.f(x,y), xx, yy, tt;
        double res = 1.0;
        double cc = 1.0;
        double x2 = x*Math.sin(1+2*params[22] * y);
        double y2 = y*Math.sin(1+2*params[23] * x);
        x=x2;
        y=y2;
        double rrr = 1;//fff(x, y, 100);
        for (int j = 0; j < 7; j++) {
            //cc = cc * 2.0;
            double x1=x;
            double y1=y;
           // double h = ptrn1(x1, y1, cc, j);
            double h = fff(x1, y1, cc, j);
            rrr *= h;
            //if (rrr>1) rrr = 1;    

            x = x1 * 2.5 * (0.55 + params[16]) +(0.5+ params[11]) * y1*Math.sin(1+4*params[12] * y1);
            y = y1 * 2.5 * (0.55 + params[17]) +(0.5+params[13]) * x1*Math.sin(1+4*params[14] * x1);

        }
        if (rrr < 0) {
            rrr = 0;
        }
        if (rrr > 5) {
            rrr = 5;
        }
        
        rrr = Math.pow(rrr, 8 * params[9]);
        if (params[10] > 0.5) {
            rrr =1-rrr;
        }
        return rrr;
    }
}

