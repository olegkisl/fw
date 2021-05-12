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


public class Block_Prototype_Macro
    extends Block_Prototype {
  static final long serialVersionUID = 1L;
  java.util.List ls = null;
  double w= 400;
  double h= 400;
  Tr2 ha = null;

  public Block_Prototype_Macro(String name, java.util.List s, int nw, int nh) {
    super(name, name, s.size(), Block_Prototype.class);
  int nn=0;
  ls=s;

  w =(double) nw;
  h =(double) nh;


  nn=0;
  for (Iterator i = s.iterator(); i.hasNext(); ) {
    sons[nn]=((FW_MacroElement)i.next()).b;
    nn++;
  }

  ///////////////////////Change
  nn=0;
  for (Iterator i = s.iterator(); i.hasNext(); ) {
    this.classes[nn]=(((FW_MacroElement)i.next()).b).getClass();
    nn++;
  }


 double x1=w*0.5;
 double y1=h*0.75;
 double x2=w*0.5;
 double y2=h*0.25;
 double d1=x1-x2;
 double d2= y1-y2;
 double d3 = d1*d1+d2*d2+0.0001;
 double cc = Math.sqrt(d3)+0.0001;
  Tr2 hc = new Tr2();
  hc = Tr2.scale(1.0/cc, 1.0/cc);
   ha = Tr2.sovm(
                 x1, y1,    x2, y2,
                 0.0,0.0,      0.0,1.0
                 );
    ha = Tr2.mult(ha,hc);
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    AffineTransform trn = ct.getTransform();
    ct.normalizeContext();
    Tr2 h,hb;
    for (int i = 0; i < sons.length; i++) {
      if(sons[i]!=null){
        FW_MacroElement me =(FW_MacroElement)ls.get(i);
        if(me==null) continue;
        hb = me.hh;
        h = Tr2.mult(hb,ha);
        ct.setApplicationContext(null);
        ct.setApplicationContext(new grammar.Active( "", h, 10.0,1.0,0.0));
        sons[i].paint(ct,depth-1);
      }
    }
    ct.repaint();
    ct.setApplicationContext(null);
    ct.setTransform(trn);
  }



}
