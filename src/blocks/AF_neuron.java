package blocks;


 import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;

// Polynom
 public class AF_neuron   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;

   public AF_neuron() {
      super("AF_neuron", 0, 10);
   }

   double mod = 1.2;
   double coef = 2.5;
   double max = 12.0;
   double min = -12.0;

   public double f(double x, double y){
    double a = params[1]*x+params[2]*y  ;
    a = a*coef;
    if(a>max)  a= max;
    if(a<min)  a= min;
    return mod/(1.0+Math.exp(-a));
  }


 }
