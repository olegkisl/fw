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


public class LL_two_simple   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public LL_two_simple() {
     super("LL_two_simple", 2);
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
     case 0 : t = "*"+tt+t+"*";
       break;
     case 1 : t = tt+t;
       break;
     case 2 : t = t+tt;
       break;
     case 3 : t = "*"+t+"*"+tt;
       break;
     case 4 : t ="[*"+t+tt+"]" +t +tt;
       break;
     case 5 : t = "[*"+tt+t+"]" +t +tt;
       break;
     default:
       break;
   }

   return  t;
 }

}
