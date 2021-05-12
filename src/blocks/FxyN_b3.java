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


  public class FxyN_b3   extends Block_Prototype_Fxy {
  static final long serialVersionUID =1L;
    double sc = 10.0;
    public FxyN_b3() {
       super("FxyN_b3", 3, 10);
    }

    public double f(double x, double y){
     Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
     if(g == null) return 0.5;
     double t = g.f(x,y);
     Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
     if(gg == null) return 0.9;
     double tt = gg.f(x,y);
     Block_Prototype_Fxy ggg =(Block_Prototype_Fxy)sons[2];
     if(ggg == null) return 0.9;
     double ttt = ggg.f(x,y);

     if(params[0]<0.35){
     if(ttt<1.05)
       ttt = g.f(x,y)+g.f(params[1]*x,params[2]*y)+g.f(params[1]*params[1]*x,params[2]*params[2]*y);
     else
       ttt =g.f(x,y)+g.f(ttt*params[1]*x,ttt*params[2]*y)+g.f(ttt*params[1]*params[1]*x,ttt*params[2]*params[2]*y);;
     }

     else if(params[0]<0.7)
       ttt = ggg.f(x,params[3]+y)+ggg.f(-x,params[3]+y);
     else{
      ttt = ggg.f(params[4]+x,-y)+ggg.f(params[4]+x,y);;
     }
     return  ttt;
    }

  }
