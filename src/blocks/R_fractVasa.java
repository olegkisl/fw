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

public class R_fractVasa
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_fractVasa() {
    super("R_fractVasa", 1);
    mutateLarge_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private Tr2 make(double xx, double yy, double ang, double cx, double cy) {
    Tr2 t = Tr2.turn(0.0, 0.0, ang);
    Tr2 t1 = Tr2.move(xx,yy);
    Tr2 t2 = Tr2.scale(cx,cy);
    t = Tr2.mult(t, t1);
    t = Tr2.mult(t, t2);
    return t;
  }




  private void setRot(int nh,int nn)
  {
  sim = nh*nn*2;
  hh = new Tr2[sim];
  double r1 = FW_Rand.rand01();
  double r2 = FW_Rand.rand01();
  double r3 = FW_Rand.rand01();
  double r4 = FW_Rand.rand01();
  double r5 = FW_Rand.rand01();
  double r6 = FW_Rand.rand01();
  double r7 = FW_Rand.rand01();
  double r8 = FW_Rand.rand01();
  int mm=0;
  double bb=0.0;
  for (int i = 0; i < nh; i++) {
    bb = r1+r6*0.05*i*i+r7*Math.cos(r8*i);
    for (int j = 0; j < nn; j++) {
      hh[mm] = make(bb*r1, i*r2, 3.0*(r3*j+r4*i),1.0,1.0);
      mm++;
    }
    for (int j = 0; j < nn; j++) {
      hh[mm] = make(bb*r1, i*r2, 3.0*(r3*j+r4*i),-1.0,1.0);
      mm++;
    }
  }


  }


  public void mutate_this() {
   super.mutate_this();
  // int nn = FW_Rand.rand(5)+2;
  // setRot(nn);
  }

  public void mutateLarge_this() {
  int nn = FW_Rand.rand(10)+2;
  int nnn = FW_Rand.rand(7)+2;
  setRot(nn,nnn);
 }


 public  java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  Block_Prototype_R son =(Block_Prototype_R)sons[0];
  if(son == null) return vv;
  java.util.List v = son.action(k);
  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    item.x+=params[0];
    item.y+=params[1];
    for(int i=0;i<sim; i++){
      p = new P2(item);
      p.act(hh[i]);
      vv.add(p);
    }

  }
  return vv;
  }



}
//////////////

