package fw2003;

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
import java.awt.geom.*;

public class Block_Prototype_R
    extends Block_Prototype {
  static final long serialVersionUID = 1L;
  double cr = 1.0, cg = 1.0, cb = 1.0; // colors
  int nparam = 10;
  public double[] params = null;
  double bright = FW_Parm.getBritness();

  public Block_Prototype_R(String name, int nson) {
    super(name, name, nson, Block_Prototype_R.class);

    params = new double[nparam];
    mutate_all();
  }

  public int rand(int nn) {
    int k;
    double d = params[0];
    d = d * nn;
    k = (int) d;
    if (k < 0) {
      k = 0;
    }
    if (k >= nn) {
      k = nn - 1;
    }
    return k;
  }

  void mutate_all() {
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }

  public void mutate_this() {
    if (FW_Rand.rand01() < FW_Parm.getMutateProb() * 0.33) {
      mutate_all();
    }
    for (int i = 0; i < params.length; i++) {
      if (FW_Parm.getMutateProb() > FW_Rand.rand01()) {
        params[i] = params[i] + 0.1 * (FW_Rand.rand01() - 0.5);
      }
    }
  }

  // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p) {
    Color c;
    c = p.getColor();
    cr = c.getRed() / 255.0;
    cg = c.getGreen() / 255.0;
    cb = c.getBlue() / 255.0;
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    AffineTransform trn = ct.getTransform();
    ct.normalizeContext();
    bright = FW_Parm.getBritness();
    double crr,cgg,cbb;
    crr = cr * bright;
    if (crr < 0) {
      crr = crr + 1.0;
    }
    if (crr > 1) {
      crr = 0.9999;

    }
    cgg = cg * bright;
    if (cgg < 0) {
      cgg = cgg + 1.0;
    }
    if (cgg > 1) {
      cgg = 0.9999;

    }
    cbb = cb * bright;
    if (cbb < 0) {
      cbb = cbb + 1.0;
    }
    if (cbb > 1) {
      cbb = 0.9999;

    }

    int list_size = 100; // size of the list in the terminal nodes of the tree
    double alfa = 0.2; // transparensy

    int nnn = FW_Parm.getDrawRepetition();
    java.util.List s = action(list_size*nnn);
    ct.randomPointsStart();
    P2 item;

    for (int j = 0; j < 1; j++) {
      for (Iterator i = s.iterator(); i.hasNext(); ) {
        item = (P2) i.next();
        if (ct.isPaintStoped()) {
          ct.repaint();
          return;
        }
        ct.pDirectSet(item.x, item.y, crr, cgg, cbb, alfa);
      }
    }
    ct.repaint();
    ct.setTransform(trn);
  }

  //////////////////////////////////////////////////////////
  public java.util.List action(int number) {
    return new java.util.LinkedList();
  }

  /////////////////////////

  static class Point {
    double x = 0.0;
    double y = 0.0;
    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }

}
