package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class R_funcC
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_funcC() {
    super("R_funcC", 1);
    mutate_this();
  }

  int nnn =0;
  double distX=1, distY=1, distZ=1;

  public void mutate_this() {
   super.mutate_this();
   distX=params[0];
   distY=params[1];
   distZ=params[2];
  }

    public void mutateLarge_this() {
    nnn = FW_Rand.rand(22);
   }


 public  java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  Block_Prototype_R son =(Block_Prototype_R)sons[0];
  if(son == null) return vv;
  java.util.List v = son.action(k);
  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    func(item);
    vv.add(item);
    }
  return vv;
  }

  private void func(P2 p)
  {
  double x=p.x, y=p.y, x0,y0,r;
  r=x*x+y*y;
  if(r>distX){
    x0=r/x-1+r;
    y0=r/y-1+r;
  }
  else{
    x0=x;
    y0=y;
  }
//   x0= distX*Math.sin(0.3*distZ*x+params[6])/(1+5*params[4]*y*y);
 //  y0= distY*Math.cos(0.3*distZ*x+params[7])/(1+5*params[5]*y*y);
    p.x=x0;
    p.y=y0;
  }

}
