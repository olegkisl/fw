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

public class op_LoperR
    extends Block_Prototype_Op {
  static final long serialVersionUID = 1L;
  public op_LoperR() {
    super("op_LoperR", 1);
  }

  public void oper(FW_ImageContext ct) {
    BufferedImage ima = ct.getImage();
    if (ima == null) {
      return;
    }
    int w = ima.getWidth();
    int h = ima.getHeight();
    //////////////////////////////////KERNEL
    float[] k3x3 = new float[9];
    float s = 0.0f;
    for (int i = 0; i < 9; i++) {
      k3x3[i] = (float) (params[i] * 2 - 1.0);
      s = s + k3x3[i];
    }
    if ( (s > 0.01) || (s < 0.01)) {
      for (int i = 0; i < 9; i++) {
        k3x3[i] = k3x3[i]/s;
      }
    }

    BufferedImage dstbimg = new
        BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Kernel kernel = new Kernel(3, 3, k3x3);
    ConvolveOp cop = new ConvolveOp(kernel,
                                    ConvolveOp.EDGE_NO_OP,
                                    null);
    cop.filter(ima, dstbimg);
    Raster r = dstbimg.getData();
    ima.setData(r);
  }

}