package blocks;

 import fw2003.*;

// Polynom
 public class A1_PRIM3_0   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public A1_PRIM3_0() {
      super("A1_PRIM3_0", 0, 10);
   }

    double val = -0.5;

   public double f(double x, double y){
    double x1=0.4*params[0];
    double y1=0.4 *params[1];
    double x2=0.6+0.4*params[2];
    double y2=0.5*params[3];
    double x3=0.2+0.8*params[4];
    double y3=0.7+0.3*params[5];
    double clr=params[6];
    double a1 =(x-x1)*(y2-y1)-(y-y1)*(x2-x1);
    double a2 =(x-x2)*(y3-y2)-(y-y2)*(x3-x2);
    double a3 =(x-x3)*(y1-y3)-(y-y3)*(x1-x3);
    
    if(a1>=0 && a2>=0 && a3>=0)
      return clr;
    if(a1<=0 && a2<=0 && a3<=0)
      return clr;
    return val;
  }

 }
