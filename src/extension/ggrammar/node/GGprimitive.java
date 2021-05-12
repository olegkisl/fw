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

public class GGprimitive extends Block_Prototype_Grammar{
  static final long serialVersionUID = 1L;
  java.util.List list = new LinkedList();


  public GGprimitive() {
    super("GGprimitive", 0);

    float [] pp=null;
    pp = new float[]{0.0f, 0.0f,  0.5f,0.5f, 0.0f,1.0f, -0.5f,0.5f};
    list.add(new GGElement(pp, Color.green));
    pp = new float[]{0.0f, 0.0f,  0.2f,0.5f, 0.0f,1.0f, -0.2f,0.5f};
    list.add(new GGElement(pp, Color.red));
  }
  public void paintG(FW_ImageContext ct, AffineTransform trn, double s, double b, double u, double v, Map map) {
   for (Iterator iter = list.iterator(); iter.hasNext(); ) {
     GGElement item = (GGElement) iter.next();
     ct.getGraphics2D().setPaint(item.getColor());
     ct.fill(item.getGeneralPath().createTransformedShape(trn));
   }
 }

 public boolean isEditable(){
    return true;
  }

 public javax.swing.JPanel getEditor(){
    return new GGEditorWindow(list);
  }


//////////////////////


}
