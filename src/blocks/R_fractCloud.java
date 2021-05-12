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

public class R_fractCloud
    extends Block_Prototype_R {
  static final long serialVersionUID = 1L;
  public R_fractCloud() {
    super("R_fractCloud", 0);
    mutate_this();
  }


  Tr2 ha1 = new Tr2(0.5, 0.0, 0.2, 0.0, 0.5, 0.3);
  Tr2 ha2 = new Tr2(0.5, 0.0, 0.4, 0.0, 0.5, 0.2);
  Tr2 ha3 = new Tr2(0.5, 0.0, 0.6, 0.0, 0.5, 0.1);
  Tr2 ha4 = new Tr2(0.5, 0.0, 0.0, 0.0, 0.5, 0.7);

  private Tr2 make(double xx, double yy) {
    Tr2 t = Tr2.turn(0.0, 0.0, 20.0 -40.0*FW_Rand.getDouble());
    Tr2 t1 = Tr2.move(xx-0.2+0.4*FW_Rand.getDouble(), yy-0.2+0.4*FW_Rand.getDouble());
    Tr2 t2 = Tr2.scale(0.5+ FW_Rand.getDouble() * 0.4, 0.5+ FW_Rand.getDouble() * 0.4);
    t = Tr2.mult(t, t1);
    t = Tr2.mult(t, t2);
    return t;
  }

  public void mutate_this() {
    ha1 = make(0.0,0.0);
    ha2 = make(0.0,0.5);
    ha3 = make(0.5,0.0);
    ha4 = make(0.5,0.5);
  }

  public void mutateLarge_this() {
  ;
 }


public  java.util.List action(int number) {
    java.util.List f = new java.util.LinkedList();
    P2 p = new P2(0.5, 0.5);
    for (int i = 0; i < 5; i++) {
      p = next(p);
    }
    for (int i = 0; i < number; i++) {
      p = next(p);
      f.add(p);
    }
    return f;
  }

  P2 next(P2 p) {
    double clrM = 0.5; // Coeficient of Transformation

    double x0 = p.x;
    double y0 = p.y;
    double r0 = 0, r, a, b;
    P2 pp = new P2(x0, y0);
    switch (FW_Rand.rand(4)) {
      case 0:
        pp.act(ha1);
        break;
      case 1:
        pp.act(ha2);
        break;
      case 2:
        pp.act(ha3);
        break;
      case 3:
        pp.act(ha4);
    }
    return pp;
  }

}
