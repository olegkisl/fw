package blocks;




import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_EX_perspectA  extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_EX_perspectA() {
     super("AFM_EX_perspectA", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
//   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
 //  double tt = gg.f(x,y);
   double h,d,a,b;
   double bgr=0.0;
   h=g.f(x,params[4])-y; //horizont;
   if(h<0.1*params[0])
     return bgr;
   a = Math.pow(h,0.2+2*params[1]);
   if(a>1) a=1;
   b = Math.pow(h,0.2+2*params[2]);
  // if(b>1) b=1;
   return a*gg.f(x*b,y*b);

  }

}
