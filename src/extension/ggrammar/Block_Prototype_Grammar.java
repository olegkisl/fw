package extension.ggrammar;

/**
 * <p>Title: Images generator FW2003</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Company: </p>
 *
 * @author Oleg Kislyuk
 * @version 3.0
 */
import fw2003.*;
import java.util.*;
//import java.awt.image.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.*;
//import javax.swing.event.*;
import java.awt.geom.*;
//import java.awt.image.BufferedImage;
//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
import java.util.*;
//import javax.swing.tree.*;
import java.io.*;

public class Block_Prototype_Grammar extends Block_Prototype   {
  static final long serialVersionUID =1L;

  // random parameters [0.0 - 1.0]
  public double r0=0.5,r1=0.5,r2=0.5,r3=0.5,r4=0.5,r5=0.5,r6=0.5,r7=0.5,r8=0.5,r9=0.5,r10=0.5,r11=0.5;

  public Block_Prototype_Grammar(String name, int n_children) {
    super(name, name, n_children, Block_Prototype_Grammar.class);
    mutate_all();
  }

  protected void mutate_all(){
    r0 = FW_Rand.rand01();
    r1 = FW_Rand.rand01();
    r2 = FW_Rand.rand01();
    r3 = FW_Rand.rand01();
    r4 = FW_Rand.rand01();
    r5 = FW_Rand.rand01();
    r6 = FW_Rand.rand01();
    r7 = FW_Rand.rand01();
    r8 = FW_Rand.rand01();
    r9 = FW_Rand.rand01();
    r10 = FW_Rand.rand01();
    r11 = FW_Rand.rand01();
  }

  static double c_mut = 0.2;
  static double c_mut1 = 1.0-c_mut;

  protected void mutate_all_small(){
    r0 = c_mut1*r0+c_mut*FW_Rand.rand01();
    r1 = c_mut1*r1+c_mut*FW_Rand.rand01();
    r2 = c_mut1*r2+c_mut*FW_Rand.rand01();
    r3 = c_mut1*r3+c_mut*FW_Rand.rand01();
    r4 = c_mut1*r4+c_mut*FW_Rand.rand01();
    r5 = c_mut1*r5+c_mut*FW_Rand.rand01();
    r6 = c_mut1*r6+c_mut*FW_Rand.rand01();
    r7 = c_mut1*r7+c_mut*FW_Rand.rand01();
    r8 = c_mut1*r8+c_mut*FW_Rand.rand01();
    r9 = c_mut1*r9+c_mut*FW_Rand.rand01();
    r10 = c_mut1*r10+c_mut*FW_Rand.rand01();
    r11 = c_mut1*r11+c_mut*FW_Rand.rand01();
  }

  public void mutate_this(){
    if(FW_Rand.rand01()<FW_Parm.getMutateProb()*0.33)
      mutate_all();
    else
      mutate_all_small();
  }


  public void paint(FW_ImageContext ct, int depth) {
   if (depth <= 0) {
     return;
   }
   if(ct.isPaintStoped())
     return;
   int d = depth - 1;
   paintG(ct, new AffineTransform(), r1,r2,0.0,0.0,null);
 }


 public void paintG(FW_ImageContext ct, AffineTransform trn, double s, double b, double u, double v, Map map) {
   for (int i = 0; i < sons.length; i++) {
       actNode(i, ct, trn,   1.0,1.0, 0.0,0.0,  0.0,  s, b, u,v,    map);
   }
 }



 public void actNode(int nodeN, FW_ImageContext ct, AffineTransform tr,
                         double cx, double cy, double dx, double dy, double angle,
                         double s, double b, double u, double v, Map map){
  if (nodeN >= sons.length)
    return;
  if (sons[nodeN] == null)
    return;
  if (!(sons[nodeN] instanceof Block_Prototype_Grammar))
    return;
  AffineTransform to = AffineTransform.getTranslateInstance(dx, dy);
  AffineTransform t3 = AffineTransform.getRotateInstance(Math.PI*angle/180.0);
  AffineTransform t1 = AffineTransform.getScaleInstance(cx, cy);
  AffineTransform tt = (AffineTransform)(tr.clone());
//  AffineTransform t1 = new AffineTransform();
//  t1.setTransform(cx,0,0,cy,0,0);
  to.concatenate(t3);
  to.concatenate(t1);
  to.preConcatenate(tt);
  ((Block_Prototype_Grammar)sons[nodeN]).paintG(ct,to,s,b,u,v,map);

 }

 public void actNodeThis(FW_ImageContext ct, AffineTransform tr,
                        double cx, double cy, double dx, double dy, double angle,
                        double s, double b, double u, double v, Map map){

 AffineTransform to = AffineTransform.getTranslateInstance(dx, dy);
 AffineTransform t3 = AffineTransform.getRotateInstance(Math.PI*angle/180.0);
 AffineTransform t1 = AffineTransform.getScaleInstance(cx, cy);
 AffineTransform tt = (AffineTransform)(tr.clone());
// AffineTransform t1 = new AffineTransform();
// t1.setTransform(cx,0,0,cy,0,0);
 to.concatenate(t3);
 to.concatenate(t1);
 to.preConcatenate(tt);
 paintG(ct,to,s,b,u,v,map);
}

public double rand(double a, double b){
 double c=b-a;
 return  a + Math.random()*c;
}
public int select(int n1, double a1, int n2, double a2){
  double d=a1+a2;
  if(d<0.00001)
    return n1;
  if(Math.random()<(a1/d))
    return n1;
  return n2;
}

public int select(int n1, double a1, int n2, double a2, int n3, double a3){
  double d=a1+a2+a3;
  if(d<0.00001)
    return n1;
  if(Math.random()<(a1/d))
    return n1;
  if(Math.random()<(1-a3/d))
    return n2;
  return n3;
}


}
