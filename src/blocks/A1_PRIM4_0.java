
package blocks;

 import fw2003.*;

// Polynom
 public class A1_PRIM4_0   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public A1_PRIM4_0() {
      super("A1_PRIM4_0", 0, 15);
   }

    double val = -0.5;

   public double f(double x, double y){
    double x0=x;  
    double y0=y;
    if(params[8]>0.5) {  
      x0=(0.5+0.5*params[9])*x + (0.5-0.25*params[10])*y;  
      y0=(0.5-0.25*params[11])*x +(0.5+ 0.5*params[12])*y;
    }
    double x1=0.25+0.25*params[0];
    double y1=0.25+0.25*params[1];
    double x2=0.25+params[2];
    double y2=0.25+params[3];
    double clr=params[6];
    
    if(x0>x1 && x0<=(x1+x2) && y0>=y1 && y0<=(y1+y2))
      return clr;
    
    return val;
  }

 }

