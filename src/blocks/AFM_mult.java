package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_mult   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_mult() {
     super("AFM_mult", 2, 7);
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
     return  t*tt;
   else if(params[0]<0.5){
     a=t*t;
     aa=tt*tt;
     return (2*a*aa)/(a+aa+0.0001);
   }
   else if(params[0]<0.75){
    a=Math.abs(t);
    aa=Math.abs(tt);
    return (2*a*aa)/(a+aa+0.0001);
   }
   else {
   a=t*tt;
   aa=Math.abs(t+tt); ;
   return (2*a)/(aa+0.0001);
 }

  }

}
