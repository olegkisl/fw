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
import java.awt.image.*;

public class Block_Prototype_Op
    extends Block_Prototype {
  static final long serialVersionUID = 1L;
  int ca = 250, cb = 250, cc = 250, caa = 0, cbb = 0, ccc = 0; // colors
  public double[] params = null;

  public Block_Prototype_Op(String name, int nson) {
    super(name, name, nson, Block_Prototype_Op.class);
    int nparam = 25;
    params = new double[nparam];
    mutate_all();
  }

  void mutate_all() {
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }

  public void mutate_this() {
    if (FW_Rand.rand01() < FW_Parm.getMutateProb() * 0.33) {
      mutate_all();
    }
    for (int i = 0; i < params.length; i++) {
      if (FW_Parm.getMutateProb() > FW_Rand.rand01()) {
        params[i] = params[i] + 0.1 * (FW_Rand.rand01() - 0.5);
      }
    }
  }

  // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p) {
    Color c, cw = Color.WHITE;
    c = p.getColor();
    ca = c.getRed();
    cb = c.getGreen();
    cc = c.getBlue();

    for (int i = 0; i < 10; i++) {
      cw = p.getColor();
      if (cw != c) {
        caa = cw.getRed();
        cbb = cw.getGreen();
        ccc = cw.getBlue();
      }
    }
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    oper(ct);

    if (ct.isPaintStoped()) {
      ct.repaint();
      return;
    }
    int d = depth - 1;
    for (int i = 0; i < sons.length; i++) {
      if (sons[i] != null) {
        sons[i].paint(ct, d);
      }
    }
  }

  public void oper(FW_ImageContext ct){
    BufferedImage ima = ct.getImage();
    if(ima==null) return;
    int w = ima.getWidth();
    int h = ima.getHeight();
    int[] rgbArray = new int[w*h];
    int[] rgbArrayDest = new int[w*h];

    rgbArray = ima.getRGB(0, 0,  w,  h,
                    rgbArray,  0,  w);
    int p00, p01, p02, p10, p11, p12, p20, p21, p22;
    for (int y = 0; y < h; y++) {
    for (int x = 0; x < w; x++) {
      if((x==0)||(y==0)||(x==(w-1))||(y==(h-1)))
        rgbArrayDest[y*w + x]=rgbArray[y*w + x];
      else
        rgbArrayDest[y*w + x] =operPix(x,y,
         rgbArray[(y-1)*w + x-1], rgbArray[(y-1)*w + x], rgbArray[(y-1)*w + x+1],
         rgbArray[(y)*w + x-1],   rgbArray[(y)*w + x],   rgbArray[(y)*w + x+1],
         rgbArray[(y+1)*w + x-1], rgbArray[(y+1)*w + x], rgbArray[(y+1)*w + x+1]
                                       );

    }

    }

    ima.setRGB(0,  0,  w,  h,
               rgbArrayDest,  0,  w);
  }

  public int operPix(int x, int y,
      int p00,int  p01,int  p02,int  p10,int  p11,int  p12,int  p20,int  p21,int  p22){

    float r = operPixB(x,y,
                       ((p00>>16) & 0xff)/255.0f,((p01>>16) & 0xff)/255.0f,((p02>>16) & 0xff)/255.0f,
                       ((p10>>16) & 0xff)/255.0f,((p11>>16) & 0xff)/255.0f,((p12>>16) & 0xff)/255.0f,
                       ((p20>>16) & 0xff)/255.0f,((p21>>16) & 0xff)/255.0f,((p22>>16) & 0xff)/255.0f
                       );
    float g = operPixB(x,y,
                       ((p00>>8) & 0xff)/255.0f,((p01>>8) & 0xff)/255.0f,((p02>>8) & 0xff)/255.0f,
                       ((p10>>8) & 0xff)/255.0f,((p11>>8) & 0xff)/255.0f,((p12>>8) & 0xff)/255.0f,
                       ((p20>>8) & 0xff)/255.0f,((p21>>8) & 0xff)/255.0f,((p22>>8) & 0xff)/255.0f
                       );
    float b = operPixB(x,y,
                       ((p00) & 0xff)/255.0f,((p01) & 0xff)/255.0f,((p02) & 0xff)/255.0f,
                       ((p10) & 0xff)/255.0f,((p11) & 0xff)/255.0f,((p12) & 0xff)/255.0f,
                       ((p20) & 0xff)/255.0f,((p21) & 0xff)/255.0f,((p22) & 0xff)/255.0f
                       );
    int nr = (int)(255.0f*r);
    int ng = (int)(255.0f*g);
    int nb = (int)(255.0f*b);
    if(nr<0) nr=0;
    if(nr>255) nr =255;
    if(ng<0) ng=0;
    if(ng>255) ng =255;
    if(nb<0) nb=0;
    if(nb>255) nb =255;

    return 256*256*nr+256*ng+nb;
  }

   public float operPixB(int x, int y,
      float p00,float  p01,float  p02,float   p10,float  p11,float   p12,float   p20,float   p21,float   p22){
     return p11;
   }

}
