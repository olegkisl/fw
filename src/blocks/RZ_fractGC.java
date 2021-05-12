package blocks;



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class RZ_fractGC
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public RZ_fractGC() {
    super("RZ_fractGC", 3);
   classes[1] = Block_Prototype_Fxy.class;
   classes[2] = Block_Prototype_Fxy.class;

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
  double dw=0.9+params[3]*0.2;
  double cxw=0.9+params[4]*0.2;
  double cyw=0.9+params[5]*0.2;
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
  Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[1];
  if(g == null) return v;
  Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[2];
  if(gg == null) return v;

  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    double xa, ya,z,zz;


    for(int i=0;i<sim; i++){
      p = new P2(item);
      p.act(hh[i]);
      z=g.f(p.x,p.y);
      zz =gg.f(p.x,p.y);
      if(z<-1) z=1/z;
      if(z>1) z=1/z;
       z=z*0.2;
      if(zz<-1) zz=1/zz;
      if(zz>1) zz=1/zz;
      zz=zz*0.2;
      ///////////////////
      p.x = 0.2*p.x+zz;
      p.y = p.y+p.y*z;
      //////////////////
   //   p.x=params[3]*Math.sin(params[2]*p.x);
      vv.add(p);
    }

  }
  return vv;
  }



}
