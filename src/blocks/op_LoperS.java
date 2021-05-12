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

public class op_LoperS
    extends Block_Prototype_Op {
  static final long serialVersionUID = 1L;
  public op_LoperS() {
    super("op_LoperS", 1);
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
    float u1 = (float)params[0];
    float u2 = (float)params[1];
    float u3 = (float)(params[3]);
    s = 4*u1+4*u2+u3+0.001f;
    u1 = u1/s;
    u2 = u2/s;
    u3 = u3/s;
    k3x3[0] = u1;
    k3x3[1] = u2;
    k3x3[2] = u1;
    k3x3[3] = u2;
    k3x3[4] = u3;
    k3x3[5] = u2;
    k3x3[6] = u1;
    k3x3[7] = u2;
    k3x3[8] = u1;


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
