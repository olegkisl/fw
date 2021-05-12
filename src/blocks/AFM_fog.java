package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_fog   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_fog() {
     super("AFM_fog", 2, 7);
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
     return  t/(0.2+params[2]+params[1]*tt*tt);
   else if(params[0]<0.5){
     a=t*t;
     aa=tt/(1+params[1]*tt*tt);
     return a*aa;
   }
   else if(params[0]<0.75){
    ;
    aa=Math.abs(tt);
    return (t)/(0.5+aa+params[1]);
   }
   else {
   aa=tt*tt ;
   return t/(1+params[1]*aa*aa);
 }

  }

}
