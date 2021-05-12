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


public class f_Simetr2   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  

  public f_Simetr2() {
     super("ZoomSim", 1, 7);
    
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y), xx, yy, tt;
   int i = 3;
   if(params[0]<0.5)
     i = 2;
   xx=x;
   yy=y;
   double a =params[2]+0.5;
   for(int j =0; j<i; j++){
	   if(params[2]<0.7)
              xx= x*a;
       if(params[3]<0.7)
              yy= y*a;
       x = xx;
       y = yy;
       tt = g.f(x,y);
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
