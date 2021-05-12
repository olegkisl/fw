package fw2003;

import java.util.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
public class FW_Rand {

    static int counter = 0;

    public FW_Rand() {
    }

    public static int getCounter() {
        counter++;
        return counter;
    }

    public static int rand(int nn) {
        int k;
        double d = Math.random();
        d = d * nn;
        k = (int) d;
        if (k < 0) {
            k = 0;
        }
        if (k >= nn) {
            k = nn - 1;
        }
        return k;
    }

    public static double rand01() {
        double d = Math.random();
        return d;
    }

    public static double getDouble() { //Dinamic random value may be detemingly randomize
        double d = Math.random();
        return d;
    }

    public static Object getRandObject(List s) {
        if (s == null) {
            return null;
        }
        int sz = s.size();
        if (sz == 0) {
            return null;
        }
        return s.get(rand(sz));
    }

    public static void main(String[] ssss) {
        int[] nn = new int[3];
        for (int i = 0; i < 10000; i++) {
            int ii = rand(3);
            nn[ii]++;
            //System.out.print(" "+rand(3));
        }
        System.out.print(" " + nn[0]);
        System.out.print(" " + nn[1]);
        System.out.print(" " + nn[2]);
    }
}
