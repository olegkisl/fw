package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_EXP2_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_EXP2_0() {
        super("A3_EXP2_0", 1, 40);
        // set_param();
    }
    int high = 5;
    int width = 3;
    int layers = 6;
    int parts = 5;
    private int[] mask = {1, 1, 1, 1, 1,
        1, 1, 1, 1, 1,
        1, 1, 0, 1, 1,
        1, 1, 1, 1, 1,
        1, 1, 1, 1, 1,
        1, 1, 1, 0, 1,
        1, 1, 1, 1, 1,
        1, 1, 1, 1, 1,};
    private double[] uuu = {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0};
    private int divider = 10;

    private void set_param() {
        for (int i = 0; i < 35; i += 2) {
            mask[i] = 1;
            if (params[i] > 0.75) {
                mask[i] = 0;
            }
        }

        for (int i = 0; i < uuu.length; i++) {
            //if(params[i]>0.5) uuu[i]=1;
            // else uuu[i]=0;
            uuu[i] = params[i] * 2.5;
        }
        divider = (int) (4 + (uuu.length - 6) * params[15]);
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
    }

////////////////////////////////////////////////// 
    private double res(double x, double y) {

        double r = params[21] * x * x + params[22] * y * y + 0.5 + params[23];
        return 1.0 / r;
    }

     private double xa(double x, double y) {
        double x1 = x * 2.5 * (0.55 + params[16]) +(0.5+ params[11]) * y*Math.sin(1+4*params[12] * y);
        return (x1*x1/(1+params[29]*x*x))*Math.cos(params[7]*x1*y);
    }

    private double ya(double x, double y) {
        double y1 =  y * (0.55 + params[17]) * x*Math.sin(1+4*params[14] * x);
        return (0.5 + params[15]) * Math.cos(y1*x*params[15]);
    }
    
    private double xb(double x, double y) {
        return y/(1+x*x*params[15]) + params[17]*ptrn3(x*Math.cos(x*params[15]), y*y,  30, 1);
    }

    private double yb(double x, double y) {
        return  x*Math.cos(y*params[15]) + params[19]*ptrn1(x, y,  30, 4);
    }

    ////////////////////////////////////////////////  
    private double x1(double x, double y) {
        return x + y*params[17]*ptrn4(x, y,  30, 1);
    }

    private double y1(double x, double y) {
        return y + x*Math.sin( params[17]*ptrn4(x, y,  30, 4)); 
    }

    private double x2(double x, double y) {
        double a = x*(y*params[3]+10*params[1])/(y*y*params[4]+10*params[5]+0.001);
        return params[2]*x+Math.sin(a*params[23]);
    }

    private double y2(double x, double y) {
        double a = y*(x*x*params[13]+10*params[11])/(x*x*params[14]+10*params[15]+0.001);;
        return params[3]*y-a/(a*a+params[10]+0.1);
    }

    private double fff(double x, double y, double size) {
        mod = 50000;
        //Jenkins's one_at_a_time hash
        int nx = 0, ny = 0;
        nx = (int) (x * (alfa * size));
        ny = (int) (y * (alfa * size));
        int hash = 0;

        hash += nx;
        hash += hash << 10;
        hash ^= hash >> 6;
        hash += ny;
        hash += hash << 10;
        hash ^= hash >> 6;
        hash += hash << 3;
        hash ^= hash >> 11;
        hash += hash << 15;
        //System.out.println(hash%1000);
        //if (hash % mod < mod * probability) {
        //    return 1.0;
        //}
        return (hash % mod) / (mod + 1.0);
    }

    private double ptrn1(double x, double y, double size, int j) {
        int nx = 0, ny = 0;
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        nx = (int) (x * (alfa * size));
        if (nx < 0) {
            nx = 0;
        }
        ny = (int) (y * (alfa * size));
        if (ny < 0) {
            ny = 0;
        }
        j = nx * (nx + ny) + ny * j + 1 + j;
        int res = (nx + ny + j) % divider;
        if (res < 0) {
            res = 0;
        }
        return uuu[res];
    }

    private double ptrn2(double x, double y, double size, int j) {
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        double x1 = 0.5 * j + params[21] * x * (alfa * size);
        double y1 = 0.5 * j + params[23] * x * (alfa * size);
        int nx = 0, ny = 0;
        nx = (int) (x1);
        ny = (int) (y1);
        j = nx * (nx + ny) + ny * j + 1 + j;
        int r = (nx + ny + j) % divider;
        if (r < 0) {
            r = 0;
        }
        ////
        double xx = Math.sin(3.1459 * x1);
        double yy = Math.sin(3.1459 * y1);
        double res = xx + yy;
        if (res < 0) {
            res = 0;
        }
        if (res > 1) {
            res = 1;
        }
        ////
        return res + uuu[r];
    }

    private double ptrn3(double x, double y, double size, int j) {
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        double border = 0.001 * params[12];
        double border_color = 0.4 + 0.2 * params[13];
        int nx = 0, ny = 0;
        double x1 = x * (alfa * size);
        double y1 = y * (alfa * size);
        ny = (int) (y1);
        nx = (int) (x1);
        int yshift = ny % 2;
        int xnn = (yshift + nx) / 2;
        int xpos = (yshift + nx) % 2;
        x = x1 - nx;
        if (x * x < size * border && xpos == 0) {
            return border_color;
        }
        x = x1 - nx - 1;
        if (x * x < size * border && xpos == 1) {
            return border_color;
        }
        y = y1 - ny;
        if (y * y < size * border) {
            return border_color;
        }
        y = y1 - ny - 1;
        if (y * y < size * border) {
            return border_color;
        }

        j = (xnn) % 2;
        if (j == 0) {
            return 0.8 + 0.2 * params[10];
        } else {
            return 0.8 - 0.2 * params[11];
        }

    }

    private double ptrn4(double x, double y, double size, int j) {
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }

        //double border=0.001*params[12];
        //double border_color=0.4+0.2*params[13];
        int nx = 0, ny = 0, my = 0, mx = 1, maskx = 1;
        double x1 = x * (alfa * size);
        double y1 = y * (alfa * size);
        ny = (int) (y1);
        nx = (int) (x1);
        my = ny / high;
        mx = nx / width;
        maskx = mask[parts * (my % layers) + mx % parts];

        if (maskx == 1) {
            return -1;
        } else {
            return (params[11]);
        }

    }

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        double cc = 1.0;
        double rrr = 0.5, rrr1;
        if (params[13] > 0.75) {
            rrr = g.f(x1(x, y), y1(x, y));
        }else if (params[13] > 0.5) {
            rrr = g.f(xa(x, y), ya(x, y));
        } else if (params[13] > 0.25) {
             rrr = g.f(xb(x, y), yb(x, y));
        } else{
            rrr = g.f(x2(x, y), y2(x, y));
        }
        //rrr = 1.5*fff(x,  y, 10)*ptrn3(xa(x, y), ya(x, y),100,3);
        if (params[10] > 0.75) {
            rrr = 1 - rrr;
        }
        return rrr;
    }
}
