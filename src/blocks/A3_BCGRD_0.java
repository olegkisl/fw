package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRD_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_BCGRD_0() {
        super("A3_BCGRD_0", 0, 40);
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
        if(res < 0) res=0;
        return uuu[res];
    }

    private double ptrn1(double x, double y, double size, int j) {
        int nx = 0, ny = 0;
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        nx = (int) (x * (alfa * size));
        if (nx < 0) {
            nx = 0;
        }
        ny = (int) (y * (alfa * size));
        if (ny < 0) {
            ny = 0;
        }
        j = nx *(nx+ ny) + ny * j + 1+j;
        int res = (nx + ny + j) % divider;
        if(res<0) res=0;
        return uuu[res];
    }
    
     private double ptrn2(double x, double y, double size, int j) {
        double xx= Math.sin(0.5*j+params[21]*x * (alfa * size));
        double yy= Math.sin(0.5*j+params[23]*x * (alfa * size));
        double res=xx*xx+yy*xx;
        if (res<0) res=0;
        if(res>1) res=1;
        return res;
    }

    public double f(double x, double y) {
//   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
//   if(g == null) return 0.5;
//   double t = g.f(x,y), xx, yy, tt;
        double res = 1.0;
        double cc = 1.0;

        double rrr = 1;//fff(x, y, 100);
        for (int j = 0; j < 7; j++) {
            //cc = cc * 2.0;
            double x1 = x+y/(params[26]*y*y+1);
            double y1 = y-x/(params[27]*x*x+1);
           // double h = ptrn1(x1, y1, cc, j);
            double h=0;
            if(params[j+10]>0.5)
                h = fff(x1, y1, cc, j);
            else
                h = ptrn2(x1, y1, cc, j); 
            rrr *= h;
            //if (rrr>1) rrr = 1;    

            x = x1 * 2.5 * (0.55 + params[16]) + params[11] * y1*Math.sin(10*params[12] * y);
            y = y1 * 2.5 * (0.55 + params[17]) + params[13] * x1*Math.sin(10*params[14] * x);

        }
        if (rrr < 0) {
            rrr = 0;
        }
        if (rrr > 5) {
            rrr = 5;
        }
        
        rrr = Math.pow(rrr, 8 * params[9]);
        if (params[10] > 0.35) {
            rrr =1-rrr;
        }
        return rrr;
    }
}
