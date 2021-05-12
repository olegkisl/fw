package blocks;






import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class R_cloudMod
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_cloudMod() {
    super("R_cloudMod", 1);
    mutate_this();
    mutateLarge_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private void setRot(int simt)
  {
  sim = 4;
  hh = new Tr2[sim];
  Tr2 h=null;


  for(int i=0;i<sim; i++)
  {
   h = Tr2.scale(params[2*i]+0.45,params[2*i+1]+0.45);
   hh[i]=Tr2.move(params[2*i]-params[2*i+1],params[i]-params[i+1]);
   hh[i]=Tr2.mult(h,hh[i]);
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
