package blocks;

/**
 * Horizontal nonlinear shifts
 * @author okislyuk
 */

import fw2003.*;

public class A1_TRANSY_1   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public A1_TRANSY_1() {
     super("A1_TRANSY_1", 1, 5);
  }

   private double fff(double x, double y){
       return y+params[1]*Math.cos(x*params[0])*(params[2]*x*x);    
   }
   
   private double fff1(double x, double y){
       return y+params[1]*Math.cos(x*params[0]);
   }
   
   private double fff2(double x, double y){
       return y+params[1]* Math.pow(x*x,0.1+params[2]);
   }
  
   public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double c=params[1];
   if(c<0.5)
     return g.f(x,fff(x, y));
   if(c <0.75)
     return g.f(x,fff1(x, y));;
   return g.f(x,fff2(x, y));
  }
 
}





