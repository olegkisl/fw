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


public class Block_Prototype_Fxy extends Block_Prototype {
static final long serialVersionUID =1L;
int ca= 250,cb= 250,cc= 250, caa =0, cbb =0, ccc=0;// colors
public double[] params = null;
////// added
PaintTransformation paintTransformation = null;
/////

public Block_Prototype_Fxy(String name, int nson, int nparam) {
    super(name, name, nson, Block_Prototype_Fxy.class );
    params = new double[nparam];
    mutate_all();
  }

 void mutate_all(){
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }


  public void mutate_this(){
    if(FW_Rand.rand01()<FW_Parm.getMutateProb()*0.33)
      mutate_all();
    for (int i = 0; i < params.length; i++) {
     if(FW_Parm.getMutateProb()>FW_Rand.rand01())
          params[i] = params[i] + 0.1*(FW_Rand.rand01()-0.5);
    }
  }

 // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p){
  Color c,cw = Color.WHITE;
  c =p.getColor();
  ca = c.getRed();
  cb = c.getGreen();
  cc = c.getBlue();

  for (int i = 0; i < 10; i++) {
   cw =p.getColor();
   if(cw != c){
   caa = cw.getRed();
   cbb = cw.getGreen();
   ccc = cw.getBlue();
   }
  }
}


  public void startPainting(FW_ImageContext ct){
    if(FW_Parm.getPaintMode()==1){
        paintTransformation = ct.getPaintTransformation();
    }
    if(FW_Parm.getPaintMode()==2 && paintTransformation != null){
       ct.pStart(paintTransformation);
    }else{
       ct.pStart();
    }
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    double r=0, rw=1;
    //ct.pStart();
    startPainting(ct);
    int rr;
    int gg;
    int bb;

    double bright  = FW_Parm.getBritness();
    while(ct.pNext()){
      if(ct.isPaintStoped()){
        ct.repaint();
        return;
      }
      r = f(ct.pX(),ct.pY()); // u*Math.sin(a*ct.pX())+v*Math.sin(b*ct.pY());

      if(r<0) r = -r;
      if(r>1) r=1.0/r;

      r = r * bright;
      if(r<0) r = r+1.0;
      if(r>1) r=  0.9999;

      rw= 1-r;
      rr = (int) (ca * r + caa*rw);
      gg = (int) (cb * r + cbb*rw);
      bb = (int) (cc * r + ccc*rw);
      ct.pSet(rr, gg, bb);
    }

  }

  public double f(double x, double y) {
    return (x+y)*(x+y);

  }


}