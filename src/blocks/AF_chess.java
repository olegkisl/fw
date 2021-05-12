package blocks;

 import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;

// Polynom
 public class AF_chess   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public AF_chess() {
      super("AF_chess", 0, 10);
   }

   double mod = 0.2;

   public double f(double x, double y){
    double a = params[1]*x+params[2]*y  ;
    double b = params[3]*x+params[4]*y ;
    a =a*mod-Math.floor(a*mod);
    b =b*mod-Math.floor(b*mod);
    if((a<0.5)&&(b<0.5))
      return 1.0;
    if((a>0.5)&&(b>0.5))
      return 1.0;
    return  0.0;
  }


 }
