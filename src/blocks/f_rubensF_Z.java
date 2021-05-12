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


public class f_rubensF_Z   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
//  double sc = 10.0;
  public f_rubensF_Z() {
     super("rubensF_Z", 3, 10);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[2];
   if(ggg == null) return 0.9;
  // double ttt = ggg.f(x,y);
   double u = params[5]*tt*(3*t*t-params[6]*tt*tt);
   double v = params[2]*t+params[3]*tt;
   v = Math.atan(v/(tt*tt+0.001));
   return ggg.f(u,v);
  }

}
