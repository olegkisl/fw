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


public class Fxy_sincos0   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double mod = 10.0;
  public Fxy_sincos0() {
     super("SinCos0", 0, 7);
  }

  public double f(double x, double y){
   double a = params[1]*x+params[2]*y  ;
   double b = params[3]*x+params[4]*y ;
   return    params[0]*Math.sin(params[5]+mod*a)*Math.sin(params[6]+ mod*b);
 }

}
