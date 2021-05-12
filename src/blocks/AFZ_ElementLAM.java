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


public class AFZ_ElementLAM   extends Block_Prototype_FxyP {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFZ_ElementLAM() {
     super("AFZ_ElementLAM", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t,tt;
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;

   t=g.f(x*0.2,y*0.2);
   tt=gg.f(x*0.2,y*0.2);
   if((t>1)||(t<-1))
     t=1/t;
   if((tt>1)||(tt<-1))
     tt=1/tt;

   x=x+t;
   y=y+tt;
   double ax,ay,az,aa,bb;
   double tr=10*params[3];

  // ax=x*x+y*y;
   aa=g.f(x,y);
   if(aa<0)aa=-aa;
   aa=aa-params[2]*0.2;
   if(aa<0)
     aa=aa*100;


   return aa;


  }

}
