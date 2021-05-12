package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class R_fxyTransformB
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_fxyTransformB() {
    super("R_fxyTransformB", 3);
    classes[1] = Block_Prototype_Fxy.class;
    classes[2] = Block_Prototype_Fxy.class;
    mutate_this();
  }

  int nn =0;





  public void mutate_this() {
   super.mutate_this();

  }

  public void mutateLarge_this() {
  nn = FW_Rand.rand(2);
 }


 public  java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  Block_Prototype_R son =(Block_Prototype_R)sons[0];
  if(son == null) return vv;
  java.util.List v = son.action(k);

  Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[1];
  if(g == null) return v;
  Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[2];
  if(gg == null) return v;

  double xa, ya,z,zz;
  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    z=g.f(item.x,item.y);
    if(z<0) z=-z;
    if(z>1) z=1/z;
    zz=Math.sqrt(1-z*z);
    xa = z*item.x+zz*item.y;
    ya = -zz*item.x+z*item.y;

      item.x = xa;
      item.y = ya;
    }



  return v;
  }



}
//////////////

