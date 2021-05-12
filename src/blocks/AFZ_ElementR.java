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


public class AFZ_ElementR   extends Block_Prototype_FxyP {
static final long serialVersionUID =1L;
  double sc = 10.0;
  double[] sn = new double[14];
  double[] cs = new double[14];

  public AFZ_ElementR() {
     super("AFZ_ElementR", 1, 7);
     for(int i =1; i<14; i++){
       sn[i] = Math.sin(Math.PI*2.0/i);
       cs[i] = Math.cos(Math.PI*2.0/i);
     }

  }

  int n=2;
  int ang=12;
  public void mutateLarge_this(){
   super.mutateLarge_this();
    ang= 3+FW_Rand.rand(8);
    n= 1+FW_Rand.rand(3);
  }


  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;

    double t = 0, xx, yy, tt, x0=x,y0=y;

   for(int j =1; j<n; j++){
       xx= x*cs[ang]-y*sn[ang];
       yy= x*sn[ang]+y*cs[ang];
       tt = g.f(xx,yy);
       x=xx;
       y=yy;
       if(j==1)//////////////////////
         t=tt;
       if(params[1]<0.33){
         if (tt > t)
          t = tt;
       }
       else if(params[1]<0.66){
         if (t > tt)
          t = t+tt/2;
         else
          t = t-tt/2;
       }

       else{
           t = t+tt;
       }
   }
   x=x0;y=y0;
   for(int j =1; j<n; j++){
        xx= x*cs[ang]+y*sn[ang];
        yy= -x*sn[ang]+y*cs[ang];
        tt = g.f(-xx,yy);
        x=xx;
        y=yy;
        if(params[1]<0.33){
          if (tt > t)
           t = tt;
        }
        else if(params[1]<0.66){
          if (t > tt)
           t = tt;
        }

        else{
            t = t+tt;
        }
    }



   x=x0;y=y0;
   double ax,ay,az,aa,bb;
   ax=x*x+y*y;
   aa=t;
   if(aa<0)aa=-aa;
   ay=x*x+(y-1)*(y-1);
   az=x*x+(y-0.3)*(y-0.3);
  // aa=ax;//+params[2]*ay+params[3]*az;
   bb=ax*params[2]+params[3]+ay*params[4]+az*params[5];
   aa = aa*bb;
   if(aa>10*params[5]) aa=-1;
 //  if(aa>5.0) aa=-1;
   return aa;

  }

}
