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


public class L_treeC  extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public L_treeC() {
     super("L_treeC", 2);
  }

 int nn =0;
 public void mutateLarge_this() {
  nn = FW_Rand.rand(9);
 }


  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String t = g.s(a);
   Block_Prototype_L gg =(Block_Prototype_L)sons[1];
   if(gg == null) return "";
   String tt = gg.s(a);
   Block_Prototype_L ggg =(Block_Prototype_L)sons[1];
   if(ggg == null) return "";
   String b = gg.s(a);




   switch (nn) {
     case 0 : t = "[7"+b+t+"]"+b+tt;
       break;
     case 1 : t = b+"[*7"+tt+"][7"+tt+"]";
       break;
     case 2 : t = "[*7"+tt+"]"+b+"[7"+tt+"]"+b;
       break;
     case 3 : t = "[*6"+tt+"]"+b+"[6"+tt+"]"+b;
       break;
     case 4 : t = "[*8"+tt+"]"+b+"[8"+tt+"]"+b;;
       break;
     case 5 : t = "[*9"+tt+"]"+b+"[9"+tt+"]"+b;;
       break;
     case 6 : t = "[*7"+tt+"]"+b+"[7"+tt+"]"+t;;
       break;
     case 7 : t = "[*9"+tt+"]9"+tt;
       break;
     case 8 : t = "[8"+tt+"]88"+t;
       break;
     default:
       t= t+t;
       break;
   }

   return  t;
 }

}
