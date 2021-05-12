package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFZ_ElementLA_S   extends Block_Prototype_FxyP {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public AFZ_ElementLA_S() {
     super("AFZ_ElementLA_S", 2, 9);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t;// = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;


   double ax,aa,bb,w;
   double tr=10*params[3];

   ax=x*x+y*y;
   aa=g.f(x,y);
   if(aa<0)aa=-aa;
   bb=ax*params[2]+params[3]+0.0001;
   aa = aa-bb;
   if(aa>tr){
     aa = tr-aa;
   }


   w=aa;
   //shaddow
   x=x+params[4]-0.5+x*y*params[7]*0.3;
   y=y+params[5]-0.5+x+params[6]*0.6;
   ax=x*x+y*y;
   aa=g.f(x,y);
   if(aa<0)aa=-aa;
   bb=ax*params[2]+params[3]+0.0001;
   aa = aa-bb;
   if(aa>tr){
     aa = tr-aa;
   }

   if(aa>0.0)
     aa=-0.75;
   else if(aa>-1)
     aa=-0.75+aa*0.25;
   else
     aa=-1;

   if(w>-0.5){
     aa=w;
   }
   else if(w<-1){
     ;
   }
   else
     aa=w*0.5-0.5*w*aa;


   return aa;


  }

}
