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

public class fcc_merge2   extends Block_Prototype_Fxy_C_C {
static final long serialVersionUID =1L;
  public fcc_merge2() {
     super("fcc_merge2", 2, 4);
  }

  Clr clr = new Clr(0.0,0.0,0.0);
  Clr clra = new Clr(0.0,0.0,0.0);
  Clr clrb = new Clr(0.0,0.0,0.0);

  public Clr fc(double x, double y){

   Block_Prototype_Fxy_C p =(Block_Prototype_Fxy_C)sons[0];
   if(p != null)
       clra = p.fc(x,y);

   Block_Prototype_Fxy_C pp =(Block_Prototype_Fxy_C)sons[1];
   if(pp != null)
       clrb =pp.fc(x,y);




   double a = params[0]/(params[0]+params[1]+0.0001);
   double b = 1-a;
   clr.r =clra.r*a + clrb.r*b;
   clr.g =clra.g*a + clrb.g*b;
   clr.b =clra.b*a + clrb.b*b;
   return  clr;
  }

}
