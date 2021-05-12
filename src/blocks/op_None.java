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

public class op_None
    extends Block_Prototype_Op {
  static final long serialVersionUID = 1L;
  public op_None() {
    super("op_None", 0);
  }

  public void oper(FW_ImageContext ct) {
    ;
  }

}
