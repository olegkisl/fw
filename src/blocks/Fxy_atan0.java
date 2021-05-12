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


public class Fxy_atan0   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public Fxy_atan0() {
     super("other0xy", 0, 8);
  }

  public double f(double x, double y){
   double a = params[1]*x+params[2]*y-params[0] ;
   if(params[3]<0.2)
      return  Math.atan(a);
   if(params[3]<0.4)
      return  Math.sin(a);
   if(params[3]<0.6)
      return  Math.log(a*a+0.001+params[4]);
   if(params[4]<0.8)
      return  Math.ceil(a);
   return Math.tan(a);
 }

}
