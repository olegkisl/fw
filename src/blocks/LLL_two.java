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


public class LLL_two   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public LLL_two() {
     super("LLL_two", 2);
  }

 int nn =0;
 public void mutateLarge_this() {
  nn = FW_Rand.rand(5);
 }


  public String s(String a){
   Block_Prototype_L g =(Block_Prototype_L)sons[0];
   if(g == null) return "";
   String t = g.s(a);
   Block_Prototype_L gg =(Block_Prototype_L)sons[1];
   if(gg == null) return "";
   String tt = gg.s(a);



   switch (nn) {
     case 0 : t = "[9"+tt+"8"+tt+"7"+tt+"]"+"[*9"+tt+"8"+tt+"7"+tt+"]" +"[*"+t+"]"+t;
       break;
     case 1 : t = "[6"+tt+"7"+tt+"8"+tt+"]"+"[*6"+tt+"7"+tt+"8"+tt+"]" +"[*"+t+t+"]"+t+t;
       break;
     case 2 : t = "[5"+t+tt+t+"]"+"[*5"+t+tt+t+"]"+"[*"+tt+"]"+tt;
       break;
     case 3 : t =  "[4"+t+tt+t+"]"+"[*4"+t+tt+t+"]"+"[2*"+tt+"]"+"[2"+tt+"]"+"[*"+t+"]"+t;
       break;
     case 4 : t ="[6"+t+"*7*"+t+"*7*"+t+"*7*"+t+"*7*"+t+"*7*"+t+"]"
                +"[6"+t+"*7*"+t+"*7*"+t+"*7*"+t+"*7*"+t+"*7*"+t+"]"+"[*"+tt+"]"+tt;
       break;
     case 5 : t = tt+t+tt +"[2"+tt+t+tt +"]4"+
                  t+t+t+t +"[2"+t+t+t +t+"]4"+
                  tt+t+tt +"[2"+tt+t+tt +"]4"+
                  t+t+t +t+"[2"+t+t+t +t+"]4";
       break;
     default:
       break;
   }

   return  t;
 }

}
