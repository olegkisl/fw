package blocks;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;

public class AFM_gorizontBB
    extends Block_Prototype_Fxy {
  static final long serialVersionUID = 1L;
  double sc = 10.0;
  public AFM_gorizontBB() {
    super("AFM_gorizontBB", 2, 7);
  }

  public double f(double x, double y) {
    Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
    if (g == null) {
      return 0.5;
    }
    double t = g.f(x, y);
    Block_Prototype_Fxy gg = (Block_Prototype_Fxy) sons[1];
    if (gg == null) {
      return 0.9;
    }
    double tt;
    double a = 1, aa, aaa, bb;

    if (y > -0.0001) {
      return t;
    }
    else {
      aa = -params[1] - 0.5;
      if (y < aa) {
        a = 1;
        aaa = 1;
      }
      else {
        a = aa / y;
        aaa = (y / aa + 0.1);
      }
      tt = aaa * gg.f(x * a, y * a);

      if (params[0] < 0.25) {
        return t * tt;
      }
      else if (params[0] < 0.5) {
        a = t * t;
        aa = tt * tt;
        return (2 * a * aa) / (a + aa + 0.0001);
      }
      else if (params[0] < 0.75) {
        a = Math.abs(t);
        aa = Math.abs(tt);
        return (2 * a * aa) / (a + aa + 0.0001);
      }
      else {
        a = t * tt;
        aa = Math.abs(t + tt); ;
        return (2 * a) / (aa + 0.0001);
      }


    }

  }

}
