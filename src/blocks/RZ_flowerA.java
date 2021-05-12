package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class RZ_flowerA
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public RZ_flowerA() {
    super("RZ_flowerA", 2);
    mutate_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private void setRot(int simt)
  {
  sim = 2;
  hh = new Tr2[sim];
  Tr2 h=null, ha,hb;
  double d=0, cx=1,cy=1;
  double dw=0.85+params[3]*0.15;
  double cxw=0.85+params[4]*0.2;
  double cyw=0.85+params[5]*0.2;
  for(int i=0;i<sim; i++)
  {
   if(i%2==0)
     h = Tr2.scale(cx,-cy);
   else
     h = Tr2.scale(-cx,-cy);
   ha = Tr2.move(0.0,d);
   hb=Tr2.turn(0.0,0.0,10*(params[1]-params[2])*Math.random());

   h=Tr2.mult(h,hb);
   h=Tr2.mult(h,ha);
   hh[i]=h;
   d=dw*(d+0.2);
   cx=cx*cxw;
   cy=cy*cyw;
  }
  }


  public void mutate_this() {
   super.mutate_this();
  // int nn = FW_Rand.rand(5)+2;
  // setRot(nn);
  }

  public void mutateLarge_this() {
  int nn = FW_Rand.rand(8)+2;
  setRot(nn);
 }


 public  java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  Block_Prototype_R son =(Block_Prototype_R)sons[0];
  if(son == null) return vv;
  Block_Prototype_R son2 =(Block_Prototype_R)sons[1];
  if(son2 == null) return vv;

  java.util.List v = son.action(k);
  P2 p = null;
  double tt=params[0]*0.1;
  if(tt<0.001) tt=0.1;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    item.x+=params[0];
    item.y+=params[1];
    for(int i=0;i<sim; i++){
      p = new P2(item);
      p.act(hh[i]);
      if(p.y>0.02)
        p.y=-p.y;
      if(p.x>tt)
        p.x=tt/p.x;
      if(p.x<-tt)
      p.x=tt/p.x;
      vv.add(p);
    }

  }

  if(son2 != null)
  {v = son2.action(k);
   for (Iterator i = v.iterator(); i.hasNext(); ) {
    P2 item = (P2)i.next();
    p = new P2(item);
    p.x+=0.0;//distX;
    p.y+=0.0;//distY;
    vv.add(p);
   }
  }


  return vv;
  }



}
