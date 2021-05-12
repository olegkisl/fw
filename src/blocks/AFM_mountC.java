package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_mountC   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_mountC() {
     super("AFM_mountC", 3, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[1];
   if(ggg == null) return 0.9;
   double a,b,c,d;

   a = tt*gorY(y);
   b = tt*gorC(y);
   c =  b*ggg.f(x,y+a);
   d = t*t;
   a = c*c;
   if(params[0]>0.5)
    return (a*c+t*d)/(a+d+0.000001);
   else
    return (2*a*d)/(a+d+0.000001);
  }

  private double gorY(double y){
    if(y<0.0001)
      return 0;
    else if(y<1)
      return Math.pow(y,2.0);
    else
      return 0;
  }

  private double gorC(double y){
    if(y<0.0001)
      return 1;
    else if(y<1)
      return Math.pow((1+y),-2.0);
    else
      return 1;
  }


}
