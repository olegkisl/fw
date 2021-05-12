package blocks;




 import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;

// Polynom
 public class AF_sin   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public AF_sin() {
      super("AF_sin", 0, 10);
   }

   double mod = 10.0;

   public double f(double x, double y){
    double a = params[1]*x+params[2]*y  ;
    double b = params[3]*x+params[4]*y ;
    return (0.8+params[0])*   Math.sin(params[5]+mod*a)*Math.sin(params[6]+ mod*b);
  }


 }
