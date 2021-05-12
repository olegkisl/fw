package blocks;




import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_gorizontB   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_gorizontB() {
     super("AFM_gorizontB", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
 //  double tt = gg.f(x,y);
   double a=1,aa, aaa,bb;

   if(y>-0.0001){
     return t;
   }
   else{
     aa=-params[1]-0.5;
     if(y<aa){
       a=1;
       aaa=1;
     }
     else{
       a=aa/y;
       aaa=(y/aa+0.1);
     }
     a=aaa*gg.f(x*a,y*a);
     aa=a*a;
     bb=t*t;
     return (a*aa+t*bb)/(aa+bb+0.000001);
   }


  }

}
