package fw2003;

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
//
//   Takes Block_Prototype_Fxy as possible argument
//   use Block_Prototype_Fxy.f(x,y) to calculate B&W
//   implement Clr fc(double x, double y)
//


public class Block_Prototype_Fxy_C extends Block_Prototype {
static final long serialVersionUID =1L;
public double[] color_r = null;
public double[] color_g = null;
public double[] color_b = null;

public double[] params = null;

public Block_Prototype_Fxy_C(String name, int nson, int nparam) {
    super(name, name, nson, Block_Prototype_Fxy.class );
    params = new double[nparam];
    color_r = new double[nparam];
    color_g = new double[nparam];
    color_b = new double[nparam];
    initClr();
    mutate_all();
  }

private void initClr(){
    for (int i = 0; i < color_r.length; i++) {
     color_r[i] = 1.0*(i%2);
     color_g[i] = 1.0*(i%2);
     color_b[i] = 1.0*(i%2);
   }


}

 void mutate_all(){
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }


  public void mutate_this(){
    if(FW_Rand.rand01()<FW_Parm.getMutateProb()*0.33)
      mutate_all(); // reinitiate
    for (int i = 0; i < params.length; i++) {
     if(FW_Parm.getMutateProb()>FW_Rand.rand01())
          params[i] = params[i] + 0.1*(FW_Rand.rand01()-0.5);
    }
  }

 // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p){
  Color cw;
  for (int i = 0; i < color_r.length;  i++) {
   cw =p.getColor();
   color_r[i] = cw.getRed()/255.0;
   color_g[i] = cw.getGreen()/255.0;
   color_b[i] = cw.getBlue()/255.0;
  }
}

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    double r=0,g=0,b=0, rw=1;
    ct.pStart();
    int nmax =255;
    int rr;
    int gg;
    int bb;

    double bright  = FW_Parm.getBritness();
    Clr cc;
    while(ct.pNext()){
      if(ct.isPaintStoped()){
        ct.repaint();
        return;
      }

      cc = fc(ct.pX(),ct.pY());

      r = cc.r;
      if(r<0) r = -r;
      if(r>1) r=1.0/r;
      r = r * bright;
      if(r<0) r = r+1.0;
      if(r>1) r=  0.9999;

      g = cc.g;
      if(g<0) g = -g;
      if(g>1) g=1.0/g;
      g = g * bright;
      if(g<0) g = g+1.0;
      if(g>1) g=  0.9999;

      b = cc.b;
      if(b<0) b = -b;
      if(b>1) b=1.0/b;
      b = b * bright;
      if(b<0) b = b+1.0;
      if(b>1) b=  0.9999;

      rr = (int) (nmax * r);
      gg = (int) (nmax * g);
      bb = (int) (nmax * b);
      ct.pSet(rr, gg, bb);
    }

  }

  public Clr fc(double x, double y) {
    return  new Clr(0.0,0.0,0.0);
  }




}

