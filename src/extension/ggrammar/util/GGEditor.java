package extension.ggrammar.util;
import  extension.ggrammar.*;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

public class GGEditor
    extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  java.util.List ls = null;

  Color currentColor = Color.green;
  public void setColor(Color c) {
    currentColor = c;
  }

  public GGEditor(java.util.List ls) {
    try {
      this.ls = ls;
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }

      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }

      public void mouseClicked(MouseEvent e) {
        this_mouseClicked(e);
      }
    });
    this.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
    });
    repaint();
  }

  public void deleteLast() {
    if (edit) {
      edit = false;
      gg = null;
      n_points = 0;
    }
    else if (ls.size() > 0) {
      ls.remove(ls.size() - 1);
    }
    repaint();
  }

  public void deleteAll() {
    edit = false;
    gg = null;
    n_points = 0;
    ls.clear();
    repaint();
  }

  private int curveType = 1;
  public void setCurveType(int nn) {
    curveType = nn;
    n_points = 0;
  }

  AffineTransform to = null;
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int w = this.getWidth();
    int h = this.getHeight();

    if (to == null) {
      to = AffineTransform.getTranslateInstance(0.5 * w, h);
      AffineTransform t3 = AffineTransform.getRotateInstance(Math.PI);
      AffineTransform t1 = AffineTransform.getScaleInstance(h, h);
      to.concatenate(t3);
      to.concatenate(t1);
    }

    for (Iterator iter = ls.iterator(); iter.hasNext(); ) {
      GGElement item = (GGElement) iter.next();
      g2d.setPaint(item.getColor());
      //   g2d.setColor(item.clr);
      g2d.fill(item.getGeneralPath().createTransformedShape(to));
    }

    g2d.setPaint(Color.red);
    g2d.setColor(Color.red);
    if (edit) {
      g2d.draw(gg.getCurrentGeneralPath().createTransformedShape(to));
    }

  }

  public void this_mousePressed(MouseEvent e) {

  }

  public void this_mouseReleased(MouseEvent e) {

  }

  public void this_mouseDragged(MouseEvent e) {

  }

  GGElement gg = null;
  boolean edit = false;
  public void this_mouseClicked(MouseEvent e) {

    if ( (e.getButton() == MouseEvent.BUTTON3) && edit) {
      gg.closePath();
      ls.add(gg);
      gg=null;
      n_points = 0;
      edit = false;
    }
    else {
      if (!edit) {
        AffineTransform tr = new AffineTransform(to);
        try {
          tr = tr.createInverse();
        }
        catch (NoninvertibleTransformException ex) {
          tr = new AffineTransform();
        }
        gg= new GGElement(tr, currentColor);
        gg.moveTo(e.getX(), e.getY());
        n_points = 0;
        edit = true;
      }
      else {
        if (curveType == 1) {
          gg.lineTo(e.getX(), e.getY());
          n_points = 0;
        }
        else if (curveType == 2) {
          if (n_points == 2) {
            gg.quadTo(points[0], points[1], e.getX(), e.getY());
            n_points = 0;
          }
          else if (n_points == 0) {
            points[0] = e.getX();
            points[1] = e.getY();
            n_points = 2;
          }
        }
        else if (curveType == 3) {
          if (n_points == 4) {
            gg.curveTo(points[0], points[1], points[2], points[3], e.getX(),
                             e.getY());
            n_points = 0;
          }
          else if (n_points == 2) {
            points[2] = e.getX();
            points[3] = e.getY();
            n_points = 4;
          }
          else if (n_points == 0) {
            points[0] = e.getX();
            points[1] = e.getY();
            n_points = 2;
          }
        }
        else {
          n_points = 0;
        }

      }
    }

    repaint();
  }

  private int n_points = 0;
  private int[] points = new int[8];
}
