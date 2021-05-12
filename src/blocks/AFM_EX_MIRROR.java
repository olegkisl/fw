package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_EX_MIRROR  extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_EX_MIRROR() {
     super("AFM_EX_MIRROR", 2, 7);
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
   if(h<0.1*params[0]){
     h=-(h-0.2);
     a = Math.pow(h,0.2+2*params[6]);
     if(a>1) a=1;
     b = Math.pow(h,0.2+2*params[5]);
     return a*gg.f(x*b,-y*b);
   }
   a = Math.pow(h,0.2+2*params[1]);
   if(a>1) a=1;
   b = Math.pow(h,0.2+2*params[2]);
  // if(b>1) b=1;
   return a*gg.f(x*b,y*b);

  }

}
