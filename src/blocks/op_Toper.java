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
import java.awt.image.*;

public class op_Toper
    extends Block_Prototype_Op {
  static final long serialVersionUID = 1L;
  public op_Toper() {
    super("op_Toper", 2);
    this.classes[1] = Block_Prototype_Fxy.class;
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    oper(ct);

    if (ct.isPaintStoped()) {
      ct.repaint();
      return;
    }
    int d = depth - 1;

    if (sons[0] != null) {
      sons[0].paint(ct, d);
    }

  }

  public void oper(FW_ImageContext ct) {
    BufferedImage ima = ct.getImage();
    if (ima == null) {
      return;
    }
    int w = ima.getWidth();
    int h = ima.getHeight();
    //////////////////////////////////TABLE
    byte reverse[] = new byte[256];
    for (int j = 0; j < 255; j++) {
      reverse[j] = (byte) j;
    }
    Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[1];
    if (g == null) {
      return;
    }
    double t, x = params[0], y = params[1];
    double dx = params[2] * 0.01 + 0.001, dy = params[3] * 0.01 + 0.001;
    for (int j = 0; j < 255; j++) {
      t = g.f(x, y);
      x = x + dx;
      y = y + dy;
      if (t < 0) {
        t = -t;
      }
      if (t > 1.0) {
        t = 1.0 / t;
      }
      t = t * 254.0;
      reverse[j] = (byte) t;
    }

    //////////////////////////////////////////

    BufferedImage dstbimg = new
        BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    ByteLookupTable blut = new ByteLookupTable(0, reverse);
    LookupOp lop = new LookupOp(blut, null);
    lop.filter(ima, dstbimg);
    Raster r = dstbimg.getData();
    ima.setData(r);
  }

}
