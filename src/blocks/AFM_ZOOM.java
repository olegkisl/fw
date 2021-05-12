package blocks;





import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_ZOOM  extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double[] sn = new double[8];
  double[] cs = new double[8];

  public AFM_ZOOM() {
     super("AFM_ZOOM", 1, 7);
     for(int i =1; i<8; i++){
       sn[i] = Math.sin(Math.PI*0.45/i);
       cs[i] = Math.cos(Math.PI*0.45/i);
     }
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y), xx, yy, tt;
   int i = 5;
   if(params[0]<0.2)
     i = 5;
   else if(params[0]<0.4)
     i = 4;
   else if(params[0]<0.6)
     i =6;
   else
     i =7;
  int m=5;
  if(params[4]>0.35)
    m=7;
  else if(params[4]>0.7)
    m=6;

  double s=0;
  double w=0;
   for(int j =1; j<m; j++){
       if(j%2==0){
         xx = x;
         yy = y;
       }
       else{
         xx = x;
         yy = y;
       }
       s = s+params[1]/j;
       w = w+params[4]/j;
       xx=(xx/j)*params[2];
       yy=(yy/j)*params[3];
       tt = g.f(xx+s,yy+w);
       if(params[1]<0.333){
         if (tt > t)
          t = tt;
       }
       else if(params[1]<0.666){
      if (tt < t)
       t = tt;
      }
       else{
           t = t+tt;
       }
   }
   return t;
  }

}
