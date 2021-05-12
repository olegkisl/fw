package blocks;

/**
 * Vertical strips
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_BCGRH_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;
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

    public A3_BCGRH_0() {
        super("A3_BCGRH_0", 0, 40);
        // set_param();
    }

    private void set_param() {
        for (int i = 0; i < 35; i += 2) {
            mask[i] = 1;
            if (params[i] > 0.75 && (i/parts)%2==0) {
                mask[i] = 0;
            }
        }
    }

    public void mutate_this() {
        super.mutate_this();
        set_param();
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
        return (hash % mod) / (mod + 1.0);
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
        double rrr = 1;//fff(x, y, 100);
        double h = 0;
        h = ptrn3(x, y, (int) (10 + 10 * params[13]), 0);
        rrr = h;
        h = ptrn4(x, y, (int) (5 + 5 * params[14]), 1);
        if (h > 0) {
            if (params[16] > 0.5) {
                rrr = fff(x, y, 500);
            } else {
                rrr = h;
            }
        }

        if (rrr < 0) {
            rrr = 0;
        }
        if (rrr > 5) {
            rrr = 5;
        }

        //  rrr = Math.pow(rrr, 8 * params[9]);
        if (params[10] > 0.25) {
            rrr = 1 - rrr;
        }
        return rrr;
    }
}
