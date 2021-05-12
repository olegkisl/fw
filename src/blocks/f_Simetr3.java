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


public class f_Simetr3   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  

  public f_Simetr3() {
     super("ReflSim", 1, 7);
    
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y), xx, yy, tt;
   xx=x;
   yy=y;
   for(int j =0; j<2; j++){
	   if(params[2]<0.5)
              xx= -x;
       else
              yy= -y;
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
