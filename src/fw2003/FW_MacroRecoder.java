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
import java.awt.image.*;
import java.awt.geom.*;

public class FW_MacroRecoder {
  java.util.List mList = new LinkedList();
  FW_BlockInterface last = null;
  FW_MacroElement mlast = null;
  boolean flag = false;
  public FW_MacroRecoder() {
  }

  public void start() {
    mList = new LinkedList();
    last = null;
    mlast = null;
    flag = true;
  }

  public void add(double x1, double y1, double x2, double y2,
                  FW_BlockInterface b) {
    if (!flag) {
      return;
    }
    last = b;
    mlast = new FW_MacroElement(x1, y1, x2, y2, b);
    mList.add(mlast);
  }

  public void undo() {
    if(flag&&(mlast!=null))
      mList.remove(mlast);
  }

  public void stop() {
    mList = new LinkedList();
    last = null;
    mlast = null;
    flag = false;
  }

  static int nnn = 0;
  FW_BlockInterface getNewBlock(int nw, int nh) {
    nnn++;
    Block_Prototype_Macro m = new Block_Prototype_Macro("macro" + nnn, mList,
        nw, nh);
    System.out.println("MACRO" + nnn);
    return m;
  }

  FW_BlockInterface getNewBlock(int nw, int nh, int macroType) {
    nnn++;
    Block_Prototype_Macro_mod m = new Block_Prototype_Macro_mod("macro_t"+macroType+"_" + nnn, mList,
        nw, nh, macroType );
    System.out.println("MACRO" + nnn);
    return m;
  }


}