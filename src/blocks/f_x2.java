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


public class f_x2   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public f_x2() {
     super("sin+cos", 0, 6);
  }

  public double f(double x, double y){
   double d = params[0]*Math.sin(params[1]*x+params[3])+params[2]*Math.sin(params[3]*y);
   return params[4]*d*d+params[5]*d*d*d;
  }

}