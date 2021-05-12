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
import java.awt.geom.*;

public class Test0
    extends Block_Prototype {
  public Test0() {
    super("test0", "test0", 0);
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }

    ct.setColor(Color.green);

    ct.draw(new Line2D.Double(0.0, 0.0,0.0,1.0));

    /*
    int x=100,y=100,a=100,b=100, u3 = 3;
    for (int i = 0; i < 1000; i++) {
      if (ct.isPaintStoped())return;
      for (int j = 0; j < 100; j++) {
        if(Math.random()>0.5)
          u3=2;
        else
          u3=3;
         if(Math.random()>0.5)
           a=x+u3;
         else
           a=x-u3;
         if(Math.random()>0.5)
           u3=2;
         else
           u3=3;
         if(Math.random()>0.5)
           b=y+u3;
         else
           b=y-u3;
         if(Math.random()>0.5)
          ct.setColor(Color.red);
         else
           ct.setColor(Color.green);
         ct.draw(new Line2D.Double(x, y,a,b));
         x=a;
         y=b;
      }

    }*/

  }

}