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


public class f_a0   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public f_a0() {
     super("polynom_div0", 0, 8);
  }

  public double f(double x, double y){
   double a = params[1]*x+params[2]*y ;
   double b = params[3]*x+params[4]*y ;
   double c = params[5]*x+params[6]*y ;
   double e = params[0]*x+params[7]*y ;
   return  e*e+e*c+(a+b)*b/(0.01+c*c+a*a);
 }

}
