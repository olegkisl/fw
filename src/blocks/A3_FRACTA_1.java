package blocks;

/**
 *
 * @author okislyuk
 */
import fw2003.*;

public class A3_FRACTA_1 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;
    int mod = 100000;
    double alfa = 1.0;

    public A3_FRACTA_1() {
        super("A3_FRACTA_1", 1, 40);
        // set_param();
    }
    ////////////////////////////////////////////////// 
    private double[] scores = {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0};

    private double get_score(int nn) {
        double res = 0;
        int k = (int) (params[15] * 7.0);
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
                    res = res + scores[j] * (1 - params[j]) * params[j];
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

/////////////////////////////////////////////////////////////////////////////////
    private double get_c(double x, double y, int ind) {
        double res = 0;
        int k = (int) (params[15 + ind] * 21);
        switch (k) {
            case 0: {
                double x1 = params[1] * Math.sin(params[2] * x) + params[3] * Math.cos(params[4] * y) + x;
                res = (0.5 + params[5]) * Math.cos(x1);
            }
            break;

            case 1: {
                double y1 = params[11] * Math.sin(params[12] * x) + params[13] * Math.cos(params[14] * y) + y;
                res = (0.5 + params[15]) * Math.cos(y1);
            }
            break;

            case 2: {
                res = x * Math.sin(params[11] * y);
            }
            break;

            case 3: {
                res = x * Math.cos(params[12] * y);
            }
            break;

            case 4: {
                double a = params[12] + 0.5;
                res = Math.sqrt(a * y * y + params[25] * x * x);
            }
            break;

            case 5: {
                if (x < 0.0001 && x > -0.0001) {
                    x = 0.0001;
                }
                res = Math.tan(y / x);
            }
            break;

            case 6: {
                double a = x * (y * y * params[3] + 10 * params[1]) / (y * y * params[4] + 10 * params[5] + 0.001);
                res = a;
            }
            break;

            case 7: {
                double a = y * (x * x * params[13] + 10 * params[11]) / (x * x * params[14] + 10 * params[15] + 0.001);;
                res = a;
            }
            break;

            case 8: {
                double x1 = x * 2.5 * (0.55 + params[16]) + (0.5 + params[11]) * y * Math.sin(1 + 4 * params[12] * y);
                res = (x1 * x1 / (1 + params[29] * x * x)) * Math.cos(params[7] * x1 * y);
            }
            break;
            case 9: {
                double y1 = y * (0.55 + params[17]) * x * Math.sin(1 + 4 * params[14] * x);
                res = (0.5 + params[15]) * Math.cos(y1 * x * params[15]);
            }
            break;
            case 10: {
                double a = x * (y * params[3] + 10 * params[1]) / (y * y * params[4] + 10 * params[5] + 0.001);
                res = params[2] * x + Math.sin(a * params[23]);
            }
            break;
            case 11: {
                res = x * Math.cos(y * params[15]) + params[19];
            }
            break;
            case 12: {
                double a = y * (x * x * params[13] + 10 * params[11]) / (x * x * params[14] + 10 * params[15] + 0.001);;
                res = params[3] * y - a / (a * a + params[10] + 0.1);
            }
            break;
            case 13: {
                double x1 = Math.atan(params[29] * y) + Math.cos(params[22] * x);
                res = x1 * x1;
            }
            break;
            case 14: {
                double y1 = Math.cos(y * y * params[17]);
                res = (1 + params[15]) * Math.cos(y1 + y * params[15]);
            }
            break;
            case 15: {
                res = Math.atan(params[29] * y) / (1 + x * x * params[15]);
            }
            break;
            case 16: {
                res = x + params[15] * Math.cos(y * params[15]);
            }
            break;
            case 17: {
                res = Math.cos(x * params[25] + Math.cos(y * params[5])) / (x * x + y * y * params[17]);
            }
            break;
            case 18: {
                res = y * params[9] + x * Math.atan(x * params[7]);
            }
            break;
            case 19: {
                double a = x * (y * params[3]);
                res = params[2] * x + Math.sin(a * params[23]);
            }
            break;

            case 20: {
                double a = y * Math.atan2(y * params[3], x * params[12]);
                res = 10 * params[1] * a;
            }
            break;

            default: {
                res = x;
            }
        }
        return res;
    }
//////////////////////////////////////////////////    

    public double f(double x, double y) {
        Block_Prototype_Fxy g = (Block_Prototype_Fxy) sons[0];
        if (g == null) {
            return 0.5;
        }

        double cc = 1.0;

        double rrr = 0;//fff(x, y, 100);
        int nn = (int) (params[7] * 7);
        for (int j = 0; j < nn; j++) {
            //cc = cc * 2.0;
            double x1 = x;
            double y1 = y;
            // double h = ptrn1(x1, y1, cc, j);
            double h = g.f(x1, y1);
            scores[j] = h;
            //if (rrr>1) rrr = 1;    
            if (params[1] > 0.5) {
                x = x1+get_c(x1, y1, 7 + j) * (1.8 + params[7 + j]) + params[1 + j];
                y = y1+get_c(x1, y1, j) * (1.8 + params[15 + j]) + params[22 + j];
            } else {
                x = get_c(x1, y1, 7 + j) * (1.8 + params[7 + j]) + params[1 + j];
                y = get_c(x1, y1, j) * (1.8 + params[15 + j]) + params[22 + j];
            }

        }

        rrr = get_score(nn);

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
