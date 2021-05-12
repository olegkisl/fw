package blocks;

/**
 * Bricks wall
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRF_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_BCGRF_0() {
        super("A3_BCGRF_0", 0, 40);
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
        double border=0.001*params[12];
        double border_color=0.4+0.2*params[13];
        int nx = 0, ny = 0;
        double x1=x * (alfa * size);
        double y1=y * (alfa * size);
        ny = (int) (y1);
        nx = (int) (x1);
        int yshift=ny%2;
        int xnn = (yshift+nx)/2;
        int xpos= (yshift+nx)%2;
        x=x1-nx;
        if(x*x<size*border && xpos==0)
           return border_color;
        x=x1-nx-1;
        if(x*x<size*border && xpos==1)
           return border_color;
        y=y1-ny;
        if(y*y<size*border)
            return border_color;
        y=y1-ny-1;
        if(y*y<size*border)
            return border_color;
        
        j = (xnn)%2;
        if(j == 0)
            return 0.8+0.2*params[10];
        else
            return 0.8-0.2*params[11];
       
    }
     
     
    public double f(double x, double y) {
        double rrr = 1.0;
        double cc=1.0;   
        if(params[2]>0.33) cc=1+33*params[1];  
        rrr = ptrn3(x, y, cc, 1);
        if (params[10] > 0.25) {
            rrr =1-rrr;
        }
        return rrr;
    }
}
