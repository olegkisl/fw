package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AF_super3   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;

  public AF_super3() {
     super("AF_super3", 3, 10);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);
   double a = params[1]*t+params[2]*tt-params[0] - params[7]*t -params[6]*tt;
   double b = params[3]*t+params[4]*tt-params[5] - params[8]*t-params[9]*tt;
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[1];
   if(ggg == null) return 0.9;
   double ttt = ggg.f(a,b);
   return ttt;
  }

}
