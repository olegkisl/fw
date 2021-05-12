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


public class f_SimetrA1   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;


  public f_SimetrA1() {
     super("TransASim", 1, 10);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y), xx, yy, tt;
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
       xx= x*params[2]-y*params[3]+params[4];
       yy= x*params[5]+y*params[6]+params[7];
       x = xx;
       y = yy;
       tt = g.f(x,y);
       if(params[1]<0.5){
         if (tt > t)
          t = tt;
       }
       if(params[1]<=0.5){
           t = t+tt;
       }
   }
   return t;
  }

}
