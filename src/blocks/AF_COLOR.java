package blocks;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author Oleg Kislyuk
 * @version 3.0
 */
import fw2003.*;
import java.awt.*;
import java.util.*;

// ROTATION
public class AF_COLOR extends Block_Prototype_Fxy implements FW_PalletEditorInterface {

    static final long serialVersionUID = 1L;
    int ar = 250, ag = 250, ab = 250,
            br = 0, bg = 0, bb = 0,
            cr = 110, cg = 50, cb = 50,
            dr = 50, dg = 110, db = 50,
            er = 50, eg = 50, eb = 110;// colors

    public AF_COLOR() {
        super("AF_COLOR", 1, 5);
    }

    ////////////// Interface with color editor implements FW_PalletEditorInterface
    public java.util.List<java.awt.Color> getColorsFromObject() {
        java.util.List<java.awt.Color> clrs = new ArrayList<java.awt.Color>();
        clrs.add(new Color(ar, ag, ab));
        clrs.add(new Color(br, bg, bb));
        clrs.add(new Color(cr, cg, cb));
        clrs.add(new Color(dr, dg, db));
        clrs.add(new Color(er, eg, eb));
        return clrs;
    }

    public void getColorsToObject(java.util.List<java.awt.Color> clrs) {
        for (int i = 0; i < clrs.size(); i++) {
            Color c = clrs.get(i);
            switch (i) {
                case 0:
                    ar = c.getRed();
                    ag = c.getGreen();
                    ab = c.getBlue();
                    break;
                case 1:
                    br = c.getRed();
                    bg = c.getGreen();
                    bb = c.getBlue();
                    break;
                case 2:
                    cr = c.getRed();
                    cg = c.getGreen();
                    cb = c.getBlue();
                    break;
                case 3:
                    dr = c.getRed();
                    dg = c.getGreen();
                    db = c.getBlue();
                    break;
                case 4:
                    er = c.getRed();
                    eg = c.getGreen();
                    eb = c.getBlue();
                    break;

            }
        }
    }

    ;
    
     //Free Form Editor////////////////////////////////////////////////
    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public javax.swing.JPanel getEditor() {
        return new FW_BlockPalletEditor(this);
    }
//////////////////////////////////////////////////////////////////////////
    
    

    public boolean mutatePallet1(FW_PalletInterface p) {
        if (p.getColorsList().size() > 1) {
            return false;
        }
        Color c = p.getColor();
        int n = FW_Rand.getCounter() % 5;
        switch (n) {
            case 0:
                ar = c.getRed();
                ag = c.getGreen();
                ab = c.getBlue();
                break;
            case 1:
                br = c.getRed();
                bg = c.getGreen();
                bb = c.getBlue();
                break;
            case 2:
                cr = c.getRed();
                cg = c.getGreen();
                cb = c.getBlue();
                break;
            case 3:
                dr = c.getRed();
                dg = c.getGreen();
                db = c.getBlue();
                break;
            case 4:
                er = c.getRed();
                eg = c.getGreen();
                eb = c.getBlue();
                break;

        }
        return true;
    }

    public void mutatePallet_this(FW_PalletInterface p) {
        if (mutatePallet1(p)) {
            return;
        }
        Color c, cw = Color.WHITE;
        c = p.getColor();
        ar = c.getRed();
        ag = c.getGreen();
        ab = c.getBlue();

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                br = cw.getRed();
                bg = cw.getGreen();
                bb = cw.getBlue();
            }
        }

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                cr = cw.getRed();
                cg = cw.getGreen();
                cb = cw.getBlue();
            }
        }

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                dr = cw.getRed();
                dg = cw.getGreen();
                db = cw.getBlue();
            }
        }

        for (int i = 0; i < 10; i++) {
            cw = p.getColor();
            if (cw != c) {
                er = cw.getRed();
                eg = cw.getGreen();
                eb = cw.getBlue();
            }
        }
    }

    public void paint(FW_ImageContext ct, int depth) {
        if (depth <= 0) {
            return;
        }
        int cfl = 0;
        double r = 0, rw = 1;
        //ct.pStart();
        startPainting(ct);
        int rr1;
        int gg1;
        int bb1;
        double bright = FW_Parm.getBritness();
        while (ct.pNext()) {
            if (ct.isPaintStoped()) {
                ct.repaint();
                return;
            }
            r = f(ct.pX(), ct.pY()); // u*Math.sin(a*ct.pX())+v*Math.sin(b*ct.pY());

            if (r < 0) {
                r = -r;
                cfl = 1;
            } else {
                cfl = 2;
            }

            if (r > 1) {
                r = 1.0 / r;
                cfl = -cfl;
            }

            r = r * bright;
            if (r < 0) {
                r = r + 1.0;
            }
            if (r > 1) {
                r = 0.9999;
            }

            rw = 1 - r;
            if (cfl == 1) {
                rr1 = (int) (ar * r + br * rw);
                gg1 = (int) (ag * r + bg * rw);
                bb1 = (int) (ab * r + bb * rw);
            } else if (cfl == 2) {
                rr1 = (int) (ar * r + cr * rw);
                gg1 = (int) (ag * r + cg * rw);
                bb1 = (int) (ab * r + cb * rw);
            } else if (cfl == (-1)) {
                rr1 = (int) (ar * r + dr * rw);
                gg1 = (int) (ag * r + dg * rw);
                bb1 = (int) (ab * r + db * rw);
            } else {
                rr1 = (int) (ar * r + er * rw);
                gg1 = (int) (ag * r + eg * rw);
                bb1 = (int) (ab * r + eb * rw);
            }


            ct.pSet(rr1, gg1, bb1);
        }

    }

    public double f(double x, double y) { // just return son coeficient
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        return g.f(x, y);
    }
}
