package blocks;



import fw2003.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.tree.*;
 import java.io.*;


 public class AF_reflect   extends Block_Prototype_Fxy {
 static final long serialVersionUID =1L;
   double sc = 10.0;
   public AF_reflect() {
      super("AF_reflect", 1, 10);
   }

   public double f(double x, double y){
    Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
    if(g == null) return 0.5;
    double t = g.f(x,y);

    x = x+params[1]-params[2];
    y = y+params[3]-params[4];
    double a,b,w=0, s=0;


      if (params[0] < .025) {
        a=g.f(x, y);
        b=g.f(x, -y);
        w=a*a;
        s=b*b;
        t = (a*w+b*s)/(w+s+0.001);
      }
      else if (params[0] < .025) {
        a=g.f(x, y);
        b=g.f(x, -y);
        w=a*a;
        s=b*b;
        t = (b*w+a*s)/(w+s+0.001);

      }
      else {
        a=g.f(x, y);
         b=g.f(x, -y);
         w=a*a;
         w=w*w;
         s=b*b;
         s=s*s;
         t = (a*w+b*s)/(w+s+0.001);
      }



    return t;
 }

 }
