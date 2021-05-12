package fw2003;

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
import java.awt.image.*;
import java.awt.geom.*;

public class FW_MacroElement implements Serializable {
  static final long serialVersionUID =1L;
  double x1=0.0,y1=0.0,x2=0.0,y2=1.0;
  Tr2 hh= null;
  FW_BlockInterface b= null;
  int repeat = 1;


  public FW_MacroElement(double x1,double y1,double x2,double y2, FW_BlockInterface b) {
  this.x1=x1;
  this.y1=y1;
  this.x2=x2;
  this.y2=y2;
  double d1=x1-x2;
  double d2= y1-y2;
  double d3 = d1*d1+d2*d2+0.0001;
  double cc = Math.sqrt(d3);
  Tr2 hc = new Tr2();
  hc = Tr2.scale(cc,cc);
  hh = Tr2.sovm(
                  0.0,0.0,  0.0,1.0,
                  x1,y1,  x2,y2
                  );
  hh = Tr2.mult(hc,hh);
  if(b!=null)
    this.b = b.copy();
  }

  public void  addRepeat(){
    repeat++;
  }

  public int getRepeat(){
    return repeat;
  }


}