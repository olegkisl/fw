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


public class L_lineB   extends Block_Prototype_L {
static final long serialVersionUID =1L;
  public L_lineB() {
     super("L_lineB", 1);
     mutateLarge_this();
  }


    int nn =0;
    public void mutateLarge_this() {
     nn = FW_Rand.rand(5);
    }


    public String s(String a){
     Block_Prototype_L g =(Block_Prototype_L)sons[0];
     if(g == null) return "";
     String t = g.s(a);
     String tt= "",ttt;
     switch (nn) {
     case 0:
       tt = "[{4f*4*"+t+"}][{*4f*4"+t+"}]("+t+")";
       break;
     case 1:
       tt = "{"+t+"}f"+t;
       break;
      case 2:
       ttt="f["+t+"]";
       tt =ttt+"{"+ttt+"}"+ttt;
      break;
       case 3:
         ttt="f["+t+"]";
         tt =ttt+"*{"+ttt+"}"+ttt+"*{"+ttt+"}";;
       break;
       case 4:
       tt = "{"+t+"}[2*"+t+"]";
       break;
     default:
        tt = t+t;
       break;
   }


     return  tt;
   }


}
