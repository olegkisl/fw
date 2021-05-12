package blocks;




import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_Fgh5   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;

  public AFM_Fgh5() {
     super("AFM_Fgh5", 5, 10);
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
   double u = w.f(x,y);
   Block_Prototype_Fxy ww =(Block_Prototype_Fxy)sons[3];
   if(ww == null) return 0.9;
   double uu = ww.f(x,y);

   double a =x*params[1]*t+y*params[2]*tt-params[0] - params[7]*u -params[6]*uu;
   double b =x*params[3]*t+y*params[4]*tt-params[5] - params[8]*u-params[9]*uu;
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[4];
   if(ggg == null) return 0.9;
   double ttt = ggg.f(a,b);
   return ttt;
  }

}
