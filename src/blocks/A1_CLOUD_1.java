
package blocks;

/**
 *
 * @author okislyuk
 */

import fw2003.*;

public class A1_CLOUD_1   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  int max_fract= 12;
  double alfa=1.0;
 
  public A1_CLOUD_1() {
     super("A1_CLOUD_1", 1, 30);
     set_param();
  }
  
  int a1=46713,a2=21713,a3=71893, a4=31713,a5=12753, a6=5117;
  
  private void set_param() {
    a1 = (int)(10000*params[0]); 
    a2 = (int)(10000*params[2]);
    a3 = (int)(10000*params[3]);
    a4 = (int)(10000*params[4]);
    a5 = (int)(10000*params[5]);
    a6 = (int)(10000*params[6]);
  }
  public void mutate_this(){
    super.mutate_this();
    set_param();
  }
 
  private double fff(double x, double y, double size){
      int nx=0,ny=0;
     // x=x+10000;
     // y=y+10000;
      nx = (int)(x/(alfa*size));
      ny = (int)(y/(alfa*size));
      if (nx>0)
        nx = (a1*nx)%1024;
      else
        nx = (-a2*nx)%1024;
      if (ny>0)
        ny = (a3*ny)%1024;
       else
        ny = (-a4*ny)%1024;
      double r=(a5*nx+a6*ny+124)%1000;
      return r*0.001;
  }
  
  
   public double f(double x, double y){
   Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double x1 = g.f(x,y);
   x1=x+0.25*x1*params[8];
   double y1=y;
   double res=0.0;
   double cc=1.0;
   for(int j =0; j<max_fract; j++){
       cc=cc*(0.56);//(0.45+params[j+10]*0.25);
       res += fff( x1, y1, cc)/max_fract;
   }
   if(res>params[10]*0.9)
     return 1-res;
   return 1;
  }
 

}

