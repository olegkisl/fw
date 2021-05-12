package blocks;




import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class RZ_flowerB
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public RZ_flowerB() {
    super("RZ_flowerB", 2);
    mutate_this();
    mutateLarge_this();
  }


  int simA =0;
  Tr2 [] hhA= null;
  double dd=0.0;

  private void setRotA(int simt)
  {
  simA = 2*simt;
  hhA = new Tr2[simA];
  Tr2 h=null, ha,hb;
  double d=0, cx=1,cy=1;
  double dw=0.85+params[3]*0.15;
  double cxw=0.85+params[4]*0.2;
  double cyw=0.85+params[5]*0.2;
  for(int i=0;i<simA; i++)
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
   hhA[i]=h;
   d=dw*(d+1);
   cx=cx*cxw;
   cy=cy*cyw;
  }
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
   if(i%2==0)
     h = Tr2.scale(cx,cy);
   else
     h = Tr2.scale(-cx,cy);
   ha = Tr2.move(0.0,d);
   hb=Tr2.turn(0.0,0.0,10*(params[1]-params[2])*Math.random());

   h=Tr2.mult(h,hb);
   h=Tr2.mult(h,ha);
   hh[i]=h;
   d=dw*(d+1);
   cx=cx*cxw;
   cy=cy*cyw;
  }
    dd=d;
  }


  public void mutate_this() {
   super.mutate_this();
  // int nn = FW_Rand.rand(5)+2;
  // setRot(nn);
  }

  public void mutateLarge_this() {
  int nn = FW_Rand.rand(4)+2;
  setRot(nn);
   nn = FW_Rand.rand(8)+2;
  setRotA(nn);
 }


 public  java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  Block_Prototype_R son =(Block_Prototype_R)sons[0];
  if(son == null) return vv;
  Block_Prototype_R son2 =(Block_Prototype_R)sons[1];
  if(son2 == null) return vv;

  java.util.List v = son.action(k);
  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
   // item.x+=params[0];
   // item.y+=params[1];
    for(int i=0;i<sim; i++){
      p = new P2(item);
      p.act(hh[i]);
      p.x=0.5*params[3]*Math.sin(params[2]*p.x);
      p.y=p.y-dd;
      vv.add(p);
    }
  }


      if(son2 != null)
      {v = son2.action(k);
       for (Iterator it = v.iterator(); it.hasNext(); ) {
        P2 item = (P2)it.next();
        for(int i=0;i<simA; i++){
         p = new P2(item);
         p.act(hhA[i]);

         vv.add(p);
       }
       }
      }



  return vv;
  }



}
