package blocks;


 import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;

// Polynom
 public class AF_normal   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public AF_normal() {
      super("AF_normal", 0, 10);
   }

   double mod = 1.001;
   double max = 12.0;

   public double f(double x, double y){
    double a = params[1]*x+params[2]*y  ;
    double b = params[3]*x+params[4]*y ;
    a =a*a+b*b;
    if(a>max)  a= max;
    b =Math.exp(-a)*mod;
    return  b;
  }


 }
