package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_gorizont   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFM_gorizont() {
     super("AFM_gorizont", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
 //  double tt = gg.f(x,y);
   double a,aa, aaa;

   if(params[0]<0.5){
     a=(1+params[1])/(1+t*t);
     aa=(1+params[2])/(1+t*t);
     aaa = 1+ 1/(1+params[3]*t*t);
     return aaa*gg.f(x*a,y*aa);
   }
   else {
       a=(1+params[1]+t)/(1+params[4]*t*t);
      aa=(1+params[2]+t)/(1+params[5]*t*t);
      aaa = 1+params[4]- 1/(1+params[3]*t*t);
      return aaa*gg.f(x*a,y*aa);

   }

  }

}
