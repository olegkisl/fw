package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_FRACT_3 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_FRACT_3() {
        super("A3_FRACT_3", 3, 40);
        // set_param();
    }
    ////////////////////////////////////////////////// 
    private double[] scores = {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0};

    private double get_score(int nn) {
        double res = 0;
        int k = (int) (params[15]*7.0);
        switch (k) {
            case 0: {
                res = 0;
                for (int j = 0; j < nn; j++) {
                    res = res + scores[j];
                }
                res = res / nn;
            }
            ;
            break;

            case 1: {
                res = 1;
                for (int j = 0; j < nn; j++) {
                    res = res * (scores[j] + 0.5 * params[j]);
                }
            }
            ;
            break;

            case 2: {
                res = 0;
                for (int j = 0; j < nn; j++) {
                    if (res < scores[j] * params[j]) {
                        res = scores[j] * params[j];
                    }
                }
            }
            ;
            break;

            case 3: {
                res = 1;
                for (int j = 0; j < nn; j++) {
                    if (res > scores[j] * (params[j] + 1)) {
                        res = scores[j] * (params[j] + 1);
                    }
                }
            }
            ;
            break;

            case 4: {
                res = 0;
                for (int j = 0; j < nn; j++) {
                    res += scores[j] * (1 - scores[j]) * params[j];
                }
            }
            ;
            break;
            case 5: {
                res = 0;
                for (int j = 0; j < nn; j++) {
                    res = res+scores[j] * (1 - params[j]) * params[j];
                }
            }
            ;
            break;
            default: {
                res = 0;
                for (int j = 0; j < nn; j++) {
                    res = res * params[j + 8] + scores[j] * params[j];
                }
            }
        }
        return res;
    }

    private double xa(double x, double y) {
        double x1 = x * 2.5 * (0.55 + params[16]) + (0.5 + params[11]) * y * Math.sin(1 + 4 * params[12] * y);
        return (x1);
    }

    private double ya(double x, double y) {
        double y1 = y * 2.5 * (0.55 + params[17]) + (0.5 + params[13]) * x * Math.sin(1 + 4 * params[14] * x);
        return (0.5 + params[15]) * Math.cos(y1);
    }

//////////////////////////////////////////////////    
    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }
        Block_Prototype_Fxy gg = (Block_Prototype_Fxy) sons[1];
        if (gg == null) {
            return 0.5;
        }
        Block_Prototype_Fxy ggg = (Block_Prototype_Fxy) sons[2];
        if (ggg == null) {
            return 0.5;
        }

        double cc = 1.0;
        double rrr = 0;//fff(x, y, 100);
        int nn = (int) (params[7] * 7 + 1);
        for (int j = 0; j < nn; j++) {
            //cc = cc * 2.0;
            double x1 = x;
            double y1 = y;
            // double h = ptrn1(x1, y1, cc, j);
            double h = 0;
            if (params[j + 1] > 0.6) {
                h = g.f(x1, y1);
            } else if (params[j + 1] > 0.2) {
                h = gg.f(x1, y1);
            }  else {
                h = ggg.f(x1, y1);
            }
            scores[j] = h;               
            x = x1 * (1.8 + params[7+j])+params[1+j];
            y = y1 * (1.8 + params[15+j])+params[22+j];

        }
        
        rrr =  get_score(nn);
                
        if (rrr < 4 && rrr > -4) {
            if (rrr > 0) {
                rrr = Math.pow(rrr, 8 * params[9]);
            } else {
                rrr = -Math.pow(-rrr, 5 * params[9]);
            }
        }
        if (params[10] > 0.5) {
            rrr = 1 - rrr;
        }
        return rrr;
    }
}

