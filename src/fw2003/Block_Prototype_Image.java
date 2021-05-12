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
import java.awt.geom.*;



public class Block_Prototype_Image extends Block_Prototype {
static final long serialVersionUID =1L;
public double[] params = new double[10];
transient BufferedImage img = null;
Tr2 hh= null;
AffineTransform tr = null;
int[] rgbArray = null;
int w = 1;
int h = 1;

public Block_Prototype_Image(String name,BufferedImage ima ) {
    super(name, name, 0, Block_Prototype.class );
    img = new
        BufferedImage(ima.getWidth(),ima.getHeight(), BufferedImage.TYPE_INT_ARGB);
    filterTransp(ima,img);
    w = ima.getWidth();
    h = ima.getHeight();
    double x1 = w*0.5;
    double x2 = x1;
    double y1 = h*0.99;
    double y2 = h*0.01;
    double cc = h*1.0;
    Tr2 hc = new Tr2();
    if(h>0)
      hc = Tr2.scale(1.0/cc,1.0/cc);
    hh = Tr2.sovm(
                  x1,y1,  x2,y2,
                  0.0,0.0,  0.0,1.0
                  );
    hh = Tr2.mult(hh,hc);
    tr = new AffineTransform(hh.h[0][0],hh.h[0][1],hh.h[1][0],
                                           hh.h[1][1],hh.h[2][0],hh.h[2][1]);
  }

  public void filterTransp(BufferedImage ima,BufferedImage imb){
   if(ima==null) return;
   int w = ima.getWidth();
   int h = ima.getHeight();
   if(w*h<=0) return;
   rgbArray = new int[w*h];
   rgbArray = ima.getRGB(0, 0,  w,  h,
                   rgbArray,  0,  w);
   int bgr = rgbArray[0];
   for (int i = 0; i < rgbArray.length; i++) {
     if(rgbArray[i]==bgr)
       rgbArray[i]=0;//transparent
     else
       rgbArray[i]=rgbArray[i]|0xff000000;//not transparent
   }
   imb.setRGB(0,  0,  w,  h,
              rgbArray,  0,  w);
 }

 void check(){
   if(img == null){
     img = new
        BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
     img.setRGB(0,  0,  w,  h,
              rgbArray,  0,  w);
   }
 }

 void mutate_all(){
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }




 // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p){
  Color c,cw = Color.WHITE;
  c =p.getColor();
//  ca = c.getRed();
//  cb = c.getGreen();
//  cc = c.getBlue();

  for (int i = 0; i < 10; i++) {
   cw =p.getColor();
   if(cw != c){
//   caa = cw.getRed();
//   cbb = cw.getGreen();
//   ccc = cw.getBlue();
   }
  }
}

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    check();
    AffineTransform trn = ct.getTransform();
    ct.normalizeContext();
    ct.getGraphics2D().drawImage(img,tr,null);
    ct.repaint();
    ct.setTransform(trn);
  }

}
