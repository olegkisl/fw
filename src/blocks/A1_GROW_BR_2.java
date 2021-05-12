package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A1_GROW_BR_2 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    double sn = 0.5;
    double cs = 0.5;

    public A1_GROW_BR_2() {
        super("A1_GROW_BR_2", 2, 30);
        set_param();
    }

    private void set_param() {
        sn = Math.sin(Math.PI * params[0] * 0.3);
        cs = Math.cos(Math.PI * params[0] * 0.3);
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
    }
    
     private double fff(double a, double b){
       if(a>0)
           return a;
       return b;    
   }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double back = g.f(x, y);
        Block_Prototype_Fxy gg = (Block_Prototype_Fxy) sons[1];
        if (gg == null) {
            return 0.9;
        }
        double  xx= x*cs-y*sn+0.5*params[1];
        double  yy= x*sn+y*cs;
        double f = gg.f(xx, yy);
        
        return fff(f,back);
    }
}
