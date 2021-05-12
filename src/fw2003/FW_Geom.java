package fw2003;

import java.util.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_Geom {
  public FW_Geom() {
  }

  static AffineTransform sovm(double x1, double y1, double x2, double y2,
                              double x3, double y3, double x4, double y4) {
    AffineTransform h, h2, h3;
    double r1, ax, ay, r2;
    try {
      ax = x2 - x1;
      ay = y2 - y1;
      r1 = ax * ax + ay * ay;
      if (r1 < 0.0000000001) {
        return AffineTransform.getTranslateInstance(0.0, 0.0);
      }
      r2 = Math.sqrt(r1);
      double sn = ay / r2, cs = ax / r2;
      h2 = new AffineTransform(cs, sn, -sn, cs, 0.0, 0.0);
      h2 = h2.createInverse();

      ax = x4 - x3;
      ay = y4 - y3;
      r1 = ax * ax + ay * ay;
      if (r1 < 0.0000000001) {
        return AffineTransform.getTranslateInstance(0.0, 0.0);
      }
      r2 = Math.sqrt(r1);
      sn = ay / r2;
      cs = ax / r2;
      h3 = new AffineTransform(cs, sn, -sn, cs, 0.0, 0.0);
      h = AffineTransform.getScaleInstance( -x1, -y1);
      h2.concatenate(h);
      h3.concatenate(h2);
      h = AffineTransform.getScaleInstance(x3, y3);
      h.concatenate(h3);
    }
    catch (Exception e) {
      return AffineTransform.getTranslateInstance(0.0, 0.0);
    }
    return h;
  }
  /*
    static Tr2 sovm(double x1, double y1, double x2, double y2,
                  double x3, double y3, double x4, double y4
   )
//r1-2  to r3-4
   {Tr2  h2,h3,h;
   double r1,ax,ay,r2;
   ax=x2-x1;
   ay=y2-y1;
   r1=ax*ax+ay*ay;
   if(r1<0.0000000001)
      return Tr2.move(0.0,0.0);
   r2=Math.sqrt(r1);
   h2=Tr2.turn(0.0,0.0, ay/r2, ax/r2);
   h2=Tr2.inv(h2);
   ax=x4-x3;
   ay=y4-y3;
   r1=ax*ax+ay*ay;
   if(r1<0.0000000001)
      return Tr2.move(0.0,0.0);
   r2=Math.sqrt(r1);
   h3=Tr2.turn(0.0,0.0, ay/r2, ax/r2);
   h=Tr2.mult(Tr2.move(-x1,-y1),h2);
   h=Tr2.mult(h,h3);
   h=Tr2.mult(h,Tr2.move(x3,y3));
   return h;
   }
   */
}