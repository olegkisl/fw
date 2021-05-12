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

public class RZ_Buket
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public RZ_Buket() {
    super("RZ_Buket", 3);
    classes[1] = Block_Prototype_Fxy.class;
    classes[2] = Block_Prototype_Fxy.class;
    mutate_this();
  }

  int sim =0;


  private void setRot(int simt)
  {
  sim = simt;
  }


  public void mutate_this() {
   super.mutate_this();
  // int nn = FW_Rand.rand(5)+2;
  // setRot(nn);
  }

  public void mutateLarge_this() {
  int nn = FW_Rand.rand(6)+1;
  mutate_this();
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
  double xa,ya;
  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    //item.x+=params[0];
    item.y+=params[1]*3.0;
    if(item.y<0.0)
     continue;
    for(int i=0;i<sim; i++){
      p = new P2(item);
      p.x=p.x+Math.pow(p.y,params[i]+0.1);
      if(i%2==0)
        p.x=-p.x;
      xa = g.f(p.x,p.y);
      ya = gg.f(p.x,p.y);
      if((xa>1)||(xa<-1)) xa = 1/xa;
      if((ya>1)||(ya<-1)) ya = 1/ya;
      p.x=p.x+xa*params[2];
      p.y=p.y+ya*params[3];
      p.x=p.x*(params[5]+0.5);
      p.y=p.y*(params[6]+0.5);
      vv.add(p);
    }

  }
  return vv;
  }



}
//////////////

