package blocks;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

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


public class L_treeA   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public L_treeA() {
     super("L_treeA", 1);
     mutateLarge_this();
  }

  int nn =0;
  public void mutateLarge_this() {
   nn = FW_Rand.rand(7);
  }


  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String t = g.s(a);
   String tt= "";
   switch (nn) {
   case 0:
     tt = "("+t+")*("+t+")*";
     break;
   case 1:
     tt = t+t;
     break;
    case 2:
     tt = t+"9"+t+"*9";
    break;
     case 3:
     tt = t+"8"+t+"*8";
     break;
     case 4:
     tt = t+"9*"+t+"9";
     break;
     case 5:
     tt = t+"8*"+t+"8";
     break;
     case 6:
     tt = t+t+t;
     break;

   default:
      tt = t+t;
     break;
 }


   return  tt;
 }

}
