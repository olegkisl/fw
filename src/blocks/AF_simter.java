package blocks;




import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

// ROTATION
public class AF_simter   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double[] sn = new double[8];
  double[] cs = new double[8];

  public AF_simter() {
     super("AF_simter", 1, 5);
     for(int i =1; i<8; i++){
       sn[i] = Math.sin(Math.PI*2.0/i);
       cs[i] = Math.cos(Math.PI*2.0/i);
     }
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y), xx, yy, tt;
   int i = 3;
   if(params[0]<0.2)
     i = 7;
   else if(params[0]<0.4)
     i = 4;
   else if(params[0]<0.6)
     i =5;
   else if(params[0]<0.8)
     i =6;
   else
     i =3;
 //  x = x+params[1]-params[2];
 //  y = y+params[3]-params[4];
   double w=t*t, s=w*t;
   for(int j =1; j<i; j++){
       xx= x*cs[i]-y*sn[i];
       yy= x*sn[i]+y*cs[i];
       x = xx;
       y = yy;
       tt = g.f(x,y);
       t=tt*tt;
       w=w+t;
       s=s+t*tt;
   }

   return s/(w+0.0001);
  }

}
