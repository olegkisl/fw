package blocks;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;


public class f_PerlinD   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  public f_PerlinD() {
     super("f_PerlinD", 2, 8);
  }

int nn =0;
public void mutateLarge_this() {
 nn = FW_Rand.rand(5);
}


  public double f(double x, double y){
    Block_Prototype_Fxy g =(Block_Prototype_Fxy)sons[0];
   if(g == null) return 0.5;
   double t = g.f(x,y);
   Block_Prototype_Fxy gg =(Block_Prototype_Fxy)sons[1];
   if(gg == null) return 0.9;
  // double tt = gg.f(x,y);

   return  gg.f(perlinNoise_2D(x, y),t);
 }
////////////////////////////////////utils


   private double  noise(int x, int y){
     int n = x + y *57;
     n = (n<<13) ^ n;
     return ( 1.0 - ( (n * (n * n * 15731 + 789221) + 1376312589) & 0X7fffffff) / 1073741824.0);
   }

    private double   smoothedNoise(int  x, int  y){
    //  double t =noise(x,y);
     // System.out.println(t);
      return noise(x,y);
   // double corners = ( noise(x-1, y-1)+noise(x+1, y-1)+noise(x-1, y+1)+noise(x+1, y+1) ) / 16.0;
   // double sides   = ( noise(x-1, y)  +noise(x+1, y)  +noise(x, y-1)  +noise(x, y+1) ) /  8.0;
   // double center  =  noise(x, y) / 4.0;
   // return corners + sides + center;
    }

    private double   interpolate(double a, double  b, double  x){
   //  double     ft = x * 3.1415927;
  //   double     f = (1 - Math.cos(ft)) * 0.5;
     double xx=x*x;
     double f = 6*xx*xx*x-15*xx*xx+10*xx*x;
     return  a*(1-f) + b*f;
    }

    private double   interpolatedNoise(double  x, double  y){

     int integer_X    =(int)Math.round(Math.floor(x));
     double fractional_X = x - integer_X;

     int integer_Y    = (int)Math.round(Math.floor(y));
     double  fractional_Y = y - integer_Y;

     double  v1 = smoothedNoise(integer_X,     integer_Y);
     double  v2 = smoothedNoise(integer_X + 1, integer_Y);
     double  v3 = smoothedNoise(integer_X,     integer_Y + 1);
     double  v4 = smoothedNoise(integer_X + 1, integer_Y + 1);

     double  i1 = interpolate(v1 , v2 , fractional_X);
     double  i2 = interpolate(v3 , v4 , fractional_X);

      return interpolate(i1 , i2 , fractional_Y);
    }

    private double perlinNoise_2D(double x, double y){

      double total = 0;//params[1];
      double p = params[0]; //persistence;
      int n = nn; //Number_Of_Octaves - 1

      double frequency = 1.0;
      double amplitude = 1.0;

      for (int i = 0; i <= n; i++) {
          total = total + interpolatedNoise(x * frequency, y * frequency) * amplitude;
          frequency = 2.0*frequency;
          amplitude = p*amplitude;
      }

      return 0.5*( 1+total);
}


}
