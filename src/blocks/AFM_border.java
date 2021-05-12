package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_border   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_border() {
     super("AFM_border", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);
   double a,aa;

   if(params[0]<0.25){
     if (tt > params[1])
       tt = t /(tt+0.0001);
     else
       tt = t * params[1];
     return tt;
   }
   else if(params[0]<0.5){
     if (tt > 1)
        tt = t /(tt*tt+0.0001);
      else
        tt = t;
      return tt;
   }
   else if(params[0]<0.75){
     if (tt > 0.75)
         tt = t /(tt*tt+0.0001);
       else
         tt = t;
       return tt;

   }
   else {
     if (tt > 1)
         tt = t /(tt*tt*tt+0.0001-params[1]);
       else
         tt = t;
       return tt;

 }

  }

}
