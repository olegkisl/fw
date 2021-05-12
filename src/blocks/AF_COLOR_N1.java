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
public class AF_COLOR_N1   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;

  int ar= 250,ag= 250,ab= 250,
      br =0, bg =0, bb=0,
      cr =110, cg =50, cb=50,
      dr =50, dg =110, db=50,
      er =50, eg =50, eb=110
      ;// colors

  public AF_COLOR_N1() {
     super("AF_COLOR_N1", 3, 5);
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
     
    rw=0.005;
     
     if((r>r1)&&(r2>r1)){
	   pp=r+r2+rw;
       r=r/pp;
       r1=r2/pp;
	   rr1 = (int) (ar * r + br * r1);
       gg1 = (int) (ag * r + bg * r1);
       bb1 = (int) (ab * r + bb * r1);
       ct.pSet(rr1, gg1, bb1);     
     }
     else if((r>r2)&&(r1>r2)){
	   pp=r+r1+rw;
       r=r/pp;
       r1=r1/pp;
	   rr1 = (int) (ar * r + cr * r1);
       gg1 = (int) (ag * r + cg * r1);
       bb1 = (int) (ab * r + cb * r1);
       ct.pSet(rr1, gg1, bb1);     
     }
     else {
	   pp=r1+r2+rw;
       r=r/pp;
       r1=r1/pp;
	   rr1 = (int) (ar * r + dr * r1);
       gg1 = (int) (ag * r + dg * r1);
       bb1 = (int) (ab * r + db * r1);
       ct.pSet(rr1, gg1, bb1);     
     }
     

     


    // ct.pSet(rr1, gg1, bb1);
   }

 }



  public double f(double x, double y, int nn){ // just return son coeficient
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[nn];
   if(g == null) return 0.5;
   double r = g.f(x,y);
   if(r<0) r=-r;
   if(r>1) r=1.0/r;
   if(params[0]>0.8)r=r*r;
   if(params[0]>0.9)r=r*r;
   return r;
  }
  
 

}
