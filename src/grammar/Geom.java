package grammar;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

import fw2003.*;
import java.util.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.*;


//////////////////////////////////////////////////////////////

public class  Geom implements  Serializable  //abstract base class for geom obgects
{
  static final long serialVersionUID =1L;
        double r=1.0,g=1.0,b=1.0; // Color


public Geom copy()
        {
        return this;
        }

public double dist(double xx, double yy)
    {
        return 0.0;
    }

public void act(Tr2 t)
        {
        }

public void setColor(double  r1,double g1,double b1)
        {
        r=r1;
        g=g1;
        b=b1;
        }

void mutateColor(double a)
{
        r=r+a*(Math.random()-0.5);
        if(r<0.0)r=0.0;
        if(r>1.0) r=1.0;
        g=g+a*(Math.random()-0.5);
        if(g<0.0)g=0.0;
        if(g>1.0) g=1.0;
        b=b+a*(Math.random()-0.5);
        if(b<0.0)b=0.0;
        if(b>1.0) b=1.0;
}

void modColorAbs(double dr,double dg,double db)
{
        double s, s1, r1,g1,b1;
        if(r>g) s=r; else s =g;
        if(b>s) s=b;
        if(s<=0.001) return;

        r1=dr;
        if(r1<0.0)r1=0.0;
        if(r1>1.0) r1=1.0;
        g1=dg;
        if(g1<0.0)g1=0.0;
        if(g1>1.0) g1=1.0;
        b1=db;
        if(b1<0.0)b1=0.0;
        if(b1>1.0) b1=1.0;
        r=r1;
        g=g1;
        b=b1;


      /*  if(r1>g1) s1=r1; else s1 =g1;
        if(b1>s1) s1=b1;

        if(s1<0.001) return;
        s=s/s1;
        r=r1*s;
        g=g1*s;
        b=b1*s;*/
}

void modColor(double dr,double dg,double db)
{
        double s=r+g+b, s1, r1,g1,b1;
        if(s<=0.001) return;

        r1=r+dr;
        if(r1<0.0)r1=0.0;
        if(r1>1.0) r1=1.0;
        g1=g+dg;
        if(g1<0.0)g1=0.0;
        if(g1>1.0) g1=1.0;
        b1=b+db;
        if(b1<0.0)b1=0.0;
        if(b1>1.0) b1=1.0;

        s1=r1+g1+b1;
        if(s1<0.001) return;
        s=s/s1;
        r=r1*s;
        g=g1*s;
        b=b1*s;
}


void mutateAddColor(double a){}

void mutateGeom(double a){}


protected void draw0(Tr2 t,FW_ImageContext c,int cr,int cg,int cb){}//Must be redefine by individual obj.

void draw1(Tr2 t,FW_ImageContext c,int cr,int cg,int cb)
{
    if(cr<0) cr=0;
        if(cr>255) cr=255;
        if(cg<0) cg=0;
        if(cg>255) cg=255;
        if(cb<0) cb=0;
        if(cb>255) cb=255;
        draw0(t,c,cr,cg,cb);
}

public void draw(Tr2 t,FW_ImageContext c)
        {
    int cr = (int)(255*r);
        int cg = (int)(255*g);
        int cb = (int)(255*b);

        draw1(t,c,cr,cg,cb);
        }

public void draw(Tr2 t,FW_ImageContext c,double brt)//draw with this britness
    {
    int cr = (int)(255*r*brt);
        int cg = (int)(255*g*brt);
        int cb = (int)(255*b*brt);
        draw1(t,c,cr,cg,cb);
    }

public void draw(Tr2 t,FW_ImageContext c,double r1,double g1,double b1)//draw with this color
    {
    int cr = (int)(255*r1);
        int cg = (int)(255*g1);
        int cb = (int)(255*b1);
        draw1(t,c,cr,cg,cb);
    }

public void drawAddColor(Tr2 t,FW_ImageContext c,double r1,double g1,double b1)//draw with add color
    {
    int cr = (int)(255*(r1+r)*0.5);
        int cg = (int)(255*(g1+g)*0.5);
        int cb = (int)(255*(b1+b)*0.5);
        draw1(t,c,cr,cg,cb);
    }

public void drawMultColor(Tr2 t,FW_ImageContext c,double r1,double g1,double b1)//draw with mult color
    {
    int cr = (int)(255*(r1*r));
        int cg = (int)(255*(g1*g));
        int cb = (int)(255*(b1*b));
        draw1(t,c,cr,cg,cb);
    }

}// end of class


//////////////////////////////////////////////////////////////




class  Pnt extends Geom
{
static final long serialVersionUID =1L;
        P2 p;

public Pnt(double x,double y,double  r1,double g1,double b1)
        {
        p=new	P2(x,y);
        r=r1;
        g=g1;
        b=b1;
        }


void mutateGeom(double a)
{
    p.x=p.x+a*(Math.random()-0.5);
        if(p.x<0.0)p.x=0.0;
        if(p.x>1.0)p.x=1.0;
        p.y=p.y+a*(Math.random()-0.5);
        if(p.y<0.0)p.y=0.0;
        if(p.y>1.0)p.y=1.0;
}

public double dist(double xx, double yy)
    {
        double a=p.x-xx, b=p.y-yy;
        return a*a+b*b;
    }

public Geom copy()
        {
        return new Pnt(p.x,p.y,r,g,b);
        }

public void act(Tr2 t)
        {
        p.act(t);
        }


public void draw0(Tr2 t,FW_ImageContext c,int cr,int cg,int cb)
        {
        Graphics2D g2 = c.getGraphics2D();

        P2 pp = p.actNew(t);
        double nx=pp.x;
        double ny=pp.y;

        g2.setColor(new Color(cr,cg,cb));
        c.draw(new Line2D.Double(nx, ny, nx+0.001, ny));

        //c.setColor(new Color(cr,cg,cb));
        //c.drawLine(nx,ny,nx+1,ny);

        }

}// end of class


//////////////////////////////////////////////////////////////

class  Poly extends Geom
{
        static final long serialVersionUID =1L;

        P2[] vert = new P2[5];
        int l=0;

public Poly(double  r1,double g1,double b1)
        {
        r=r1;
        g=g1;
        b=b1;
        }

        void mutateGeom(double a)
    {
     P2 p;
     for(int i=0; i<l; i++)
       {
        p=vert[i];
        p.x=p.x+a*(Math.random()-0.5);
            if(p.x<0.0)p.x=0.0;
            if(p.x>1.0)p.x=1.0;
            p.y=p.y+a*(Math.random()-0.5);
            if(p.y<0.0)p.y=0.0;
            if(p.y>1.0)p.y=1.0;
           }
        }



        public double dist(double xx, double yy)
    {
        P2 pp;
        double a1, b1;
        double d1=0.0;
        double d2=100000.0;
        for(int i=0; i<l; i++)
            {
                pp = vert[i];
                a1=xx-pp.x;
                b1=yy-pp.y;
                d1=a1*a1+b1*b1;
                if(d1<d2)
                  d2=d1;
            }
      return d2;
    }

public  void addPnt(P2 p)
        {int lmax= vert.length;
         if((l+1)>=lmax)
         {
                P2[] t= new P2[lmax+5];
                for(int i=0; i<l; i++)
                  t[i]=vert[i];
                vert=t;
         }
         vert[l]=p;
         l++;
        }

public  void addPnt(double x, double y)
        {
        addPnt(new P2(x,y));
        }


public Geom copy()
        {Poly pp= new Poly(r,g,b);
         for(int i=0; i<l; i++)
                pp.addPnt(vert[i]);
         return pp;
        }

public void act(Tr2 t)
        {
        for(int i=0; i<l; i++)
                vert[i].act(t);
        }



protected void draw0(Tr2 t,FW_ImageContext c,int cr, int cg,int cb)
        {
          Graphics2D g2 = c.getGraphics2D();
          GeneralPath filledPolygon = new
          GeneralPath(GeneralPath.WIND_EVEN_ODD,
                       l);

        float nx1,ny1;
        P2 pp;
        pp = vert[0].actNew(t);
        nx1=(float)pp.x;
        ny1=(float)pp.y;
        filledPolygon.moveTo(nx1, ny1);

        for(int i=0; i<l; i++)
        {
                pp = vert[i].actNew(t);
                nx1=(float)pp.x;
                ny1=(float)pp.y;
                filledPolygon.lineTo(nx1,ny1);
        }
        filledPolygon.closePath();
        g2.setPaint(new Color(cr,cg,cb));
        c.fill(filledPolygon);
    //    c.setColor(new Color(cr,cg,cb));
    //    c.fillPolygon(pg);
        }



}// end of class


//////////////////////////////////////////////////////////////

class  Lin extends Geom
{
static final long serialVersionUID =1L;
        P2 p1,p2;

public Lin(double x1,double y1,double x2,double y2,
                   double  r1,double g1,double b1)
        {
        p1=new	P2(x1,y1);
        p2=new	P2(x2,y2);
        r=r1;
        g=g1;
        b=b1;
        }

        void mutateGeom(double a)
    {
    p1.x=p1.x+a*(Math.random()-0.5);
        if(p1.x<0.0)p1.x=0.0;
        if(p1.x>1.0)p1.x=1.0;
        p1.y=p1.y+a*(Math.random()-0.5);
        if(p1.y<0.0)p1.y=0.0;
        if(p1.y>1.0)p1.y=1.0;

        p2.x=p2.x+a*(Math.random()-0.5);
        if(p2.x<0.0)p2.x=0.0;
        if(p2.x>1.0)p2.x=1.0;
        p2.y=p2.y+a*(Math.random()-0.5);
        if(p2.y<0.0)p2.y=0.0;
        if(p2.y>1.0)p2.y=1.0;
    }


        void mutateGeom1(double a)
    {
    double d;
    d=(p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y);
    if(d>0) d=Math.sqrt(d);
        p2.x=p2.x+d*a*(Math.random()-0.5);
        if(p2.x<0.0)p2.x=0.0;
        if(p2.x>1.0)p2.x=1.0;
        p2.y=p2.y+d*a*(Math.random()-0.5);
        if(p2.y<0.0)p2.y=0.0;
        if(p2.y>1.0)p2.y=1.0;
    }

        void mutateGeom2(double a)
    {
    double d;
    d=(p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y);
    if(d>0) d=Math.sqrt(d) ;
    p1.x=p1.x+d*a*(Math.random()-0.5);
        if(p1.x<0.0)p1.x=0.0;
        if(p1.x>1.0)p1.x=1.0;
        p1.y=p1.y+d*a*(Math.random()-0.5);
        if(p1.y<0.0)p1.y=0.0;
        if(p1.y>1.0)p1.y=1.0;

        p2.x=p2.x+d*a*(Math.random()-0.5);
        if(p2.x<0.0)p2.x=0.0;
        if(p2.x>1.0)p2.x=1.0;
        p2.y=p2.y+d*a*(Math.random()-0.5);
        if(p2.y<0.0)p2.y=0.0;
        if(p2.y>1.0)p2.y=1.0;
    }






        public double dist(double xx, double yy)
    {
        double a1=p1.x-xx, b1=p1.y-yy;
        double a2=p2.x-xx, b2=p2.y-yy;
        double d1=a1*a1+b1*b1;
        double d2=a2*a2+b2*b2;
        if(d1>d2)
         return d2;
        else
         return d1;
    }

public Geom copy()
        {
        return new Lin(p1.x, p1.y, p2.x, p2.y,  r,g,b);
        }

public void act(Tr2 t)
        {
        p1.act(t);
        p2.act(t);
        }


protected void draw0(Tr2 t,FW_ImageContext c,int cr, int cg,int cb)
        {
        Graphics2D g2 = c.getGraphics2D();

        P2 pp = p1.actNew(t);
        double nx1=pp.x;
        double ny1=pp.y;

        pp = p2.actNew(t);
        double nx2=pp.x;
        double ny2=pp.y;

        g2.setColor(new Color(cr,cg,cb));
        c.draw(new Line2D.Double(nx1, ny1,nx2, ny2));

       // c.drawLine(nx1,ny1,nx2,ny2);
       // System.out.println("Line:"+nx1+" "+ny1+" "+nx2+" "+ny2);

        }
/*
protected void drawL(Tr2 t,Graphics c,String s)
        {


        P2 pp = p1.actNew(t);
        int nx1=(int)pp.x;
        int ny1=(int)pp.y;

        pp = p2.actNew(t);
        int nx2=(int)pp.x;
        int ny2=(int)pp.y;

        c.setXORMode(Color.black);
        c.setColor(Color.white);
        c.drawLine(nx1,ny1,nx2,ny2);
        c.setColor(Color.white);
        c.drawRect(nx1-2,ny1-2,4,4);
        c.setColor(Color.white);
        c.drawString(s, nx1+6, ny1+6);
        c.setPaintMode();
        }


protected void drawL(Tr2 t,Graphics c)
        {


        P2 pp = p1.actNew(t);
        int nx1=(int)pp.x;
        int ny1=(int)pp.y;

        pp = p2.actNew(t);
        int nx2=(int)pp.x;
        int ny2=(int)pp.y;

        c.setXORMode(Color.black);
        c.setColor(Color.yellow);
        c.drawLine(nx1,ny1,nx2,ny2);
        c.setColor(Color.yellow);
        c.drawRect(nx1-4,ny1-4,8,8);
        c.setPaintMode();
        }
*/

}// end of class

//////////////////////////////////////////////////////////////



class  GeomObj extends Geom
{ static final long serialVersionUID =1L;
        Vector v=new Vector();



public GeomObj (double  r1,double g1,double b1)
        {
        r=r1;
        g=g1;
        b=b1;
        }

void mutateColor(double a)
{
        Geom m=null;
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        m=(Geom)(e.nextElement());
        m.mutateColor(a);
                }

}




void mutateAddColor(double a)
{
    double dr, dg, db,s;
    dr=a*(Math.random()-0.5);
        dg=a*(Math.random()-0.5);
        db=a*(Math.random()-0.5);
        s=dr+db+dg;
        if((s>0.01)||(s<0.01))
            {
            dr=dr/s;
            dg=dg/s;
            db=db/s;
        }

        Geom m=null;
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        m=(Geom)(e.nextElement());
        m.modColor( dr, dg, db);
                }

}

void mutateColorAbs(double dr,double dg, double db)
{


        Geom m=null;
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        m=(Geom)(e.nextElement());
        m.modColorAbs( dr, dg, db);
                }

}

void mutateGeom(double a)
    {
    Geom m=null;
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        m=(Geom)(e.nextElement());
        m.mutateGeom(a);
                }
        }

public void addElement(Geom e)
        { v.addElement(e); }

public void delLastlement()
        {
            int i=v.size();
        if(i>0)
            v.removeElementAt(i-1);
        }

public void delElement(double xx, double yy)
        {
        Geom m=null, m1=null;;
        double d=1000000;

        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        m=(Geom)(e.nextElement());
        if(d>m.dist(xx,yy))
          {
            m1=m;
            d=m.dist(xx,yy);
          }
                }
        if(m1!=null)
            v.removeElement(m1);
        }

public Geom copy()
        {
        GeomObj g1= new GeomObj(r,g,b);
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        g1.addElement(((Geom)(e.nextElement())).copy());
                }
        return g1;
        }

public void act(Tr2 t)
        {
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        ((Geom)(e.nextElement())).act(t);
                }
        }



public void draw(Tr2 t,FW_ImageContext c)
        {
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        ((Geom)(e.nextElement())).draw(t,c);
                }
        }

public void draw(Tr2 t,FW_ImageContext c,double r1,double g1,double b1)
        {
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        ((Geom)(e.nextElement())).draw(t,c,r1,g1,b1);
                }
        }


public void drawAddColor(Tr2 t,FW_ImageContext c,double r1,double g1,double b1)
        {
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        ((Geom)(e.nextElement())).drawAddColor(t,c,r1,g1,b1);
                }
        }


public void drawMultColor(Tr2 t,FW_ImageContext c,double r1,double g1,double b1)
        {
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        ((Geom)(e.nextElement())).drawMultColor(t,c,r1,g1,b1);
                }
        }


public void draw(Tr2 t,FW_ImageContext c, double brt)
        {
        for (Enumeration  e  =  v.elements()  ;  e.hasMoreElements()  ;)
                {
        ((Geom)(e.nextElement())).draw(t,c,brt);
                }
        }

}// end of class



