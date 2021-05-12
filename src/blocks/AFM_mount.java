package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_mount   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_mount() {
     super("AFM_mount", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
 //  double tt = gg.f(x,y);
   double a,aa;

   if(params[0]<0.5){
     a=(1+params[1])/(1+t*t);
     aa = params[2]*t*t;
     return a*gg.f(x,y-aa);
   }
   else {
     a=(1+t*t*params[1])/(1+t*t*params[2]);
     aa=params[2]*t*t;;
     return a*gg.f(x,y-aa);
   }

  }

}
