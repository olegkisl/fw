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

// ROTATION
public class AF_COLOR_WW   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;

  int ar= 250,ag= 250,ab= 250,
      br =0, bg =0, bb=0,
      cr =110, cg =50, cb=50,
      dr =50, dg =110, db=50,
      er =50, eg =50, eb=110
      ;// colors

  public AF_COLOR_WW() {
     super("AF_COLOR_WW", 3, 5);
  }

  public void mutatePallet_this(FW_PalletInterface p){
 Color c,cw = Color.WHITE;
 c =p.getColor();
 ar = c.getRed();
 ag = c.getGreen();
 ab = c.getBlue();

 for (int i = 0; i < 10; i++) {
  cw =p.getColor();
  if(cw != c){
  br = cw.getRed();
  bg = cw.getGreen();
  bb = cw.getBlue();
  }
 }

  for (int i = 0; i < 10; i++) {
   cw =p.getColor();
   if(cw != c){
   cr = cw.getRed();
   cg = cw.getGreen();
   cb = cw.getBlue();
   }
  }

   for (int i = 0; i < 10; i++) {
    cw =p.getColor();
    if(cw != c){
    dr = cw.getRed();
    dg = cw.getGreen();
    db = cw.getBlue();
    }
   }

    for (int i = 0; i < 10; i++) {
     cw =p.getColor();
     if(cw != c){
     er = cw.getRed();
     eg = cw.getGreen();
     eb = cw.getBlue();
     }
    }
}


  public void paint(FW_ImageContext ct, int depth) {
   if (depth <= 0) {
     return;
   }
   int cfl=0;
   double r=0,r1=0,r2=0, rw=0.05,pp=0;
   //ct.pStart();
    startPainting(ct);
   int rr1;
   int gg1;
   int bb1;
   double bright  = FW_Parm.getBritness();
   while(ct.pNext()){
     if(ct.isPaintStoped()){
       ct.repaint();
       return;
     }
     r = f(ct.pX(),ct.pY(),0); // u*Math.sin(a*ct.pX())+v*Math.sin(b*ct.pY());
     r1 = f(ct.pX(),ct.pY(),1);
     r2 = f(ct.pX(),ct.pY(),2);
     
     rw=0.05;
     pp=r+r1+r2+rw;
     r=r/pp;
     r1=r1/pp;
     r2=r2/pp;
     rw=rw/pp;
     

     
       rr1 = (int) (ar * r + br * rw+r1*cr+r2*dr);
       gg1 = (int) (ag * r + bg * rw+r1*cg+r2*dg);
       bb1 = (int) (ab * r + bb * rw+r1*cb+r2*db);
       ct.pSet(rr1, gg1, bb1);
    


    // ct.pSet(rr1, gg1, bb1);
   }

 }



  public double f(double x, double y, int nn){ // just return son coeficient
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[nn];
   if(g == null) return 0.5;
   double r = g.f(x,y);
   if(r<0) r=-r;
   if(r>1) r=1.0/r;
   if(params[0]>0.1)r=r*r;
   if(params[0]>0.3)r=r*r;
   if(params[0]>0.5)r=r*r;
   if(params[0]>0.8)r=r*r;
   return r;
  }
  
 

}
