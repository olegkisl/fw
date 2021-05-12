package blocks;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFZ_protoA   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  double nn=5.0;

  public AFZ_protoA() {
     super("AFZ_protoA", 3, 9);
  }

  public void mutate_this(){
   super.mutate_this();
   nn=FW_Rand.rand(3)*2+3.0;
 }




  public double f(double x, double y){
  double ta=0,tb=0,tc=0,cc;
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g != null) ta=g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg != null) tb=gg.f(x,y);
   Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[2];
   if(ggg != null) tc=ggg.f(x,y);
   if((ta>1)||(ta<-1))
     ta=1/ta;
   if((tb>1)||(tb<-1))
     tb=1/tb;
   if((tc>1)||(tc<-1))
     tc=1/tc;

     x=x+ta;
   y=y+tb;
 //  cc=1+params[0]*ta;

  double a,b,c=0,d,e;


   a=2*params[1]*x*x+y*y+0.00001;
   b=2*params[0]*x*x+(y-1)*(y-1);
   c=2*params[2]*x*x+(y-0.5)*(y-0.5);
  // c=Math.cos(y/a)-params[0];


  e= f(20*params[4] - a*a - b -c)*tc*tc;
/*   d=Math.cos(nn*c+y+tb)*g.f(a,c)+params[2];
   if(d<0)
     d=0;

   e=f(1/a)*d;


   double a2,b2,c2=0,d2,e2;

   a2=(1-(25+params[3]*5)*x*x*tc*tc);
   if(y>0) a2=a2-10*y*params[4];
  // if(y<-3) a2=a2+10*(y+3)*params[5];;
   if(a2<0) a2=0;

 //  if(e<a2)
  //   e=a2;
  // else
  //  e=e;
*/
   return e;
  }



     double mod = 1.0;
     double coef = 4.5;
     double max = 12.0;
     double min = -12.0;

     private double f(double a){
      a = a*coef;
      if(a>max)  a= max;
      if(a<min)  a= min;
      return mod/(1.0+Math.exp(-a))-0.01;
    }


}
