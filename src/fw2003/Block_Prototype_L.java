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
import java.awt.geom.*;

public class Block_Prototype_L
    extends Block_Prototype {
  static final long serialVersionUID = 1L;
  int ca = 250, cb = 250, cc = 250, caa = 0, cbb = 0, ccc = 0; // colors
  int nparam = 5;
  public double[] params = null;

  public Block_Prototype_L(String name, int nson) {
    super(name, name, nson, Block_Prototype_L.class);

    params = new double[nparam];
    mutate_all();
  }

  public int rand(int nn) {
    int k;
    double d = params[0];
    d = d * nn;
    k = (int) d;
    if (k < 0) {
      k = 0;
    }
    if (k >= nn) {
      k = nn - 1;
    }
    return k;
  }

  void mutate_all() {
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }

  public void mutate_this() {
    if (FW_Rand.rand01() < FW_Parm.getMutateProb() * 0.33) {
      mutate_all();
    }
    for (int i = 0; i < params.length; i++) {
      if (FW_Parm.getMutateProb() > FW_Rand.rand01()) {
        params[i] = params[i] + 0.1 * (FW_Rand.rand01() - 0.5);
      }
    }
  }

  // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p) {
    Color c, cw = Color.WHITE;
    c = p.getColor();
    ca = c.getRed();
    cb = c.getGreen();
    cc = c.getBlue();

    for (int i = 0; i < 10; i++) {
      cw = p.getColor();
      if (cw != c) {
        caa = cw.getRed();
        cbb = cw.getGreen();
        ccc = cw.getBlue();
      }
    }
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }

    build_rot();
    String st = s("f");
    int nr = FW_Parm.getDrawRepetition();
    for (int i = 0; i < nr; i++) {
      paintString(st, ct);
    }

  }

  //////////////////////////////////////////////////////////

  public String s(String p) {
    return p;
  }

  /////////////////// Interpreteer //////////////////////////////
  double[] sn = new double[10];
  double[] cs = new double[10];
  double scale_factor = 0.75;

  void build_rot() {
    for (int i = 1; i < 10; i++) {
      sn[i] = Math.sin(Math.PI * 2.0 / i);
      cs[i] = Math.cos(Math.PI * 2.0 / i);
    }
  }

  void rot(Pen pen, int nn) {
    double x = pen.dx, y = pen.dy, d = pen.reflect, xa, ya;
    xa = x * cs[nn] - d * y * sn[nn];
    ya = d * x * sn[nn] + y * cs[nn];
    pen.dx = xa;
    pen.dy = ya;
  }

  double alfa = 0.2; // transparensy//alt
  void paintString(String s, FW_ImageContext ct) {
    Pen pen = new Pen(0.1);
    //ct.setQuality(2);
    Pen pena;
    Color clr = new Color(255, 255, 255); //Color(ca, cb, cc);
    ct.randomPointsStart(); //alt
    double bright = FW_Parm.getBritness();
   double crr,cgg,cbb,rrr;
   crr = ca * bright;
   if (crr < 0) {
     crr = crr + 1.0;
   }
   if (crr > 1) {
     crr = 0.9999;

   }
   cgg = cb * bright;
   if (cgg < 0) {
     cgg = cgg + 1.0;
   }
   if (cgg > 1) {
     cgg = 0.9999;

   }
   cbb = cc * bright;
   if (cbb < 0) {
     cbb = cbb + 1.0;
   }
   if (cbb > 1) {
     cbb = 0.9999;

   }


    ct.setColor(clr);
    double xa, ya,xb,yb;
    char[] buf = s.toCharArray();
    Stack st = new Stack();
    for (int i = 0; i < buf.length; i++) {
      switch (buf[i]) {
        case 'f':
          xb = pen.scale * pen.dx;
          yb = pen.scale * pen.dy;
          xa = pen.x + xb;
          ya = pen.y + yb;
          if (pen.paint > 0) {
            if (ct.isPaintStoped()) {
              ct.repaint();
              return;
            }
            rrr =FW_Rand.rand01();
            xb = pen.x+rrr*xb;
            yb = pen.y+rrr*yb;
            ct.pDirectSet(xb, yb, crr, cgg, cbb, alfa);


            //ct.draw(new Line2D.Double(pen.x, pen.y, xa, ya));
          }
          pen.x = xa;
          pen.y = ya;
          break;
        case '[':
          st.push(new Pen(pen));
          break;
        case ']':
          if (!st.empty()) {
            pen = (Pen) st.pop();
          }
          break;
        case '(':
          st.push(new Pen(pen));
          break;
        case ')':
          if (!st.empty()) {
            pena = (Pen) st.pop();
            pen.dx = pena.dx;
            pen.dy = pena.dy;
          }
          break;

        case '*':
          if (pen.reflect > 0.0) {
            pen.reflect = -1.0;
          }
          else {
            pen.reflect = 1.0;
          }
          break;

        case '-':
          pen.scale = pen.scale * scale_factor;
          break;

        case '+':
          pen.scale = pen.scale / scale_factor;
          break;

        case '>':
          pen.paint++;
          break;
        case '<':
          pen.paint--;
          break;

        case '{':
          alfa = alfa*0.75;
          break;
        case '}':
          alfa = alfa/0.75;;
          break;


        case '2':
          rot(pen, 2);
          break;
        case '3':
          rot(pen, 3);
          break;
        case '4':
          rot(pen, 4);
          break;
        case '5':
          rot(pen, 5);
          break;
        case '6':
          rot(pen, 6);
          break;
        case '7':
          rot(pen, 7);
          break;
        case '8':
          rot(pen, 8);
          break;
        case '9':
          rot(pen, 9);
          break;
        default:
          break;
      }

    }

  }

  static class Pen {
    double x = 0.0;
    double y = 0.0;
    double dx = 0.0;
    double dy = 1;
    double reflect = 1.0;
    double scale = 1.0;
    double bright = 1.0;
    int paint = 1;
    Pen() {
    }

    Pen(double length) {
      dy = length;
    }

    Pen(Pen a) {
      x = a.x;
      y = a.y;
      dx = a.dx;
      dy = a.dy;
      reflect = a.reflect;
      scale = a.scale;
      bright = a.bright;
      paint = a.paint;
    }
  }

}
