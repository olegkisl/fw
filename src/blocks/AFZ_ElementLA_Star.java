package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFZ_ElementLA_Star   extends Block_Prototype_FxyP {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFZ_ElementLA_Star() {
     super("AFZ_ElementLA_Star", 2, 7);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t;// = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
//   double tt = gg.f(x,y);
   double ax,ay,az,aa,bb,uu,vv;

   double tr=10*params[3];
   ax=x*x+y*y;
   uu=y/Math.sqrt(0.0001+ax);
   uu = (1.1-Math.cos(params[1]+39*params[0]*uu))*(1.1+uu)*(1.1+uu);

   vv=(y*y/(params[2]+0.01+y*y))*params[3]/(0.001+10*params[4]*x*x);

   aa=g.f(x/uu,y/uu);
   aa=aa+g.f(-x/uu+0.2*params[4]*aa,y/uu);
   aa=aa*uu;

   if(y<0){
     bb=vv*g.f(x/vv,y/vv)+g.f(-x/vv,y/vv);
     aa=aa+bb;
   }

   if(aa<0)aa=100*aa;

 //  ay=x*x+(y-1)*(y-1);
 //  az=x*x+(y-0.3)*(y-0.3);
  // aa=ax;//+params[2]*ay+params[3]*az;


 /*
   bb=ax*params[2]+params[3]+0.0001;//+ay+az;
   aa = aa-bb;//Math.pow(bb,0.2);
   if(aa>tr){
     aa = tr-aa;
   }*/


 //  if(aa>tr){
 //    aa = -1;
 //  }
 //  if(aa>5.0) aa=-1;
   return aa;


  }

}
