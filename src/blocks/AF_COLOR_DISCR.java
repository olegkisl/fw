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
public class AF_COLOR_DISCR   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;

  int ar= 250,ag= 250,ab= 250,
      br =0, bg =0, bb=0,
      cr =110, cg =50, cb=50,
      dr =50, dg =110, db=50,
      er =50, eg =50, eb=110
      ;// colors

  public AF_COLOR_DISCR() {
     super("AF_COLOR_DISCR", 1, 5);
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
   double r=0, rw=1;
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
     r = f(ct.pX(),ct.pY()); // u*Math.sin(a*ct.pX())+v*Math.sin(b*ct.pY());

     if(r<0) {r = -r; cfl=1;}
     else cfl=2;

     if(r>1) {r=1.0/r;cfl =-cfl;}
     
     r=r*r;
     r=r*r;
     r=r*r;//////////////////////////

     r = r * bright;
     if(r<0) r = r+1.0;
     if(r>1) r=  0.9999;

     rw= 1-r;
     if(cfl==1){
       rr1 = (int) (ar * r + br * rw);
       gg1 = (int) (ag * r + bg * rw);
       bb1 = (int) (ab * r + bb * rw);
     }
     else if(cfl==2){
       rr1 = (int) (ar * r + cr * rw);
       gg1 = (int) (ag * r + cg * rw);
       bb1 = (int) (ab * r + cb * rw);
     }
     else if(cfl==(-1)){
       rr1 = (int) (ar * r + dr * rw);
       gg1 = (int) (ag * r + dg * rw);
       bb1 = (int) (ab * r + db * rw);
     }
     else {
       rr1 = (int) (ar * r + er * rw);
       gg1 = (int) (ag * r + eg * rw);
       bb1 = (int) (ab * r + eb * rw);
     }


     ct.pSet(rr1, gg1, bb1);
   }

 }



  public double f(double x, double y){ // just return son coeficient
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   return g.f(x,y);
  }

}
