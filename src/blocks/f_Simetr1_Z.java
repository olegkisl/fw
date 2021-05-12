package blocks;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class f_Simetr1_Z   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double[] sn = new double[7];
  double[] cs = new double[7];

  public f_Simetr1_Z() {
     super("RotateSim_Z", 1, 10);
     for(int i =1; i<7; i++){
       sn[i] = Math.sin(Math.PI*2.0/i);
       cs[i] = Math.cos(Math.PI*2.0/i);
     }
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y), xx, yy, tt;
   t = t*params[0]+1/(params[2]+0.001+t*t*params[3]);
   int i = 3;
   if(params[0]<0.2)
     i = 2;
   else if(params[0]<0.4)
     i = 4;
   else if(params[0]<0.6)
     i =6;
   else
     i =3;
   for(int j =1; j<i; j++){
       xx= x*cs[i]-y*sn[i];
       yy= x*sn[i]+y*cs[i];
       x = xx;
       y = yy;
       tt = g.f(x,y);
       tt = tt*params[0]+1/(params[2]+0.001+tt*tt*params[3]);
       if(params[1]<0.5){
         if (tt > t)
          t = tt;
       }
       else{
           t = t+tt;
       }
   }
   return t;
  }

}
