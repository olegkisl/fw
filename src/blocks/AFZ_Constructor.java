package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class AFZ_Constructor   extends Block_Prototype_FxyP {
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
    nH= 1+FW_Rand.rand(5);
    mergeType=FW_Rand.rand(6)-1;

    /////////////////// Set H
    double c=0.4+params[4]*0.5, pp=params[5], s=0,cc=1, rf=1;
    for(int i =0; i<14; i++){
      cc=c*cc;
      rf=rf*(-0.7);
      dxx[i] =cc*rf;
      dyy[i] =cc;
      dyc[i]=  -s*cc;
      dxy[i] =0;
      dyx[i] =0;
      dxc[i]=0;
      s=cc*pp+s;
    }

    ////////////////////
  }

  public AFZ_Constructor() {
     super("AFZ_Constructor", 1, 7);
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
   t=-2;
   for(int j =0; j<nH; j++){
       xx= x*dxx[j]+y*dxy[j]+dxc[j];
       yy= x*dyx[j]+y*dyy[j]+dyc[j];
       tt = g.f(xx,yy);
       if(tt<=0) {
         ;
       }
       else if(nn==0){
         t=tt;
         nn=1;
       }
       else if(mergeType==0){
         if (tt > t)
          t = tt;
       }
       else if(mergeType==1){
         if (t < tt)
          t = tt;
       }
       else if(mergeType==2){
           t = tt*0.7+t;
       }
       else{
           t=tt+t;
       }
   }

   return t;

  }

}
