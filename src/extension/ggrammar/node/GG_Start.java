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

public class GG_Start
    extends Block_Prototype_Grammar {
  static final long serialVersionUID = 1L;

  public GG_Start() {
    super("GG_Start", 1);
  }

  public void paintG(FW_ImageContext ct, AffineTransform trn,
                     double s, double b, double u, double v, Map map) {
       actNode(0, ct, trn,     1, 1,    0, 0.0,     0,
               r1*15, b, u, v, map);
  }

}
