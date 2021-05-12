package blocks;





import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class R_fractPsevdo
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_fractPsevdo() {
    super("R_fractPsevdo", 1);
    mutate_this();
    mutateLarge_this();
  }

  int sim =0;
  Tr2 [] hh= null;

  private void setRot(int simt)
  {
  sim = simt;
  hh = new Tr2[sim];
  Tr2 h=null;
  if(params[3]>0.5)
     h = Tr2.scale(-1,1);
   else
     h = Tr2.scale(1,1);

  for(int i=0;i<sim; i++)
  {
   hh[i]=Tr2.shiftX(params[2]);
   if(i%2==1)
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
  int nn = FW_Rand.rand(2)+2;
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
