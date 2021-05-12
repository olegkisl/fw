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

public class R_funcA
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_funcA() {
    super("R_funcA", 1);
    mutate_this();
  }

  int nnn =0;
  double distX=1, distY=1, distZ=1;

  public void mutate_this() {
   super.mutate_this();
   distX=params[0];
   distY=params[1];
   distZ=params[2];
  }

    public void mutateLarge_this() {
    nnn = FW_Rand.rand(22);
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
      x0 = x*(1.5 + x*x - y*y)/(0.1+distZ + distX*x*x + distY*y*y );
      y0 = y+0.2*x;
      break;
    case 1:
      x0 = x*(1.7 +y-0.2*x)/(0.1+distZ + distY*y*y );
      y0 = y*(x*x+y*y+5)/(x*x+y*y+1+distZ);
      break;
     case 2:
      x0 = 0.2*x*x+x/(y*y+0.1+distY);
      y0 = y/(x*x+0.1+distZ);
      break;
      case 3:
      x0 = distY*x/(distX*y*y+1);
      y0 = y+x*distZ;
      break;
      case 4:
      x0 = y*(distX+0.3)/(y*y+0.1);
      y0 = 2*(distY)/(x*x+0.1+distZ);
      break;
      case 5:
      x0 = 0.7*distX*x*y;
      y0 = 0.7*distY*(x*x+y*y);
      break;
      case 6:
      x0 = distX/(Math.abs(x)+0.4)+0.2*distZ*y;
      y0 = distY/(Math.abs(y)+0.4);
      break;
      case 7:
      x0 = y*distX-0.5*x/(Math.abs(x*y-y)+0.4);
      y0 = (1.0+x*x*distY-x)/(Math.abs(y*y-distZ*x*x)+0.4);
      break;
       case 8:
      x0 = distX*y*x+x/(1.0+x*x);
      y0 = distY*(Math.sqrt(0.01+x*x+y*y));
      break;
       case 9:
      x0 = 1/(x*x+distX*0.43)+y/(Math.abs(x*x-y*y+distX)+1.25);
      y0 = (distY*x-0.2*y)/(Math.abs(y-x*x)+0.44);
      break;
       case 10:
      x0 = -y*(x+y)*distY;
      y0 = x*x*distZ+y;
      break;
       case 11:
      x0 = -x*distX/(Math.abs(x*y+y)+0.5);
      y0 = 0.2*x-y*distY/(Math.abs(y*x+x+y*y+y)+0.5);
      break;
      case 12:
      x0 = (y-1.0)*(distX+x);
      y0 = x*x*distY;
      break;
      case 13:
      x0 = x+Math.sqrt(distZ*y*y+0.001);
      y0 = y+Math.sqrt(distX*x*x+distY*y*y+0.001);
      break;
      case 14:
      x0 =distZ*x+Math.cos(distX*x*x+6.0*y);
      y0 = Math.cos(distY*y-12*y*y+x);;
      break;
       case 15:
      x0 =distZ*x+Math.cos(distX*x*x+6.0*y+y*y*x);
      y0 =y+0.2*x+ Math.cos(distY*y*y-12*x*y+x);;
      break;
      case 16:
      x0 =distZ*x+Math.cos(0.7*y+distX*x+12.0*y*y);
      y0 =-y+distZ*Math.cos(distY*y*x-11*y*y+x);;
      break;
       case 17:
      x0 =0.25*distZ/(x*x+0.3)+Math.cos(7*distX*x+12.0*y);
      y0 =0.1*x*y+ Math.cos(4*distY*y*x-11*y*y+x);;
      break;
       case 18:
      x0 =x+Math.cos(distX*x+11.0*y*y);
      y0 =distZ*y*y+ Math.cos(distY*y*x*x-4*y*y*x);;
      break;
      case 20:
      x0 =x*Math.cos(5.0*distX*y);
      y0 =x*Math.cos(5.0*distY*y);;
      break;
      case 21:
      x0 =x*x+x*Math.cos(15.0*distX*y);
      y0 =y*y+x*Math.cos(15.0*distY*y);;
      break;
      case 22:
      x0 =x+x*Math.cos(65.0*distX*y+x);
      y0 =y+x*Math.cos(45.0*distY*y);
      break;
    default:
      x0=1.0/(0.3+x*x+y*y);
      y0=distY*(x*x-y*y);
      break;
  }
    p.x=x0;
    p.y=y0;
  }

}
