package blocks;




import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

// Polynom
public class AF_Poly   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 3.0;
  public AF_Poly() {
     super("AF_Poly", 0, 10);
  }

  public double f(double x, double y){
  double ttt =0.0;
  x=sc*x;
  y=sc*y;
     ttt = params[1]*x+params[2]*y+params[3]*x*y-params[4]*x*x-params[5]*y*y;

   return  ttt;
  }

}
