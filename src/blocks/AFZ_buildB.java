package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFZ_buildB   extends Block_Prototype_FxyP {
static final long serialVersionUID =1L;
  double sc = 10.0;
  double[] dxx = new double[14];
  double[] dxy = new double[14];
  double[] dxc = new double[14];
  double[] dyx = new double[14];
  double[] dyy = new double[14];
  double[] dyc = new double[14];
  int mergeType=0;
  int nH=5;

  public void createH(){
    nH= 1+FW_Rand.rand(3);
    mergeType=FW_Rand.rand(4)-1;

    /////////////////// Set H
    double c=0.6+params[4]*0.5,cc=1, alfa=params[5]*0.3 ;

    for(int i =0; i<14; i++){
      cc=c*cc;

      dxx[i] =cc;
      dxy[i] =cc*Math.sin(alfa*i);
      dxc[i] = cc*Math.sin(alfa*i*i);

      dyx[i] =cc;
      dyy[i] =0;
      dyc[i]= cc*Math.cos(alfa*i);;

    }

    ////////////////////
  }

  public AFZ_buildB() {
     super("AFZ_buildB", 1, 7);
     createH();

  }


  public void  mutateLarge_this(){
   super.mutate_this();
   createH();
  }


  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;

   double t , xx, yy, tt;;
   int nn=0;
   t=0;

   double u = 2.5*params[1]*x*x; //space restrictor
   if(y>u){
     u=1;
   }
   else{
     u=u-y;
     u=params[3]/(params[3]+0.01+params[2]*u*u);
   }

   for(int j =0; j<nH; j++){
       xx= x*dxx[j]+y*x*dxy[j]+y*y*x*dxc[j];
       yy= y*dyy[j]+y*y*dyc[j];
       tt = g.f(xx,yy)*u;

       if(nn==0){
         t=tt;
         nn=1;
       }
       else if(mergeType==1){
         if (tt > t)
          t = tt;
       }
       else if(mergeType==0){
         if (tt > t)
          t = t*tt;
       }
       else{
           t=tt+t;
       }

       tt = g.f(-xx,yy)*u;//simmetry
       if(mergeType==1){
         if (tt > t)
          t = tt;
       }
       else if(mergeType==0){
         if (tt > t)
          t = t*tt;
       }
       else{
           t=tt+t;
       }

   }

   return t;

  }

}
