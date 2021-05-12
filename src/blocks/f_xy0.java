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


public class f_xy0   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double mod = 10.0;
  public f_xy0() {
     super("Polinom", 0, 9);
  }

  public double f(double x, double y){
  double xx = x*x;
  double xxxx = xx*xx;
  double yy = y*y;
  double yyyy = yy*yy;
  double a = params[0]+params[1]*x+params[2]*xx+params[2]*xx*x +params[3]*xxxx;
  double b = params[4]+params[5]*y+params[6]*yy+params[7]*yy*y +params[8]*yyyy;
  return   a*b;
 }

}
