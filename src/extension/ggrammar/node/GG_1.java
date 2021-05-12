package extension.ggrammar.node;
import  extension.ggrammar.Block_Prototype_Grammar;
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

public class GG_1
    extends Block_Prototype_Grammar {
  static final long serialVersionUID = 1L;

  public GG_1() {
    super("GG_1", 1);
  }

  public void paintG(FW_ImageContext ct, AffineTransform trn,
                     double s, double b, double u, double v, Map map) {

     // actNode(0, ct, trn, 1.0, 1.0, 0.0, 0.0, 0.0, s, b, u, v, map);
     for (int i = 0; i < 3; i++) {
       actNode(0, ct, trn,     0.8, 0.8,    0, i*1.0,     20,
               s, b, u, v, map);
     }


  }

}
