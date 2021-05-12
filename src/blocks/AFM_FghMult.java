package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_FghMult   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;

  public AFM_FghMult() {
     super("AFM_FghMult", 3, 10);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);

   Block_Prototype_Fxy w =(Block_Prototype_Fxy)sons[2];
   if(w == null) return 0.5;

   double a;
   if(params[0]>0.5)
       a = params[1]/(1+params[2]*t*t +params[3]*tt*tt);
   else
       a = 1-1/(1+params[2]*t*t +params[3]*tt*tt);

   return a*w.f(x*t,x*tt);
  }

}
