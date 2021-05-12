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


public class Fxy_poly0   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public Fxy_poly0() {
     super("polynom0x", 0, 8);
  }

  public double f(double x, double y){
   double a = params[1]*x+params[2]*y-params[0] ;
   double aa = a*a;
   return  params[3]+params[4]*a+params[5]*aa+params[6]*a*aa+params[7]*aa*aa;
 }

}
