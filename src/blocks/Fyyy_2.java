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


 public class Fyyy_2   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;
   double sc = 10.0;
   public Fyyy_2() {
      super("Fyyy_2", 1, 10);
   }

   public double f(double x, double y){
    Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
    if(g == null) return 0.5;
    double t = g.f(x,y);



    if(params[0]<.05)
      t = g.f(x+params[1],y)+g.f(x+params[1],-y);
    else
      t = g.f(x,y+params[1])+g.f(-x,y+params[1]);


  return t;
 }

 }
