package blocks;


 import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;

// Polynom
 public class AF_square   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public AF_square() {
      super("AF_square", 0, 10);
   }

   double mod = 0.7;

   public double f(double x, double y){
    double a = params[1]*x+params[2]*y  ;
    double b = params[3]*x+params[4]*y ;
    a =(a*mod);
    b =(b*mod);
    if((a<0.5)&&(b<0.5)&&(a>0.0)&&(b>0.0))
      return 1.0;
    return  0.0;
  }


 }
