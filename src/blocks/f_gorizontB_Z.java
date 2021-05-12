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


public class f_gorizontB_Z   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public f_gorizontB_Z () {
     super("gorizontB_Z", 3, 10);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);

   double a = params[1]*x+params[2]*t-params[0];
   double b = params[3]*tt+params[4]*y-params[5] ;
   b=b*b;
   double u;
   u = g.f(a/(b+0.001),b*tt-t);
   return u;
  }

}
