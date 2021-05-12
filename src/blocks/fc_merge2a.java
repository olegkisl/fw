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

public class fc_merge2a   extends Block_Prototype_Fxy_C {
static final long serialVersionUID =1L;
  public fc_merge2a() {
     super("fc_merge2a", 2, 4);
  }

  Clr clr = new Clr(0.0,0.0,0.0);

  public Clr fc(double x, double y){

   Block_Prototype_Fxy p =(Block_Prototype_Fxy)sons[0];
   double t =0;
   if(p != null)
       t = p.f(x,y);
   if (t<0) t =-t;
   if (t>1) t =1/t;

   Block_Prototype_Fxy pp =(Block_Prototype_Fxy)sons[1];
   double tt =0;
   if(pp != null)
       tt = pp.f(x,y);
   if (tt<0) tt =-tt;
   if (tt>1) tt =1/tt;

   double a = params[0]/(params[0]+params[1]+0.0001);
   double b = 1-a;
   clr.r =color_r[0]*a*t + color_r[1]*b*tt;
   clr.g =color_g[0]*a*t + color_g[1]*b*tt;
   clr.b =color_b[0]*a*t + color_b[1]*b*tt;
   return  clr;
  }

}
