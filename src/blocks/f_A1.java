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


public class f_A1   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public f_A1() {
     super("1 arg", 1, 12);
  }

  public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double c =params[0] + params[1]*x + params[2]*x*x+params[6]*y + params[7]*y*y+params[8]*x*y;
   double cc =params[3] + params[4]*y + params[5]*y*y+params[9]*x + params[10]*x*x+params[11]*x*y;
   return g.f(c,cc);
  }

}
