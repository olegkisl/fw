/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fw2003;

import java.io.Serializable;

/**
 *
 * @author okislyuk
 */
public class PaintTransformation implements  Serializable {

    static final long serialVersionUID = 1L;
    public double m00, m10, m01, m11, m02, m12;
    public int maxX;
    public int maxY;

    public PaintTransformation(double m00, double m10, double m01, double m11, double m02, double m12, int maxX, int maxY) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        this.maxX = maxX;
        this.maxY = maxY;
    }


}
