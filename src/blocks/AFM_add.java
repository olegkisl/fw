package blocks;



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_add   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_add() {
     super("AFM_add", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);
   double a,aa;

   if(params[0]<0.25)
     return  t+tt;
   else if(params[0]<0.5){
     a=t*t;
     aa=tt*tt;
     return (a*t+aa*tt)/(a+aa+0.0001);
   }
   else if(params[0]<0.75){
    a=t*t; a=a*a;
    aa=tt*tt; aa=aa*aa;
    return (a*t+aa*tt)/(a+aa+0.0001);
   }
   else {
   a=t*t; a=a*a; a=a*a;
   aa=tt*tt; aa=aa*aa; aa=aa*aa;
   return (a*t+aa*tt)/(a+aa+0.0001);
 }

  }

}
