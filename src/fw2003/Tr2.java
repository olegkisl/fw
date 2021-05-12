package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

/*
 *
 * Tr2  2 dimesional transformations
 *
 */
import java.io.*;
public class Tr2 implements Serializable {
    double h[][]= new double[4][4];

public    Tr2()
    { int k,l;
      for(k=0;k<3;k++)
      for(l=0;l<3;l++)
              h[k][l]=(double)0.0;
      for(k=0;k<3;k++)
              h[k][k]=(double)1.0;
    }

public    Tr2(double h1[][])
    { int k,l;
      for(k=0;k<3;k++)
      for(l=0;l<3;l++)
              h[k][l]=h1[k][l];
    }

public    Tr2(Tr2 u)
   { this(u.h);}


public    Tr2(double a1,double a2,double a3,
                                       double b1,double b2,double b3)
   { this();
    h[0][0]=a1;h[1][0]=a2;h[2][0]=a3;
        h[0][1]=b1;h[1][1]=b2;h[2][1]=b3;
   }

public    P2 point()
   {
           return new P2(h[2][0],h[2][1]);
   }


public static Tr2   move(double a, double b)
    { Tr2 t= new Tr2();
      t.h[2][0]=a;
      t.h[2][1]=b;
      return t;
    }

public static Tr2   shiftX(double a)
    { Tr2 t= new Tr2();
      t.h[0][1]=a;
      return t;
    }
public static Tr2   shiftY(double a)
    { Tr2 t= new Tr2();
      t.h[1][0]=a;
      return t;
    }
public static Tr2   scale(double a, double b)
    { Tr2 t= new Tr2();
      t.h[0][0]=a;
      t.h[1][1]=b;
      return t;
    }

public static Tr2 sovm(double x1, double y1, double x2, double y2,
                double x3, double y3, double x4, double y4
)
//r1-2  to r3-4
{Tr2  h2,h3,h;
 double r1,ax,ay,r2;
 ax=x2-x1;
 ay=y2-y1;
 r1=ax*ax+ay*ay;
 if(r1<0.0000000001)
    return Tr2.move(0.0,0.0);
 r2=Math.sqrt(r1);
 h2=Tr2.turn(0.0,0.0, ay/r2, ax/r2);
 h2=Tr2.inv(h2);

 ax=x4-x3;
 ay=y4-y3;
 r1=ax*ax+ay*ay;
 if(r1<0.0000000001)
    return Tr2.move(0.0,0.0);
 r2=Math.sqrt(r1);
 h3=Tr2.turn(0.0,0.0, ay/r2, ax/r2);


 h=Tr2.mult(Tr2.move(-x1,-y1),h2);
 h=Tr2.mult(h,h3);
 h=Tr2.mult(h,Tr2.move(x3,y3));
 return h;
}



public static  Tr2  turn(double x1, double y1, double  salfa, double calfa)
/*turn 1 - 2 */
  {Tr2 hr1,hr2,h;
   hr1=move(-x1,-y1);
   h= new Tr2();
   h.h[0][0]=calfa;
   h.h[1][1]=calfa;
   h.h[1][0]=-salfa;
   h.h[0][1]=salfa;
   h=mult(hr1,h);
   hr2=move(x1,y1);
   h=mult(h,hr2);
   return h;
   }

public static  Tr2  turn(double x1, double y1, double  alfa)
/*turn 1 - 2 */
  {double sa=Math.sin((alfa/180.0)*3.14159);
   double ca=Math.cos((alfa/180.0)*3.14159);
   return turn(x1,y1,sa,ca);
   }


public static Tr2  mult(Tr2 h2,Tr2 h3)
/* H1*S==H3*(H2*S) */
{
   Tr2 hr=new Tr2();
   int   k,l;
   for(k=0;k<3;k++)
   for(l=0;l<3;l++)
     hr.h[k][l]=h2.h[k][0]*h3.h[0][l]+h2.h[k][1]*h3.h[1][l]+
                  h2.h[k][2]*h3.h[2][l];
   return hr;

 }




public static Tr2 copy(Tr2 h)
  {
   Tr2 hr=new Tr2();
   int   k,l;
   for(k=0;k<3;k++)
   for(l=0;l<3;l++)
     hr.h[k][l]=h.h[k][l];
   return hr;
    }



private static double dop(double T[][],int K ,int L)
  {
     double TR[][]= new double[2][2],D1,D2,D3,DOP0;
     int M1,K1,L1,M,K2,L2;
     M=K+L    ;
     M1=M % 2;
     for(K1=0;K1<3;K1++)
     for(L1=0;L1<3;L1++)
           {if(K1==K) continue ;
            if(L1==L) continue ;
            K2=K1 ;
            L2=L1  ;
            if(K1>K)K2=K1-1 ;
            if(L1>L)L2=L1-1  ;
            TR[K2][L2]=T[K1][L1];
           }
     DOP0=TR[0][0]*TR[1][1]-TR[0][1]*TR[1][0] ;
     if(M1==1)DOP0=(-DOP0) ;
     return(DOP0);
  }


public static Tr2  inv(Tr2  H2)
 /* H1=H2**(-1)  */
    {
      double EMIN,HR[][] = new double[3][3],DET;
      int K,L;
      EMIN=(double)0.0000001 ;
      DET=dop(H2.h,0,0)*H2.h[0][0]+dop(H2.h,0,1)*H2.h[0][1]
                                            +dop(H2.h,0,2)*H2.h[0][2];
      if((DET>(-EMIN))&&(DET<EMIN)) {return new Tr2();}
      for(K=0;K<3;K++)
      for(L=0;L<3;L++)
         HR[K][L]=dop(H2.h,L,K)/DET;
      return new Tr2(HR);

    }


public void  noRotate()
    {
      double a=h[0][0];
      double b=h[0][1];
      double r=a*a+b*b;
      if(r>0.000001)r=Math.sqrt(r);
      h[0][0]=r;
      h[1][0]=0.0;
      h[0][1]=0.0;
      h[1][1]=-r;
    }

public void  noScale()
    {
      double a=h[0][0];
      double b=h[0][1];
      double r=a*a+b*b;
      if(r>0.0000001)
        r=Math.sqrt(r);
      else
        return;
      h[0][0]=a/r;
      h[0][1]=b/r;
      h[1][0]=h[1][0]/r;
      h[1][1]=h[1][1]/r;
    }

}
