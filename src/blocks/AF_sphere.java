package blocks;


 import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;

// Polynom
 public class AF_sphere   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public AF_sphere() {
      super("AF_sphere", 0, 10);
   }

   double mod = 0.5;
   double max = 2.0;

   public double f(double x, double y){
    double a = params[1]*x  ;
    double b = params[4]*y ;
    a = max - a*a+b*b;
    if(a<0)  a=0;
    b =a*mod;
    return  b;
  }


 }
