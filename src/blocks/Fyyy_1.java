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


public class Fyyy_1   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 10.0;
  public Fyyy_1() {
     super("Fyyy_1", 0, 10);
  }

  public double f(double x, double y){
  double ttt =0.0;

     ttt = params[1]*x+params[2]*y+params[3]*x*y-params[4]*x*x-params[5]*y*y;

   return  ttt;
  }

}
