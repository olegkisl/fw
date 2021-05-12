package blocks;

import fw2003.*;

public class AAC_EXPERIMENT extends Block_Prototype_Fclr {

    static final long serialVersionUID = 1L;
    double sc = 10.0;

    public AAC_EXPERIMENT() {
        super(null, 1, 7);
    }

    public Fclr fc(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return getFclr(ar, ag, ab);
        }
        int cfl = 0;
        double r = 0, rw = 1;
        double rr1;
        double gg1;
        double bb1;
        
        r = g.f(x, y) * params[0] + params[1]-params[2];

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

        if (r < 0) {
            r = r + 1.0;
        }
        if (r > 1) {
            r = 0.9999;
        }

        rw = 1 - r;
        if (cfl == 1) {
            rr1 =  (ar * r + br * rw);
            gg1 =  (ag * r + bg * rw);
            bb1 =  (ab * r + bb * rw);
        } else if (cfl == 2) {
            rr1 =  (ar * r + cr * rw);
            gg1 =  (ag * r + cg * rw);
            bb1 =  (ab * r + cb * rw);
        } else if (cfl == (-1)) {
            rr1 =  (ar * r + dr * rw);
            gg1 =  (ag * r + dg * rw);
            bb1 =  (ab * r + db * rw);
        } else {
            rr1 =  (ar * r + er * rw);
            gg1 =  (ag * r + eg * rw);
            bb1 =  (ab * r + eb * rw);
        }
        return getFclr(rr1, gg1, bb1);
    }
}
