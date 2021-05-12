package blocks;




  import fw2003.*;
  import java.awt.*;
  import javax.swing.*;
  import java.awt.event.*;
  import java.util.*;
  import javax.swing.tree.*;
  import java.io.*;


  public class FxxN_b5   extends Block_Prototype_Fxy {
  static final long serialVersionUID =1L;
    double sc = 10.0;
    public FxxN_b5() {
       super("FxxN_b5", 0, 10);
    }

    public double f(double x, double y){
    double ttt =0.0;
     if(params[0]<0.35){
       ttt = params[1]*x+params[2]*y+params[3]*x*y-params[4]*x*x-params[5]*y*y;
     }

     else if(params[0]<0.7){
       ttt = params[1] * x - params[2] *y;
       ttt = params[3]*ttt*ttt+params[4]*ttt*ttt*ttt-params[5]*ttt;
     }
     else{
       ttt = params[1] * x - params[2] *y;
       ttt =Math.sin(params[5]*ttt)/(params[4]+0.001+ttt*ttt);
     }
     return  ttt;
    }

  }
