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

public class GG_2
    extends Block_Prototype_Grammar {
  static final long serialVersionUID = 1L;
  public GG_2() {
    super("GG_2", 2);
  }

  public void paintG(FW_ImageContext ct, AffineTransform trn,
                     double s, double b, double u, double v, Map map) {
       if(s<0.001)
         return;
       r1 = 0.6+r1*0.39;
       r3 = 0.6+r3*0.39;
       s=s-1;
       double angle = rand(-40*r2, 40*r2);
       double angles = -100+200*r2;
       actNode(0, ct, trn,      1, 1.04, 0.0, 0.0,        0.0,          s, b, u, v, map);
       actNodeThis(ct, trn,     -r1, r1, 0, 1,       angle ,         s, b, u, v, map);

       actNode(1, ct, trn,     r3, r3, 0, 0.5,        angles,         s, b, u, v, map);
  }
}
