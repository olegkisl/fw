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


public class L_simC   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public L_simC() {
     super("L_simC", 1);
  }

  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String t = g.s(a);
   int nn = rand(8);
   switch (nn) {
     case 0 : t ="["+ t +"]*(" + t +")*";
       break;
     case 1 : t ="("+ t +")2[" + t +"]2";
       break;
     case 2 : t ="("+ t +")4*(" + t +")4*(" + t +")4*(" + t +")*";
       break;
     case 3 : t ="("+ t +")(" + t +")(" + t +")";
       break;
     case 6 : t ="("+ t +")*(" + t +")*";
       break;
     case 7 : t ="("+ g.s(g.s(t)) +")*(" + g.s(t) +")*(" + t +")";
       break;
     default:
       break;
   }

   return  t;
 }

}
