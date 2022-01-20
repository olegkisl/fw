package fw2003;

import javax.swing.JInternalFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.JDesktopPane;
import java.util.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.swing.JPanel;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author Oleg Kislyuk
 * @version 3.0
 */
public class FW_ImagePanel extends JPanel implements MouseListener,
        MouseMotionListener, KeyListener {

    public static final Color defaultColor = new Color(160, 158, 154);
    
    public void  dispose(){
        if (g2img != null)
             g2img.dispose();
    }
    
    Color bcgr = defaultColor;
    //int lmax =500;
    //int hmax =400;
    int x = 0, y = 0, xend = 0, yend = 0;
    double scale = 1.0;
    final static double scaleMax = 10.0;
    final static double scaleMin = 0.1;
    int ddx = 0, ddy = 0;

    public double changeScale(double c) {
        double u = scale * c;
        if ((u <= scaleMax) && (u >= scaleMin)) {
            scale = u;
        }

        int a = (int) (l_img * scale);
        int b = (int) (h_img * scale);
        this.setPreferredSize(new Dimension(a, b));
        this.setMaximumSize(new Dimension(a, b));
        this.setMinimumSize(new Dimension(a, b));
        repaint();
        return scale;
    }

    private int xTB(int x) {
        return ddx + (int) (x * scale);
    }

    private int yTB(int y) {
        return ddy + (int) (y * scale);
    }

    private int xTA(int x) {
        return (int) ((x - ddx) / scale);
    }

    private int yTA(int y) {
        return (int) ((y - ddy) / scale);
    }
    BufferedImage img = null;
//  BufferedImage img_b = null;
    Graphics2D g2img = null;
    AffineTransform g2img_trans = null;
    AffineTransform current_trans = null;
    int l_img = 0;
    int h_img = 0;

    private void init() {
        g2img = img.createGraphics();
        g2img_trans = g2img.getTransform();
        // clear();
        this.setPreferredSize(new Dimension(l_img, h_img));
        this.setMaximumSize(new Dimension(l_img, h_img));
        this.setMinimumSize(new Dimension(l_img, h_img));
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        x = l_img / 2;
        y = h_img - 2;
        xend = l_img / 2;
        yend = h_img / 2;
    }

    public FW_ImagePanel(int l, int h) {
        img = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
        //   img_b = new
        //       BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
        l_img = l;
        h_img = h;
        init();
        clear();
    }

    public FW_ImagePanel(int l, int h, BufferedImage bf) {
        img = bf; // new
        // BufferedImage(l,h,BufferedImage.TYPE_INT_ARGB);
        //   img_b = new
        //       BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
        l_img = l;
        h_img = h;
        init();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        update(g);
    }

    public void update(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        int w = getSize().width;
        int h = getSize().height;
        //  if (pressed) {//// Debug
        //    g2img.setPaint(Color.magenta);
        //    g2img.draw(new Line2D.Double(x, y,
        //                         xend, yend));
        //  }

        int nw = img.getWidth();
        int nh = img.getHeight();
        int nwa = (int) (nw * scale);
        int nha = (int) (nh * scale);

        if (nwa < w) {
            ddx = (w - nwa) / 2;
        } else {
            ddx = 0;
        }
        if (nha < h) {
            ddy = (h - nha) / 2;
        } else {
            ddy = 0;
        }

        g2.drawImage(img, ddx, ddy, nwa, nha, this);

        //   g2.drawImage(img, 0, 0, this);

        if (pressed) {
            g2.setPaint(Color.magenta);
            int xv = xTB(x);
            int yv = yTB(y);
            int xvend = xTB(xend);
            int yvend = yTB(yend);
            g2.draw(new Line2D.Double(xv, yv,
                    xvend, yvend));
            g2.draw(new Line2D.Double(xv - 1, yv,
                    xv + 1, yv));
            g2.draw(new Line2D.Double(xv, yv - 1,
                    xv, yv + 1));
            g2.draw(new Line2D.Double(xv - 1, yv - 1,
                    xv + 1, yv + 1));
            g2.draw(new Line2D.Double(xv - 1, yv + 1,
                    xv + 1, yv - 1));
        }

    }

    public void setBackgroundClr(Color b) {
// setBackground(b);?
        bcgr = b;
// g2img.setColor(b);
// g2img.fillRect(0,0,l_img,h_img);
        this.repaint();
    }

    public void clear() {
        g2img.setTransform(g2img_trans);
        g2img.setColor(bcgr);
        g2img.setPaint(bcgr);
        g2img.fillRect(0, 0, l_img, h_img);
        this.repaint();
    }
    private Raster rbuf = null; // for undo

    public void save() {
        rbuf = img.getData();
    }

    public void undo() {
        stopPaint();
        img.setData(rbuf);
        this.repaint();
    }
//////// Mouse Events Processing for Image
    private boolean pressed = false;

    public void mousePressed(MouseEvent e) {
        pressed = true;
        x = xTA(e.getX());
        y = yTA(e.getY());
        xend = x;
        yend = y;
    }

    public void mouseDragged(MouseEvent e) {
        if (pressed) {
            xend = xTA(e.getX());
            yend = yTA(e.getY());
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        xend = xTA(e.getX());
        yend = yTA(e.getY());
        // pressed = false;
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    private void add(int dx, int dy, boolean alt) {
        if (alt == false) {
            x += dx;
            y += dy;
        }
        xend += dx;
        yend += dy;
    }

    public void keyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        int d = 1;
        boolean alt = false;
        if (evt.isAltDown()) {
            alt = true;
        }
        if (evt.isShiftDown()) {
            d = 10;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            add(-d, 0, alt);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            add(d, 0, alt);
        } else if (keyCode == KeyEvent.VK_UP) {
            add(0, -d, alt);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            add(0, d, alt);
        }
    }

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }
/////////////// Painting Process ///////////////////////////
    FW_ImagePaint imagePaintThread = null;
    FW_ImageContext icont = null;

    public void startPaint(FW_BlockInterface b) {
        pressed = false;
        //  if(imagePaintThread == null){ //Uniq Painting thread
        int ax = (xend - x);
        int ay = (yend - y);
        int rr = ax * ax + ay * ay;
        if (rr == 0) {
            return;
        }
        double scl = Math.sqrt((double) rr);
        double sn = ay / scl, cs = ax / scl;
        //     AffineTransform hh = new AffineTransform(cs, sn, -sn, cs, 0.0, 0.0);
        AffineTransform hh = new AffineTransform(sn, -cs, cs, sn, 0.0, 0.0);
        g2img.setStroke(new BasicStroke(0.0f));
        current_trans = AffineTransform.getTranslateInstance(x, y);
        current_trans.scale(scl, scl);
        current_trans.concatenate(hh);

        g2img.setTransform(g2img_trans);
        g2img.transform(current_trans);
        icont = new FW_ImageContext(img, g2img, this);
        imagePaintThread = new FW_ImagePaint(icont, b, this);
        imagePaintThread.start();
        // }
    }

    public void startPaint_synchronized(FW_BlockInterface b) {
        pressed = false;
        //  if(imagePaintThread == null){ //Uniq Painting thread
        int ax = (xend - x);
        int ay = (yend - y);
        int rr = ax * ax + ay * ay;
        if (rr == 0) {
            return;
        }
        double scl = Math.sqrt((double) rr);
        double sn = ay / scl, cs = ax / scl;
        //     AffineTransform hh = new AffineTransform(cs, sn, -sn, cs, 0.0, 0.0);
        AffineTransform hh = new AffineTransform(sn, -cs, cs, sn, 0.0, 0.0);
        g2img.setStroke(new BasicStroke(0.0f));
        current_trans = AffineTransform.getTranslateInstance(x, y);
        current_trans.scale(scl, scl);
        current_trans.concatenate(hh);

        g2img.setTransform(g2img_trans);
        g2img.transform(current_trans);
        icont = new FW_ImageContext(img, g2img, this);
        imagePaintThread = new FW_ImagePaint(icont, b, this);
        imagePaintThread.start();
        try {
            imagePaintThread.join();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }

    public void stopPaint() {
        if (icont != null) {
            icont.stopPaint();
        }
    }
}
