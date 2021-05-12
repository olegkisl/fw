package blocks;

/**
 *
 * @author okislyuk
 */

import fw2003.*;

public class A1_MAP_AND_2   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public A1_MAP_AND_2() {
     super("A1_MAP_AND_2", 2, 5);
  }

   private double fff(double a, double b){
        if(a>0 && b>0)
           return a*b;
       return b;    
   }
   
   private double fff1(double a, double b){
       if(a>0 && b>0)
           return a*params[0]+b*(1-params[0]); 
       return b;
   }
   
   private double fff2(double a, double b){
       if(a>0 && b>0){
           double a1= a*a;
           double b1=b*b;
           return Math.pow((a1+b1)/2.0, 2.0);
       }
       return b;
   }
  
   public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double a1 = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double b1 = gg.f(x,y);
   double c=params[1];
   if(c<0.5)
     return fff(a1,b1);
   if(c <0.75)
     return fff1(a1,b1);
   return fff2(a1,b1);
  }
 
}


