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


public class Block_Prototype_Fclr extends Block_Prototype {
static final long serialVersionUID =1L;
static int  clrmax =255; // max color value
static double cmult = 1.0/clrmax;
//int ca= 250,cb= 250,cc= 250, caa =0, cbb =0, ccc=0;// colors
public double[] params = null;
////// added
PaintTransformation paintTransformation = null;
/////
public double         ar = 0.250, ag = 0.250, ab = 0.250,
               br = 0, bg = 0, bb = 0,
               cr = 0.110, cg = 0.50, cb = 0.50,
               dr = 0.50, dg = 0.110, db = 0.50,
               er = 0.50, eg = 0.50, eb = 0.110;// colors

Fclr fclr = new Fclr( 1.0, 1.0, 1.0 );

  // by default all sons are Fxy
  public Block_Prototype_Fclr(String name, int nson, int nparam) {
    super(name, name, nson, Block_Prototype_Fxy.class );
    params = new double[nparam];
    mutate_all();
   }
  // this son will be Fclr type istead of default Fxy
  public void setSonClr(int i) {
      classes[i] = Block_Prototype_Fxy.class;
  }

  public Fclr getFclr(double r, double g, double b) {
      fclr.r=r;
      fclr.g=g;
      fclr.b=b;
      return fclr;
  }

    public boolean mutatePallet1(FW_PalletInterface p) {
        if (p.getColorsList().size() > 1) {
            return false;
        }

        Color c = p.getColor();
        int n = FW_Rand.getCounter() % 5;
        switch (n) {
            case 0:
                ar = cmult * c.getRed();
                ag = cmult * c.getGreen();
                ab = cmult * c.getBlue();
                break;
            case 1:
                br = cmult * c.getRed();
                bg = cmult * c.getGreen();
                bb = cmult * c.getBlue();
                break;
            case 2:
                cr = cmult * c.getRed();
                cg = cmult * c.getGreen();
                cb = cmult * c.getBlue();
                break;
            case 3:
                dr = cmult * c.getRed();
                dg = cmult * c.getGreen();
                db = cmult * c.getBlue();
                break;
            case 4:
                er = cmult * c.getRed();
                eg = cmult * c.getGreen();
                eb = cmult * c.getBlue();
                break;

        }
        return true;
    }

    public void mutatePallet_this(FW_PalletInterface p) {
        if( mutatePallet1(p)) return;
        Color c, cw = Color.WHITE;
        c = p.getColor();
        ar = cmult * c.getRed();
        ag = cmult * c.getGreen();
        ab = cmult * c.getBlue();

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                br = cmult * cw.getRed();
                bg = cmult * cw.getGreen();
                bb = cmult * cw.getBlue();
            }
        }

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                cr = cmult * cw.getRed();
                cg = cmult * cw.getGreen();
                cb = cmult * cw.getBlue();
            }
        }

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                dr = cmult * cw.getRed();
                dg = cmult * cw.getGreen();
                db = cmult * cw.getBlue();
            }
        }

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                er = cmult * cw.getRed();
                eg = cmult * cw.getGreen();
                eb = cmult * cw.getBlue();
            }
        }
    }
////////////////////////////////////



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

/*  public void mutatePallet_this(FW_PalletInterface p){
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
}*/


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
 /*
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
*/

//////////// color function support  ////////////////////


  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    double r=0,g=0,b=0, rw=1;
    //ct.pStart();
    startPainting(ct);

    int rr;
    int gg;
    int bb;

    double bright  = FW_Parm.getBritness();
    Fclr cc;
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

      rr = (int) (clrmax * r);
      gg = (int) (clrmax * g);
      bb = (int) (clrmax * b);
      ct.pSet(rr, gg, bb);
    }

  }

  public Fclr fc(double x, double y) {
    return  getFclr( ar, ag, ab );
  }


}