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

public class R_addA
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;

  public R_addA() {
    super("R_addA", 2);
    mutate_this();
  }

  int nnn =0;
  double distX=1, distY=1, distZ=1;

  public void mutate_this() {
   super.mutate_this();
   nnn = FW_Rand.rand(22);
   distX=params[0];
   distY=params[1];
   distZ=params[2];
  }

  public void mutateLarge_this() {
  ;
 }



public   java.util.List  action(int k){
  java.util.List vv = new java.util.LinkedList();
  P2 p = null;
  java.util.List v;
  Block_Prototype_R son1 =(Block_Prototype_R)sons[0];
  if(son1 == null) return vv;
  Block_Prototype_R son2 =(Block_Prototype_R)sons[1];
  if(son2 == null) return vv;

  {v = son1.action(k);
   for (Iterator i = v.iterator(); i.hasNext(); ) {
    P2 item = (P2)i.next();
    p = new P2(item);
    vv.add(p);
   }
  }

  if(son2 != null)
  {v = son2.action(k);
   for (Iterator i = v.iterator(); i.hasNext(); ) {
    P2 item = (P2)i.next();
    p = new P2(item);
    p.x+=distX;
    p.y+=distY;
    vv.add(p);
   }
  }
  return vv;
  }



}
