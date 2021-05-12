package blocks;

import fw2003.*;

// Polynom
public class A1_FOURIER2_0 extends Block_Prototype_Fxy {

    static final long serialVersionUID = 1L;

    public A1_FOURIER2_0() {
        super("A1_FOURIER2_0", 0, 40);
    }
    double val = -0.5;
    double min = 0;
    int xmax = 5;
    int ymax = 5;

    public double f(double x, double y) {
        double s=0;
         double sff=0;
        double c=2.5+params[0];
        for(int i=1;i<xmax; i++)
            for(int j=1;j<ymax; j++){
              double ff=1.0/(i+j);
              ff=ff*(params[i]+params[j]);
              sff+=ff;
              s+= ff*Math.cos(params[i*ymax+j]+c*params[7+i*ymax+j]*(i*x+j*y))  ;
            }
   
        return 2.0* s/sff;
    }
}

