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

public class R_funcB
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;
 double xa,ya;
  public R_funcB() {
    super("R_funcB", 1);
    mutate_this();
  }

  int nnn =0;
  double aa=1, ab=1, ac=1, ad =1, ae =1, af=1, ag =1;

  public void mutate_this() {
   super.mutate_this();
   aa=params[0];
   ab=params[1];
   ac=params[2];
   ad=params[3];
   ae=params[4];
   af=params[5];
   ag=params[6];
  }

  public void mutateLarge_this() {
   nnn = FW_Rand.rand(14);
  }


 public  java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  Block_Prototype_R son =(Block_Prototype_R)sons[0];
  if(son == null) return vv;
  java.util.List v = son.action(k);
  P2 p = null;
  for (Iterator it = v.iterator(); it.hasNext(); ) {
    P2 item = (P2)it.next();
    func(item);
    vv.add(item);
    }
  return vv;
  }

  private void func(P2 p)
  {
  double x=p.x, y=p.y, x0,y0;
  switch (nnn) {
    case 0:
      x0 = aa*Math.sin(ab+3*ac*x)*y;
      y0 = ad*Math.cos(ae+3*af*x)*y;
      break;
    case 1:
      x0 = (aa*x+ab*y)/(0.01+ac+ad*y*y);
      y0 = ae*y+af;
      break;
     case 2:
      x0 = aa*Math.sin(ab+3*ac*x+af*y)*y;
      y0 = -y/(ad*x*x+0.1);
      break;
      case 3:
      x0 = aa*x*y;
      y0 = ab*x*x-ac*y*y+af;
      break;
      case 4:
      x0 = Math.sqrt(aa*y*y+ab*x*x+ae);
      y0 = ac*x+af*y;
      break;
      case 5:
      x0 = aa*Math.sin(1/(ab+3*ac*x*x))*y;
      y0 = ad*(x*(x-af)+y*(y-ag));
      break;
      case 6:
      x0 = aa*x-ab*y;
      y0 = ad*x+ae*y;
      break;
      case 7:
      x0 = aa*y+ab*x/(ad*Math.sin(x*y-y*ac)+1.01);
      y0 = af*x*y+ag*y;
      break;
       case 8:
      x0 = aa*x+x/(1.0+x*x+ac);
      y0 = ad*Math.sin(af*x+ag+ab*y);
      break;
      case 9:
      x0 = ad*Math.sin(af*x+ag);
      y0 = y*ab*Math.sin(ae*y+af);;
      break;
      case 10:
      x0 = -aa*(x+ab*y);
      y0 = x*x*ac+y*ad+x*y*ae;
      break;
       case 11:
      xa = Math.sin(3*aa*x+ab);
      ya = Math.cos(3*ac*x+ad);
      x0 = ae*xa*xa*y;
      y0 = ag*ya+af*xa*x;
      break;
      case 12:
      x0 = aa*Math.sin(ab+3*ac*x*y)*x;
      y0 = ad*Math.cos(ae+3*af*x)*y;
      break;
      case 13:
      x0 = aa*Math.sin(ab+3*ac*x/(y*y+0.1+ag))*y;
      y0 = ad*Math.cos(ae+3*af*x)*y*x;
      break;
      case 14:
      x0 =aa*x*x*x-ab*x*y;
      y0 = ac*x+ad*y;
      break;

    default:
      x0=ac*x+ad*y;
      y0=aa*y+ab*x;
      break;
  }
    p.x=x0;
    p.y=y0;
  }

}
