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


public class f_rubensP  extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
//  double sc = 10.0;
  public f_rubensP() {
     super("rubensP", 3, 10);
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

   double u = (params[0]-t*tt*params[8]+t*params[7]);
   u = 1/(0.001+u*u);
   double v = (params[2]*t - params[3]*tt+ params[4]);
   if(v<0.001) v= v*v+params[6];
   double r = u*ggg.f(t/v,tt/u+params[4]*t+params[5]);
   return r;
  }

}
