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


public class LL_Tree   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public LL_Tree() {
     super("LL_Tree", 3);
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
   Block_Prototype_L ggg =(Block_Prototype_L)sons[2];
   if(ggg == null) return "";
   String ttt = ggg.s(a);



   switch (nn) {
     case 0 : t = "*"+t+tt+"*";
       break;
     case 1 : t = tt+ttt;
       break;
     case 2 : t = t+"[7"+tt+"][*7"+tt+"]"+ttt;
       break;
     case 3 : t = t+"[9"+tt+"][*9"+tt+"]"+ttt;
       break;
     case 4 : t =t+"[5"+tt+"][*5"+tt+"]"+ttt;
       break;
     case 5 : t = t+t+"[5"+ttt+"][*5"+ttt+"]"+t;
       break;
     default:
       break;
   }

   return  t;
 }

}
