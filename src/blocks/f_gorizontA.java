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


public class f_gorizontA   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public f_gorizontA() {
     super("gorizontA", 3, 10);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
   double tt = gg.f(x,y);
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[1];
   if(ggg == null) return 0.9;
   double ttt = ggg.f(x,y);
   double a = params[1]*x+params[2]*y-params[0] ;
   double b = params[3]*x+params[4]*y-params[5] ;
   double u;
   if(y>Math.sin(params[6]*ttt)){
     u = g.f(a/(y+0.001),y);
   }
   else{
     u = gg.f(b/(ttt*ttt-y+0.001),y);
   }
  return u;
  }

}
