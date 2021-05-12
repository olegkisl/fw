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

public class R_treeA
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_treeA() {
    super("R_treeA", 1);
    mutate_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private void setRot(int simt)
  {
  sim = simt;
  hh = new Tr2[sim];
  Tr2 h=null,ha=null, hb;
  double c=1.0, d=0.0;
  for(int i=0;i<sim; i++)
  {
   c = c*(0.5+params[3]*0.55);
   d = d + (0.3+params[4])*c;
   if(i%2==0)
     h = Tr2.scale( -c, c);
   else
     h = Tr2.scale( c, c);
   hb = Tr2.turn(0.5,0.5,(double)((90.0*params[0])));
   hb = Tr2.mult(h,hb);
   ha = Tr2.move(0.0,d);
   hh[i]=Tr2.mult(hb,ha);
  }
  }


  public void mutate_this() {
   super.mutate_this();
  // int nn = FW_Rand.rand(5)+2;
  // setRot(nn);
  }

  public void mutateLarge_this() {
  int nn = FW_Rand.rand(6)+2;
  mutate_this();
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

