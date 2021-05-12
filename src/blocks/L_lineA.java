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


public class L_lineA   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public L_lineA() {
     super("L_lineA", 1);
     mutateLarge_this();
  }

  int nn =0;
  public void mutateLarge_this() {
   nn = FW_Rand.rand(11);
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
     tt = "7("+t+")*77("+t+")*7";
     break;
    case 2:
     tt = "8("+t+")*4("+t+")*8";
    break;
     case 3:
     tt = "6("+t+")*3("+t+")*6";
     break;
     case 4:
     tt = "("+t+")[2*"+t+"]";
     break;
     case 5:
     tt = "("+t+")[4"+t+"]("+t+")";
     break;
     case 6:
     tt = "("+t+")[4"+t+"][*4"+t+"]("+t+")";
     break;
     case 7:
     tt = "6("+t+")*6("+t+")6("+t+")*6";
     break;
      case 8:
        tt ="6("+t+")*3("+t+")";
        tt = "[*"+tt+"]*("+tt+")";
     break;
      case 9:
        tt ="*7("+t+")*77("+t+")";
        tt = "[*"+tt+"]*("+tt+")";
     break;
      case 10:
        tt ="8("+t+")*4("+t+")";
        tt ="("+tt+")*("+tt+")";
     break;

   default:
      tt = t+t;
     break;
 }


   return  tt;
 }

}
