package extension.ggrammar.node;
import  extension.ggrammar.*;
import extension.ggrammar.util.*;

//import fw2003.Block_Prototype_Grammar;
import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.geom.*;

public class GG_2A
    extends Block_Prototype_Grammar {
  static final long serialVersionUID = 1L;
  public GG_2A() {
    super("GG_2", 2);
  }

  public void paintG(FW_ImageContext ct, AffineTransform trn,
                     double s, double b, double u, double v, Map map) {
       if(s<0.001)
         return;
       s=s-1;
       double angle = rand(-10*r2, 10*r2);
       double angles = -100+200*r2;
       actNode(0, ct, trn,       r1, r1,0, 0,       angle,          s, b, u, v, map);
       actNodeThis(ct, trn,     -r1, r1,0, 0,       angle ,         s, b, u, v, map);

       actNode(1, ct, trn,     r3, r3, 0, 0,        angles,         r5*s, b, u, v, map);
       actNode(1, ct, trn,     r3, r3, 0, 0,        angles*0.5,     r5*s, b, u, v, map);
       actNode(1, ct, trn,     r3, r3, 0, 0,        0,              r5*s, b, u, v, map);
       actNode(1, ct, trn,     r3, r3, 0, 0,        -angles*0.5,    r5*s, b, u, v, map);
       actNode(1, ct, trn,     r3, r3, 0, 0,        -angles,        r5*s, b, u, v, map);
  }
}
