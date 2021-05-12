package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class R_fractZOOM
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_fractZOOM() {
    super("R_fractZOOM", 1);
    mutate_this();
    mutateLarge_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private void setRot(int simt)
  {
  sim = simt;
  hh = new Tr2[sim];
  Tr2 h=null, h2=null, h3=null,h4=null;

  h2 = Tr2.turn(0.0,0.0,params[5]);

  double c1=0.7*params[6]+0.3;
  double c2=1,c3=0;
  double cc=0.2;
  for(int i=0;i<sim; i++)
  {
    if(params[4]<0.5)
      h = Tr2.scale(-c2,c2);
    else
      h = Tr2.scale(c2,c2);
   hh[i]=h;
   c2=c2*c1;
  }
  }


  public void mutate_this() {
   super.mutate_this();
  // int nn = FW_Rand.rand(5)+2;
  // setRot(nn);
  }

  public void mutateLarge_this() {
  mutate_this();
  int nn = FW_Rand.rand(5)+2;
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
//////////////

