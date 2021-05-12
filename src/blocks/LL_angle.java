package blocks;


import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

//  SIMETRY PATTERNS
public class LL_angle   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public LL_angle() {
     super("LL_angle", 1);
  }

  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String v="",t = g.s(a);
   int nn = rand(6)+2;
   switch (nn) {
     case 2 : v ="7"+t +"*77*" + t +"7";
       break;
     case 3 : v ="6"+t +"*66*" + t +"6";
       break;
     case 4 : v ="5"+t +"*55*" + t +"5";
       break;
     case 5 : v ="4"+t +"*4"+t+"4*" + t +"4";
       break;
     case 6 : v ="5"+t +"*5"+t+"5*" + t +"5";
       break;
     case 7 : v ="7"+t +"*7"+t+"7*" + t +"7";
       break;
     default:v=t;
       break;
   }
   return  v;
 }
}
