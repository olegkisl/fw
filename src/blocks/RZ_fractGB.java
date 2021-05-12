package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class RZ_fractGB
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public RZ_fractGB() {
    super("RZ_fractGB", 1);
    mutate_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private void setRot(int simt)
  {
  sim = 2*simt;
  hh = new Tr2[sim];
  Tr2 h=null, ha,hb;
  double d=0, cx=1,cy=1;
  double dw=0.85+params[3]*0.15;
  double cxw=0.85+params[4]*0.2;
  double cyw=0.85+params[5]*0.2;
  for(int i=0;i<sim; i++)
  {
   if(i%2==0){
     h = Tr2.scale(cx, cy);
     hb=Tr2.turn(0.0,0.0,10*(params[1]-params[2])*Math.random()+20*d);
   }
   else{
     h = Tr2.scale( -cx, cy);
     hb=Tr2.turn(0.0,0.0,-10*(params[1]-params[2])*Math.random()-20*d);
   }
 //  ha = Tr2.move(0.0,d);


   h=Tr2.mult(h,hb);
//   h=Tr2.mult(h,ha);
   hh[i]=h;
   d=dw*(d+1);
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
