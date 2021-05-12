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


public class f_mergeA3   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public f_mergeA3() {
     super("Merge3A", 3, 5);
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
   if(params[0]<0.2)
     return  t*ttt*ttt+tt*(params[1]-ttt)*(1-ttt);
   else if(params[0]<0.4)
     return params[1]*ttt/(tt*tt+0.001+params[2])+t*(ttt+params[3])/(t*t+0.001);
   else if(params[0]<0.6)
     return ( t*ttt)*((t+tt*tt)*(t+tt)+0.001);
   else if(params[0]<0.8)
     return  t*ttt+tt*tt*(t+params[1]);
   else
     return  t+tt+ttt;
  }

}
