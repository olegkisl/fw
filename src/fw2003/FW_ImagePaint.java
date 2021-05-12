package fw2003;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.event.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

// Painting Thread
public class FW_ImagePaint extends Thread{
  FW_ImageContext cont;
  FW_BlockInterface blk;
  FW_ImagePanel ip;

  public FW_ImagePaint(FW_ImageContext cont,FW_BlockInterface blk, FW_ImagePanel ip) {
    this.cont = cont;
    this.blk = blk;
    this.ip = ip;
  }

  public void  run(){
    int d = FW_Parm.getMaxPaintDepth();
    blk.prepareToPaint();
    blk.paint(cont, d);
    cont.repaint();
    ip.imagePaintThread = null;
  }

}