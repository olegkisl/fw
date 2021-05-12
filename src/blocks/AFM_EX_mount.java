package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_EX_mount  extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_EX_mount() {
     super("AFM_EX_mount", 4, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
//   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[1];
   if(ggg == null) return 0.9;
   Block_Prototype_Fxy gggg =(Block_Prototype_Fxy)sons[1];
   if(gggg == null) return 0.9;
 //  double tt = gg.f(x,y);
   double h,d,a,b,h1,h2,h3;
   double bgr=0.0;
   h1=g.f(x,params[3])-y; //horizont1;
   h2=ggg.f(x,params[4])-y; //horizont2;
   h3=gggg.f(x,params[5])-y; //horizont2;
 //  h=Math.max(h1,Math.max(h2,h3));
   h=Math.min(h1,Math.min(h2,h3));
   if(h<0.1*params[0])
     return bgr;
   a = Math.pow(h,0.2+3*params[1]);
   if(a>1) a=1;
   b = Math.pow(h,0.2+3*params[2]);
   if(b>1) b=1;
   return a*gg.f(x*b,y*b);

  }

}
