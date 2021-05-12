package blocks;




import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;


 public class AF_repeat   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;
   double sc = 10.0;
   public AF_repeat() {
      super("AF_repeat", 1, 10);
   }

   public double f(double x, double y){
    Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
    if(g == null) return 0.5;
    double t = g.f(x,y);

    double u = params[1]-params[2];
    double v = params[3]-params[4];


    if(params[5]>0.5){
      if (params[0] < .025) {
        t = g.f(x, y) + g.f(x+u, y+v)+ g.f(x+u*2, y+v*2);
      }
      else if (params[0] < .025) {
        t = g.f(x, y) + g.f(x+u, y+v)+ g.f(x+u*2, y+v*2)+g.f(x+u*3, y+v*3);
      }
      else {
        t = g.f(x, y) + g.f(x+u, y+v);
      }
    }
    else{
      if (params[0] < .05) {
        t = Math.max(g.f(x, y),g.f(x+u, y+v));
      }
      else if (params[0] < .025) {
        t = Math.max(g.f(x, y),g.f(x-u, y+v));
      }
      else {
        t = Math.max(Math.max(g.f(x, y),g.f(x+u, y+v)),
                     Math.max(g.f(x+2*u, y+2*v),g.f( x+3*u, y+3*v)));
      }
    }


    return t;
 }

 }
