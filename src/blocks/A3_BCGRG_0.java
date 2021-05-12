package blocks;

/**
 * Vertical strips
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRG_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_BCGRG_0() {
        super("A3_BCGRG_0", 0, 40);
        // set_param();
    }
  
    public void mutate_this() {
        super.mutate_this();
    }

     private double ptrn3(double x, double y, double size, int j) {
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        int nx = 0, ny = 0;
        double x1=x * (alfa * size);
        double y1=y * (alfa * size);
        ny = (int) (y1);
        nx = (int) (x1);
        if(params[0]>0.5) ny=nx;
        int k=2+(int)(params[3]*4);
        int yshift=ny%k;
        if(yshift == 0)
            return 0.1*params[10];
        else
            return 1-0.1*params[11];
       
    }
     
     
    public double f(double x, double y) {
        double rrr = 1.0;
        double cc=1.0;   
        if(params[2]>0.33) cc=1+100*params[1];  
        rrr = ptrn3(x, y, cc, 1);
        if (params[10] > 0.25) {
            rrr =1-rrr;
        }
        return rrr;
    }
}
