package blocks;



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

//  REPEAT PATTERNS
public class LL_simetry   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public LL_simetry() {
     super("LL_simetry", 1);
  }

  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String v="",t = g.s(a);
   int nn = rand(6)+2;
   switch (nn) {
     case 2 : v ="[*"+t+"]" + t;
       break;
     case 3 : v ="[4*"+t+"]"+"[4"+t+"]"+"f";
       break;
     case 4 : v ="[5*"+t+"]"+"[5"+t+"]"+"f";
       break;
     case 5 : v ="[*"+t+"*"+t+"]" + t;
       break;
     case 6 : v =v ="[*"+t+"*"+t+"*"+t+"]" + t;
       break;
     case 7 : v =v ="[*"+t+"2"+t+"]" + t;
       break;
     default:v=t;
       break;
   }
   return  v;
 }
}
