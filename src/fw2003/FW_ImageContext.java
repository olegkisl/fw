package fw2003;

import grammar.*;
import java.util.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.event.*;
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
public class FW_ImageContext {

    final static int maxCount = 5000;
    long startTime = System.currentTimeMillis();
    long timeInterval = FW_Parm.getRepaintTime();
    BufferedImage img = null;
    Graphics2D g2img = null;
    int maxX = 1;
    int maxY = 1;
    int deltaX=0;  // used for collage  building
    int deltaY=0;
    int maxXw = 1; // Always maximum image size to paint
    int maxYw = 1;
    JPanel panel;
    int count = 0;
    ColorModel cmod = ColorModel.getRGBdefault();
    //////////////////////////
    private Object applicationContext = null;

    public Object getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Object c) {
        applicationContext = c;
    }

    public Graphics2D getGraphics2D() {
        return g2img;
    }

    public BufferedImage getImage() {
        return img;
    }

    //////////////////////////
    public void normalizeContext() {
        if (applicationContext == null) {
            return;
        }
        Active act = (Active) getApplicationContext();
        Tr2 a = act.h;
        AffineTransform t = new AffineTransform(a.h[0][0], a.h[0][1], a.h[1][0],
                a.h[1][1], a.h[2][0], a.h[2][1]);
        transform(t);
        act.h = new Tr2();
    }

    public FW_ImageContext(BufferedImage img, Graphics2D g2img, JPanel panel) {
        this.img = img;
        this.g2img = g2img;
        this.panel = panel;
        maxXw = img.getWidth();
        maxYw = img.getHeight();
        maxX = img.getWidth();
        maxY = img.getHeight();
        deltaX=0;  
        deltaY=0;
        stopped = false;
        g2img.setStroke(new BasicStroke(0.0f));
    }
    
    
     public FW_ImageContext(BufferedImage img, Graphics2D g2img, JPanel panel, int xmax, int ymax, int xdelta, int ydelta) {
        this.img = img;
        this.g2img = g2img;
        this.panel = panel;
        maxXw = img.getWidth();
        maxYw = img.getHeight();
        maxX = xmax;  //img.getWidth();
        maxY = ymax; //img.getHeight();
        deltaX=xdelta;  
        deltaY=ydelta;
        System.out.println("DELTA_____>>>>" + deltaX+" "+deltaY);
        stopped = false;
        g2img.setStroke(new BasicStroke(0.0f));
    }
     
    private void setRGB(int nx,int ny, int nn){
       int nnx=nx+deltaX;
       int nny=ny+deltaY;
       if(nnx<0 || nny<0 || nnx>=maxXw || nny>=maxYw) return;
       img.setRGB(nnx, nny, nn);
    }
    
    private int getRGB(int nx,int ny){
       int nnx=nx+deltaX;
       int nny=ny+deltaY;
       if(nnx<0 || nny<0 || nnx>=maxXw || nny>=maxYw) return 0;
       return img.getRGB(nx, ny);   
    }
      
   
/////////// set Transformation
    public AffineTransform getTransform() {
        return g2img.getTransform();
    }

    public void setTransform(AffineTransform t) {
        g2img.setTransform(t);
    }

    public void transform(AffineTransform t) {
        g2img.transform(t);
    }

/////////// set Painting Attributes
    public void setColor(Color c) {
        g2img.setColor(c);
    }

    public void setWidth(double d) {
        g2img.setStroke(new BasicStroke((float) d));
    }

    public void setAlfa(float a) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                a);
        g2img.setComposite(ac);
    }

    public void setQuality(int nn) {
        if (nn == 1) {
            g2img.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2img.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2img.setRenderingHint(RenderingHints.KEY_DITHERING,
                    RenderingHints.VALUE_DITHER_ENABLE);
            g2img.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2img.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2img.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                    RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            //   g2img.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_NORMALIZE);

        } else if (nn == 2) {
            g2img.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2img.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_DEFAULT);
            g2img.setRenderingHint(RenderingHints.KEY_DITHERING,
                    RenderingHints.VALUE_DITHER_DEFAULT);
            g2img.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2img.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
            g2img.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                    RenderingHints.VALUE_COLOR_RENDER_DEFAULT);

        } else {
            g2img.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g2img.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_SPEED);
            g2img.setRenderingHint(RenderingHints.KEY_DITHERING,
                    RenderingHints.VALUE_DITHER_DISABLE);
            g2img.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g2img.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            g2img.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                    RenderingHints.VALUE_COLOR_RENDER_SPEED);

        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////
/////////// Direct Point Painting :Depricated
    public void drawPoint(double x, double y, double gray, double alfa) {
        count++;
        if (count > maxCount) {
            count = 0;
            repaint();
        }
        // trans(x,y) ;
        // paint;
    }

    public void drawPoint(double x, double y, double r, double g, double b,
            double alfa) {
        count++;
        if (count > maxCount) {
            count = 0;
            repaint();
        }
        // trans(x,y) ;
        // paint;
    }
//////////////////////////////////////////////////////////////////////////////////////
////////// Points Itteration for F(x,y) style painting
/////////////////////////////////////////////////////////////////////////////////////
    //  new vresion with    Antialiasing/smoothing
    double shf = 0.55;
    double[] add_x = {0.0, shf, 0.0, -shf, 0.0, shf, -shf, shf, -shf};
    double[] add_y = {0.0, 0.0, shf, 0.0, -shf, shf, -shf, -shf, shf};
    double[] weight = {1, 0.55, 0.55, 0.55, 0.55, 0.35, 0.35, 0.35, 0.35};
    int n_repeat_max = 1; // paint type
///////////////////////////////////////////////////////////////////////////////////////
    double rsum = 0;
    double gsum = 0;
    double bsum = 0;
    double wsum = 0;
    int n_repeat = 0;
    int nx = 0;
    int ny = 0;
    double px = 0;
    double py = 0;
    AffineTransform c_trans = null;
    double m00 = 1, m10 = 1, m01 = 1, m11 = 1, m02 = 1, m12 = 1, px1 = 0,
            py1 = 0;

    public void pStart() { // start direct painting the Image
        // set paint type
        n_repeat_max = FW_Parm.getPaintType();
        if (n_repeat_max < 1) {
            n_repeat_max = 1;
        }
        if (n_repeat_max > 9) {
            n_repeat_max = 9;
        }
        // n_repeat_max = 9;///test
        n_repeat = 0;
        rsum = 0;
        gsum = 0;
        bsum = 0;
        wsum = 0;
        ////////////////
        nx = 0;
        ny = 0;
        px = 0;
        py = 0;
        c_trans = g2img.getTransform();
        try {
            c_trans = c_trans.createInverse();
        } catch (Exception e) {
            ;
        }
        double[] w = new double[6];
        c_trans.getMatrix(w);
        m00 = w[0];
        m10 = w[1];
        m01 = w[2];
        m11 = w[3];
        m02 = w[4];
        m12 = w[5];
        px = m02;
        py = m12;
    }

    public void pStart(PaintTransformation pt) { // start with saved Transformation
        // set paint type
        n_repeat_max = FW_Parm.getPaintType();
        if (n_repeat_max < 1) {
            n_repeat_max = 1;
        }
        if (n_repeat_max > 9) {
            n_repeat_max = 9;
        }
        // n_repeat_max = 9;///test
        n_repeat = 0;
        rsum = 0;
        gsum = 0;
        bsum = 0;
        wsum = 0;
        ////////////////
        nx = 0;
        ny = 0;
        px = 0;
        py = 0;

        double dxx =pt.maxX / (double)maxX;
        double dyy =pt.maxY / (double)maxY;

        m00 = pt.m00*dxx;
        m10 = pt.m10*dxx;   // dyy;
        m01 = pt.m01*dyy;   ///dxx;
        m11 = pt.m11*dyy;
        m02 = pt.m02;
        m12 = pt.m12;
        px = m02;
        py = m12;
    }

    PaintTransformation getPaintTransformation(){
        c_trans = g2img.getTransform();
        try {
            c_trans = c_trans.createInverse();
        } catch (Exception e) {
            ;
        }
        double[] w = new double[6];
        c_trans.getMatrix(w);
        PaintTransformation pt = new PaintTransformation(w[0],w[1],w[2],w[3],w[4],w[5],
                maxX, maxY);
        return pt;
    }

    public boolean pNext() { // next Image point, set (px, py) coordinte
        n_repeat++;
        if (n_repeat >= n_repeat_max) {
            n_repeat = 0;
            nx++;
            if (nx < maxX) {
                ;
            } else {
                ny++;
                nx = 0;
                if (ny < maxY) {
                    ;
                } else {
                    ny = 0;
                    return false;
                }
            }
        }
        px1 = ((double) nx) + add_x[n_repeat]; // / maxX;
        py1 = ((double) ny) + add_y[n_repeat]; /// maxY;
        px = m00 * px1 + m01 * py1 + m02;
        py = m10 * px1 + m11 * py1 + m12;
        return true;
    }

    public double pX() { // get current x value to calculate f(x,y)
        return px;
    }

    public double pY() { // get current y value to calculate f(x,y)
        return py;
    }

    public int pGet() { // get current RGB value
        return getRGB(nx, ny);
    }

    public void pSet(int r, int g, int b) { // set the result after calculation of  f(x,y)
        ///// quick draft paint
        if (n_repeat_max <= 1) {
            int nn = b + 256 * g + (256 * 256) * r;
            setRGB(nx, ny, nn);
            count++;
            if (count > maxCount) {
                count = 0;
                repaint();
            }
            return;
        }

        //// presentation paint
        rsum += r * weight[n_repeat];
        gsum += g * weight[n_repeat];
        bsum += b * weight[n_repeat];
        wsum += weight[n_repeat];

        if (n_repeat == n_repeat_max - 1) {
            r = (int) (rsum / wsum);
            g = (int) (gsum / wsum);
            b = (int) (bsum / wsum);
            int nn = b + 256 * g + (256 * 256) * r;
            setRGB(nx, ny, nn);
            count++;
            if (count > maxCount) {
                count = 0;
                repaint();
            }
            rsum = 0;
            gsum = 0;
            bsum = 0;
            wsum = 0;
        }
    }

    ///// old version of point iteration ( no smoothing )
 /*    int nx = 0;
    int ny = 0;
    double px = 0;
    double py = 0;
    AffineTransform c_trans = null;
    double m00 = 1, m10 = 1, m01 = 1, m11 = 1, m02 = 1, m12 = 1, px1 = 0,
    py1 = 0;

    public void pStart() { // start direct painting the Image
    nx = 0;
    ny = 0;
    px = 0;
    py = 0;
    c_trans = g2img.getTransform();
    try {
    c_trans = c_trans.createInverse();
    } catch (Exception e) {
    ;
    }
    double[] w = new double[6];
    c_trans.getMatrix(w);
    m00 = w[0];
    m10 = w[1];
    m01 = w[2];
    m11 = w[3];
    m02 = w[4];
    m12 = w[5];
    px = m02;
    py = m12;
    }

    public boolean pNext() { // next Image point, set (px, py) coordinte
    nx++;
    if (nx < maxX) {
    ;
    } else {
    ny++;
    nx = 0;
    if (ny < maxY) {
    ;
    } else {
    ny = 0;
    return false;
    }
    }
    px1 = ((double) nx); // / maxX;
    py1 = ((double) ny); /// maxY;
    px = m00 * px1 + m01 * py1 + m02;
    py = m10 * px1 + m11 * py1 + m12;
    return true;
    }

    public double pX() { // get current x value to calculate f(x,y)
    return px;
    }

    public double pY() { // get current y value to calculate f(x,y)
    return py;
    }

    public int pGet() { // get current RGB value
    return img.getRGB(nx, ny);
    }


    public void pSet(int r, int g, int b) { // set the result after calculation of  f(x,y)
    int nn = b + 256 * g + (256 * 256) * r;
    img.setRGB(nx, ny, nn);
    count++;
    if (count > maxCount) {
    count = 0;
    repaint();
    }
    }*/
////////////////////////////////////////////// //////////////////// ///////////////////////////////
///// END POINT ITERATION
//////////////////////////////////////////////////////////////////////////////////////////////////
 /*   public void pSet(double r1, double g1, double b1) { // set the result after calculation of  f(x,y)
    int r = (int) (r1 * 255.0);
    int g = (int) (g1 * 255.0);
    int b = (int) (b1 * 255.0);
    int nn = b + 256 * g + (256 * 256) * r;
    //  System.out.println(r + " "+g + " "+b + " "+ nn +"---" + img.getRGB(nx, ny));
    img.setRGB(nx, ny, nn);
    //  System.out.println("---" + img.getRGB(nx, ny));
    count++;
    if (count > maxCount) {
    count = 0;
    repaint();
    }

    }
     */
////////////////////////////////////////////////////////////////////////////////////////
////////// Random accsess to Direct Points Painting
///////////////////////////////////////////////////////////////////////////////////////
    public void randomPointsStart() {
        nx = 0;
        ny = 0;
        px = 0;
        py = 0;
        c_trans = g2img.getTransform();
        double[] w = new double[6];
        c_trans.getMatrix(w);
        m00 = w[0];
        m10 = w[1];
        m01 = w[2];
        m11 = w[3];
        m02 = w[4];
        m12 = w[5];
        px = m02;
        py = m12;
    }

    public void pDirectSet(double x, double y, double r1, double g1, double b1) {
        px = m00 * x + m01 * y + m02;
        py = m10 * x + m11 * y + m12;
        nx = (int) px;
        ny = (int) py;
        if (nx < 0) {
            return;
        }
        if (ny < 0) {
            return;
        }
        if (nx >= maxX) {
            return;
        }
        if (ny >= maxY) {
            return;
        }
        int r = (int) (r1 * 255.0);
        int g = (int) (g1 * 255.0);
        int b = (int) (b1 * 255.0);
        int nn = b + 256 * g + (256 * 256) * r;
        setRGB(nx, ny, nn);
        count++;
        if (count > maxCount) {
            count = 0;
            repaint();
        }

    }

    public void pDirectSet(double x, double y, double r1, double g1, double b1,
            double alfa) {

        px = m00 * x + m01 * y + m02;
        py = m10 * x + m11 * y + m12;
        nx = (int) px;
        ny = (int) py;
        if (nx < 0) {
            return;
        }
        if (ny < 0) {
            return;
        }
        if (nx >= maxX) {
            return;
        }
        if (ny >= maxY) {
            return;
        }
        int nnn = getRGB(nx, ny);
        int nb0 = nnn & 0xff;
        int ng0 = (nnn >> 8) & 0xff;
        int nr0 = (nnn >> 16) & 0xff;
        double alfa1 = 1.0 - alfa;

        int r = (int) (alfa * r1 * 255.0 + alfa1 * nr0);
        if (r < 0) {
            r = 0;
        }
        if (r > 255) {
            r = 255;
        }
        int g = (int) (alfa * g1 * 255.0 + alfa1 * ng0);
        if (g < 0) {
            g = 0;
        }
        if (g > 255) {
            g = 255;
        }
        int b = (int) (alfa * b1 * 255.0 + alfa1 * nb0);
        if (b < 0) {
            b = 0;
        }
        if (b > 255) {
            b = 255;
        }

        int nn = b + 256 * g + (256 * 256) * r;
        setRGB(nx, ny, nn);
        // System.out.println(" "+nr0+" "+ng0+" "+nb0+" "+r1+" "+g1+" "+b1+" "+alfa+" "+r+" "+g+" "+b);
        count++;
        if (count > maxCount) {
            count = 0;
            repaint();
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////
////////// General Painting  and Drawing ////////
    public void draw(Shape s) {
        count += 15;
        if (count > maxCount) {
            count = 0;
            repaint();
        }
        g2img.draw(s);
    }

    public void fill(Shape s) {
        count += 100;
        if (count > maxCount) {
            count = 0;
            repaint();
        }
        g2img.fill(s);
    }

    /////////// REPAINT   ////////////////////////////////////////////////////////
    private void repaintCheck() {
        if (startTime > System.currentTimeMillis()) {
            panel.repaint();
            Thread.yield();
            startTime = System.currentTimeMillis() + timeInterval;
        }
    }

    public void repaint() {
        panel.repaint();
        Thread.yield();
    }
    ////////////////////////////// STOP PAINTING
    static boolean stopped = false;

    public void stopPaint() {
        stopped = true;
    }

    public boolean isPaintStoped() {
        return stopped;
    }
}
