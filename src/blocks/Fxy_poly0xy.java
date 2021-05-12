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


public class Fxy_poly0xy   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public Fxy_poly0xy() {
     super("polynom0xy", 0, 10);
  }

  public double f(double x, double y){
   double a = params[1]*x+params[2]*y-params[0] ;
   double b = params[3]*x+params[4]*y-params[5] ;
   double aa = a*a;
   double ab = a*b;
   double bb = b*b;
   return  params[6]+params[7]*aa+params[8]*ab+params[9]*bb;
 }

}
