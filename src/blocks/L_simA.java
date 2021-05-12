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


public class L_simA   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public L_simA() {
     super("L_simA", 1);
  }

  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String v="",t = g.s(a);
   int nn = rand(6)+2;
   switch (nn) {
     case 2 : v ="["+ t +"]2[" + t +"]";
       break;
     case 3 : v ="["+ t +"]3[" + t +"]3[" + t +"]";
       break;
     case 4 : v ="["+ t +"]4[" + t +"]4[" + t +"]4[" + t +"]";
       break;
     case 5 : v ="["+ t +"]5[" + t +"]5[" + t +"]5[" + t +"]5[" + t +"]";
       break;
     case 6 : v ="["+ t +"]6[" + t +"]6[" + t +"]6[" + t +"]6[" + t +"]6[" + t +"]";
       break;
     case 7 : v ="["+ t +"]7[" + t +"]7[" + t +"]7[" + t +"]7[" + t +"]7[" + t +"]7[" + t +"]";
       break;
     default:v=t;
       break;
   }

   return  v+"<"+t+">";
 }

}
