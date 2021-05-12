package blocks;



import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

//  REPEAT PATTERNS
public class LL_repeat   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public LL_repeat() {
     super("LL_repeat", 1);
  }

  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String v="",t = g.s(a);
   int nn = rand(6)+2;
   switch (nn) {
     case 2 : v =t + t;
       break;
     case 3 : v =t+t+t;
       break;
     case 4 : v =t+"*"+t +"*" + t;
       break;
     case 5 : v =t+"*"+t +"*" + t+"*"+t+"*";
       break;
     case 6 : v =t+t+t+t;
       break;
     case 7 : v =t+t+t+t+t;
       break;
     default:v=t;
       break;
   }
   return  v;
 }
}
