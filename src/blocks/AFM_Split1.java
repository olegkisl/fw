package blocks;



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFM_Split1  extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;


  public AFM_Split1() {
     super("AFM_Split1", 1, 7);

  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double  xx, yy, tt, t=0;
   if(y<0){
     return y*g.f(x,y);
   }
   else{
     int i = 2;
     if(params[0]<0.2)
        i = 4;
     else if(params[0]<0.4)
        i = 3;


   for(int j =1; j<i; j++){
       if(j%2==0){
         xx = x/(y+0.0001)*j*params[1];
         yy = y*params[2];
       }
       else{
         xx = -x/(y+0.0001)*j*params[1];
         yy = y*params[2];
       }

       tt = g.f(xx,yy);
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
   return y*t;

   }
  }

}
