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


 public class Fyyy_3   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;
   double sc = 10.0;
   public Fyyy_3() {
      super("Fyyy_3", 2, 10);
   }

   public double f(double x, double y){
    Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
    if(g == null) return 0.5;
    double t = g.f(x,y);
    Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
    if(gg == null) return 0.9;
    double tt = gg.f(x,y);



    if(tt>0.5)
      tt = t/tt;
    else
      tt =t*0.5;

    return  tt;
   }

 }
