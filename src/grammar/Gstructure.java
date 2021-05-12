package grammar;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

//import java.awt.*;
//import java.awt.image.*;
import fw2003.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;

public class Gstructure implements  Serializable
{
 static final long serialVersionUID =1L;
 String name="";
 Vector       alfabet = new Vector();
 Vector       rules = new Vector();
 Hashtable    consts = new Hashtable();
 InitRuleName rname = new InitRuleName();

 double mutLabel1 = 0.3;// Label Geom mutation during Label copy
 double mutLabel2 = 1.0;// Label Geom mutation: end of label only
 double mutLabel3 = 1.0;// Label Geom mutation : both points
 double mutLabelAtr = 0.3;// Label Atributes mutation

 double mutImageColor = 0.2;// Image Color mutation
 double mutImageGeom = 0.8;// Image Geometry mutation


public Gstructure(String nm)
{
    name=nm;
}

///////////////////////////
//Mutations staff
//////////////////////////

  // public void mutateColor_this(){;}



  public void mutatePallet(FW_PalletInterface p)
{
 Color cw;
 Rule r;
 double r0; double g0; double b0;
 for(Enumeration  e  = rules.elements()  ;  e.hasMoreElements()  ;)
  {
   r=(Rule)e.nextElement();
   if(r.geom==null)
     continue;
   cw =p.getColor();
   r0 = cw.getRed()/255.0;
   g0 = cw.getGreen()/255.0;
   b0 = cw.getBlue()/255.0;
   r.geom.mutateColorAbs(r0, g0, b0);
}
}


//////////////////////////
//End of mutation staff
/////////////////////////




public void dummy()
{
    rname.add("A","Empty G-grammar");
}

void step(FW_ImageContext br, FW_BlockInterface stk, Active ac, int depth)//Step of drawing and rule working
{
   if(br.getGraphics2D()!=null)
   {
    Rule r=getRule(ac); // Get rule with ac.name left symbol
    if(r==null)
        return;
    r.step(br,stk,ac, depth);  // Draw the r Rule image and add new Active elements to stk
   }
}

 void mutate()
 {
      Const r=null;
      for(Enumeration  e  = consts.elements()  ;  e.hasMoreElements()  ;)
         {
             r=(Const)e.nextElement();
             r.mutate();
         }
 }


 void mutate_back()
{
     Const r=null;
     for(Enumeration  e  = consts.elements()  ;  e.hasMoreElements()  ;)
        {
            r=(Const)e.nextElement();
            r.mutate_back();
        }
}


void setDefault()
{
  //   Choice cc=null;
     Const r=null;
 //    Panel p=null;
     for(Enumeration  e  = consts.elements()  ;  e.hasMoreElements()  ;)
        {
            r=(Const)e.nextElement();
            r.setDefault();
  //          p=r.getPanel();
  //          if(p!=null)
  //            p.invalidate();
        }
    if(rname!=null)
       {
        rname.setDefault();
  //      cc=rname.getChoice();
  //      if(cc!=null)
  //         cc.invalidate();
       }
}


//
//   Choose rule with ac.name (more than one may exist)
//
private int maxRules=200,lRules=0;//Maximum number of rules with the same left symbol
private Rule[] masRules=new Rule[maxRules];
private double[]  masWeight=new double[maxRules];
Rule getRule(Active ac) //Choose rule with name=ac.name acording to condition and weight
{
    if(ac.name==null)
        return null;
    String s=ac.name;
    Rule r;
    lRules=0;
    double wt=0.0,prob=0.0;
    double wtSum=0.0;
    for(Enumeration  e  = rules.elements()  ;  e.hasMoreElements()  ;)
    {
        r=(Rule)e.nextElement();
        wt=r.getWght(ac);// Calculate weight of the rule (if IF condition=false -> wt=-1)
        if(s.equals(r.name))
            if(wt>0.0)
                if(lRules<maxRules)
                 {
                    masRules[lRules]=r;
                    masWeight[lRules]=wt;
                    wtSum=wtSum+wt;
                    lRules++;
                 }
    }
    if((lRules==0)||(wtSum<0.00000001))
       return null;
    prob=Math.random();//RAND Now  This is the only random place during G-grammar growth
    wt=0.0;

    for(int i=0;i<lRules;i++)
      {
        wt=wt+masWeight[i]/wtSum;
        if(wt>prob)
            {
           // System.out.println("NNRule---:"+lRules+"#"+i);///////
            return(masRules[i]);
            }
      }
    return masRules[0];
}

String getInitName()
{
    if(rname==null)
        return null;
    else
        return rname.getValue();
}


 double findConst(String s)//Define value of the constant
 {Const c;
  Object a;
    a=consts.get(s);
    if(a==null)
        return 1.0;
    else
        return ((Const)a).getValue();
 }


 void setConstValues()//Set new values for all constants in rules
 {Rule r;
    for(Enumeration  e  = rules.elements()  ;  e.hasMoreElements()  ;)  {
        r=(Rule)e.nextElement();
        r.findVal(this);
}


 }

 void setHValues()// Set values for all transformation
 {Rule r;
  GLabel gg;
     for(Enumeration  e  = rules.elements()  ;  e.hasMoreElements()  ;)
    {
        r=(Rule)e.nextElement();
        if(r.lb0!=null)
          {
            r.lb0.setH();
            if(r.lbs!=null)
            for(Enumeration  e1  = r.lbs.elements()  ;  e1.hasMoreElements()  ;)
            {
                gg=(GLabel)e1.nextElement();
                if(r.lb0.lb0!=null)
                    gg.setH(r.lb0.lb0);
            }
          }
    }
 }







// void initDraw()
//  {
// }

// boolean  stepDraw(Tr2 t, Graphics g)
 //  {
//    return false;
//   }
}
/////////////////////////////////////////
class RuleNameItem implements  Serializable
{
 static final long serialVersionUID =1L;
 String name=null, com=null;
    RuleNameItem(String nm, String c)
    {
        name=nm;
        com=c;
    }
}

class InitRuleName implements  Serializable
{
    static final long serialVersionUID =1L;
    int nnSelected = 0;
    Vector names  = new Vector();
//    Choice coments = new Choice();

    void add(String nm,String com)
      {
       if(nm==null)
         {com="Bad Choice"; nm="Bad";}
       if(com==null)
         com="Choice";
       names.addElement(new RuleNameItem(nm,com));
 //      coments.addItem(com);
 //     coments.select(0);
      }

    void setDefault() { nnSelected = 0;;  }

   //  Choice getChoice()
   //    { return coments;}

     String getValue()
        {
        if(names.size()<=nnSelected)
          return null;
        return ((RuleNameItem) names.elementAt(nnSelected)).name;
        }
}
///////////////////////////////////////////

class Const implements  Serializable
{
   static final long serialVersionUID =1L;
   static final int rng=100;//range for Scrooll Bar
   String    name=null;
   String    coment=null;
   double    max=1.0,min=0.0,valDefault=0.0;
   double    val=0.0;
 //  Panel     p=null;
///   Scrollbar ranger=null;

   Const(String nm,String com, double rmin,double rmax,double rdefault,double rcurrent)
   {
    name=nm;
    coment=com;
    max=rmax;
    min=rmin;
    valDefault=rdefault;
    if(max<=min)
        max=min+1.0;
    if(valDefault>max)
        valDefault=max;
    if(valDefault<min)
        valDefault=min;
    val=rcurrent;
    if(val>max)
        val=max;
    if(val<min)
        val=min;

//    p = new Panel();
//        p.setLayout(new GridLayout(1,2));
//        p.add(new Label(coment,Label.CENTER));
//        ranger = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, rng);
//        ranger.setValue((int)(rng*(val-min)/(max-min)));
 //   p.add(ranger);
   }

   void setDefault()
   {
    val=valDefault;
//    ranger.setValue((int)(rng*(val-min)/(max-min)));
   }

   void mutate()
  { double d =(max-min)*0.1;
     if (d>10.0) d=10.0;
     val=val+ d*(FW_Rand.rand01()-0.5);
     if(val>max)
        val=max;
     if(val<min)
        val=min;

//    ranger.setValue((int)(rng*(val-min)/(max-min)));
  }

  void mutate_back()
  {
    if(FW_Rand.rand01()>0.667) return;
     val=(val+valDefault)*0.5;
     if(val>max)
        val=max;
     if(val<min)
        val=min;
//    ranger.setValue((int)(rng*(val-min)/(max-min)));
  }




//   Panel getPanel() { return p;}

   double getValue()
   {
//    if(ranger!=null)
//      {
 //       val=ranger.getValue();
 //       val=val/(double)rng;
 //       val=min+val*(max-min);
  //    }
    return val;
   }
}

////////////////////////////////////////////////////////////////////
class Rule implements  Serializable
{
   static final long serialVersionUID =1L;
    String name=null;
    String comment=null;

    RealVal6 cond = null;//Condition of use
    RealVal4 weight = null;

    RealVal4 scale = null;
    RealVal4 angle = null;

    RealVal4 bright = null;//Color modifier
    RealVal4 rMod = null;//Color modifiers
    RealVal4 gMod = null;
    RealVal4 bMod = null;

    boolean absAngle=false;
    boolean absScale=false;
    int     absColor=1;// 1- absolute; 2 C=(R+C)*0.5;; 3 C=R*C

    boolean mutate=false;// Permition to mutate

    boolean randXX=false; //Set random value to x,y,z before execution
    boolean randYY=false;
    boolean randZZ=false;

    GeomObj geom=new GeomObj(1.0,1.0,1.0);

    GLabel0    lb0=new GLabel0(0.45,0.55, 0.45,0.45); // in label

    Vector  lbs= new Vector(); //out labels

///////////////////////////
//Mutations staff
//////////////////////////

//////////////////////




void clear()
{
   geom=new GeomObj(1.0,1.0,1.0);
   lbs.removeAllElements();
}

void clearLastLbl()
{
   int i=lbs.size();
   if(i>0)
    lbs.removeElementAt(i-1);
}

void clearLastObj()
{
  geom.delLastlement();
}


Rule copy()
{
    Rule r1 = new Rule(name);
    r1.comment=comment;
    if(cond!=null)
        r1.cond=cond.copy();
    if(weight!=null)
        r1.weight=weight.copy();

    if(scale!=null)
        r1.scale=scale.copy();
    if(angle!=null)
        r1.angle=angle.copy();

    if(bright!=null)
        r1.bright=bright.copy();
    if(rMod!=null)
        r1.rMod=rMod.copy();
    if(gMod!=null)
        r1.gMod=gMod.copy();
    if(bMod!=null)
        r1.bMod=bMod.copy();

    r1.absAngle = absAngle;
    r1.absScale=absScale;
    r1.absColor=absColor;

    r1.randXX=randXX;
    r1.randYY=randYY;
    r1.randZZ=randZZ;

    r1.mutate=mutate; // Permition to mutate

    if(geom!=null)
        r1.geom=(GeomObj)geom.copy();
    if(lb0!=null)
        r1.lb0=lb0.copy();

   for(Enumeration  e1  = lbs.elements()  ;  e1.hasMoreElements()  ;)
     {
            r1.lbs.addElement(((GLabel)e1.nextElement()).copy());
     }
   return r1;
}

Rule(String nm)
{
    name=nm;
}

void step(FW_ImageContext br, FW_BlockInterface stk, Active ac, int depth)//Step of drawing and rule working
{
   GLabel gg=null;
   if((lb0==null)||(ac==null))
        return;
   Tr2 h0=ac.h;
   Tr2 h1=lb0.h;
   Tr2 h3=null,h4=null,h5=null;
   if((h0==null)||(h1==null))
        return;

   if(randXX)
        ac.x=Math.random()*2.0-1.0;
   if(randYY)
        ac.y=Math.random()*2.0-1.0;
   if(randZZ)
        ac.z=Math.random()*2.0-1.0;

   if(absAngle)
        h0.noRotate();

   if(absScale)
        h0.noScale();

   if(scale!=null)//Scale as f(x,y,z);
   {
        double c1=scale.calc(ac.x,ac.y,ac.z);
        Tr2 tu=Tr2.scale(c1,c1);
        h1=Tr2.mult(h1,tu);
    }

    if(angle!=null)// Rotate as f(x,y,z);
    {
        double c1=angle.calc(ac.x,ac.y,ac.z);
        Tr2 tu=Tr2.turn(0.0,0.0,c1);
        h1=Tr2.mult(h1,tu);
       // System.out.println("ZZ="+ac.z+"/"+c1);
    }


   // Set variable changes of H
   Tr2 h2=Tr2.mult(h1,h0);

   if(geom!=null)
   {
  //   System.out.println("draw- ");
   if((rMod!=null)&&(bright!=null)){ //Semitransparent
     double r1=rMod.calc(ac.x,ac.y,ac.z);
     double br1=bright.calc(ac.x,ac.y,ac.z);
     if(r1>=1)
      geom.draw(h2,br,br1);
     else{
       if(r1<0.0) r1=0.0;
       AlphaComposite acs = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)r1);
       AlphaComposite acsB = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
       br.getGraphics2D().setComposite(acs);
       geom.draw(h2,br,br1);
       br.getGraphics2D().setComposite(acsB);
     }
   }
   else if((rMod!=null)&&(gMod!=null)&&(bMod!=null))
        {
        double r1=rMod.calc(ac.x,ac.y,ac.z);
        double g1=gMod.calc(ac.x,ac.y,ac.z);
        double b1=bMod.calc(ac.x,ac.y,ac.z);
        if(absColor==2)
            geom.drawAddColor(h2,br,r1,g1,b1);
        else if(absColor==3)
            geom.drawMultColor(h2,br,r1,g1,b1);
        else
            geom.draw(h2,br,r1,g1,b1);
        }
   else if(bright!=null)
        {
        double br1=bright.calc(ac.x,ac.y,ac.z);
        geom.draw(h2,br,br1);
        }
   else
        geom.draw(h2,br);
   }


  if(lbs!=null)
     for(Enumeration  e1  = lbs.elements()  ;  e1.hasMoreElements()  ;)
          {
             gg=(GLabel)e1.nextElement();
             if(gg.h!=null)
                {

                  /*OLD VARIANT
                  h4= Tr2.inv(gg.h_old);
                  h3= Tr2.mult(h4,h2);
                  h4= Tr2.inv(h1);
                  h3= Tr2.mult(h4,h3);
                  */

                  /* NEW VARIANT  Hcur = U2*H02*HGR = U2*H02*H10*U1*Hcur*/
                  h4= gg.h;//
                  h3= Tr2.mult(h4,h2);//
                  /**/
                  gg.step(h3, stk, ac, br, depth);
                }
          }


}

double getWght(Active ac)//Get weight of this rule (-1 means rule is forbiden)
{                        // and check IF condition
    if(cond==null)
     {
      if(weight==null)
        return(1.0);
      else
        return weight.calc(ac.x,ac.y,ac.z);
     }

    if(cond.calc(ac.x,ac.y,ac.z))
     {
       if(weight==null)
        return(1.0);
      else
        return weight.calc(ac.x,ac.y,ac.z);
     }

    return -1.0;
}


public void findVal(Gstructure  g)
{
   if(cond!=null)  cond.findVal(g);
   if(weight!=null)  weight.findVal(g);
   if(scale!=null)  scale.findVal(g);

   if(angle!=null)  angle.findVal(g);
   if(bright!=null)  bright.findVal(g);

   if(rMod!=null)  rMod.findVal(g);
   if(gMod!=null)  gMod.findVal(g);
   if(bMod!=null)  bMod.findVal(g);
   Object a;
   for(Enumeration  e  =  lbs.elements()  ; e.hasMoreElements()  ;)
    {
        a=e.nextElement();
        if(a!=null)
          ((GLabel)a).findVal(g);
    }
}
}


class GLabel implements  Serializable
{
  static final long serialVersionUID =1L;
  final static double SCALE_MIN=0.1;
  final static double SCALE_MAX=10.0;

  String name=null;
  Tr2 h=null;  // Convetrt to lb0
  Tr2 h_old=null;  // Convetrt to lb0
  Tr2 ha=null; // move+scale+rot+shift
  Lin l= new Lin(0.2,0.2,0.3,0.3,   1.0,1.0,0.0);
  RealVal moveX=null, moveY=null;
  RealVal scaleX=null, scaleY=null;
  RealVal shiftX=null, shiftY=null;
  RealVal rotate=null;

  boolean mirror=false;

  RealVal4 modXX=null;
  RealVal4 modYY=null;
  RealVal4 modZZ=null;

GLabel copy()
{
    GLabel g= new GLabel(name);
    if(l!=null)
        g.l=(Lin)l.copy();
    if( moveX!=null)
        g.moveX=moveX.copy();
    if( moveY!=null)
        g.moveY=moveY.copy();
    if( scaleX!=null)
        g.scaleX=scaleX.copy();
    if( scaleY!=null)
        g.scaleY=scaleY.copy();
    if( shiftX!=null)
        g.shiftX=shiftX.copy();
    if( shiftY!=null)
        g.shiftY=shiftY.copy();
    if( rotate!=null)
        g.rotate=rotate.copy();

    g.mirror=mirror;

    if( modXX!=null)
        g.modXX=modXX.copy();
    if( modYY!=null)
        g.modYY=modYY.copy();
    if( modZZ!=null)
        g.modZZ=modZZ.copy();

    return g;
}

GLabel(String s)
 {name=s;}

GLabel(String s, Lin ll)
 {
    name=s;
    l=ll;
 }

void step(Tr2 h, FW_BlockInterface stk, Active ac, FW_ImageContext ct, int depth) //Step of drawing and rule working
{
    double xx,yy,zz;
    if(modXX!=null)
        xx=modXX.calc(ac.x,ac.y,ac.z);
    else
        xx=ac.x;

    if(modYY!=null)
        yy=modYY.calc(ac.x,ac.y,ac.z);
    else
        yy=ac.y;

    if(modZZ!=null)
        zz=modZZ.calc(ac.x,ac.y,ac.z);
    else
        zz=ac.z;
    if(name!=null){
      //System.out.println("call: "+stk.getName()+":  "+ac.name+"["+name+"]");
      Active acc;
      int nn = FW_Utils.getDigitName(name);
      if(nn<0){
       acc = new Active( name, h, xx,yy,zz);
       ct.setApplicationContext(acc);
     //  System.out.println("  ------- "+name+" "+depth+ " "+xx);
       stk.paint(ct, depth-1);
      }
      else{
       acc = new Active( "", h, xx,yy,zz);
       ct.setApplicationContext(acc);
       if(nn>=stk.getNumberOfSons()) return;
       FW_BlockInterface s = stk.getSon(nn);
       if(s==null) return;
    //   System.out.println("       >>>> "+name+" "+depth+ " "+xx+ " "+ s.getName());
       s.paint(ct, depth-1);
      }
      //stk.addElement(name, h, xx, yy, zz); //@@@
    }
}

 void setH(Lin lb0)
   {
    if((lb0==null)||(l==null))
       h=Tr2.move(0.0,0.0);
    else
       {
        double d;
        Tr2 hh0=null;

  ///////////////////////////////////////////////////// changes
       Tr2 hh1_old=Tr2.sovm(
                  l.p1.x,l.p1.y,  l.p2.x,l.p2.y,
                  0.0,0.0,  0.0,1.0
                  );
        Tr2 hh2_old=Tr2.sovm(
                  0.0,0.0,  0.0,1.0,
                  lb0.p1.x,lb0.p1.y,  lb0.p2.x,lb0.p2.y
                  );
       Tr2 hh1= new Tr2();
       Tr2 hh2=Tr2.sovm(
            0.0,0.0,  0.0,1.0,
            l.p1.x,l.p1.y,  l.p2.x,l.p2.y
            );
  //////////////////////////////////////////////////////



        if(mirror)
        {
            hh0=Tr2.scale(-1.0,1.0);
            hh1=Tr2.mult(hh1,hh0);
        }

        if(scaleX!=null)
          {
            d=scaleX.getVal();
            if(d>=0)
                {
                    if(d>SCALE_MAX)  d=SCALE_MAX;
                    if(d<SCALE_MIN)  d=SCALE_MIN;
                }
            else
                {
                    if(d<(-SCALE_MAX))  d=-SCALE_MAX;
                    if(d>(-SCALE_MIN))  d=-SCALE_MIN;
                }
            d=1.0/d;
            hh0=Tr2.scale(d,1.0);
            hh1=Tr2.mult(hh1,hh0);
          }

        if(scaleY!=null)
          {
            d=scaleY.getVal();
            if(d>=0)
                {
                    if(d>SCALE_MAX)  d=SCALE_MAX;
                    if(d<SCALE_MIN)  d=SCALE_MIN;
                }
            else
                {
                    if(d<(-SCALE_MAX))  d=-SCALE_MAX;
                    if(d>(-SCALE_MIN))  d=-SCALE_MIN;
                }
            d=1.0/d;
            hh0=Tr2.scale(1.0, d);
            hh1=Tr2.mult(hh1,hh0);
          }

        if(shiftX!=null)
          {
            d=shiftX.getVal();
            hh0=Tr2.shiftX(d);
            hh1=Tr2.mult(hh1,hh0);
          }

        if(shiftY!=null)
          {
            d=shiftY.getVal();
            hh0=Tr2.shiftY(d);
            hh1=Tr2.mult(hh1,hh0);
          }

        if(rotate!=null)
          {
            d=rotate.getVal();
            hh0=Tr2.turn(0.0,0.0,d);
            hh1=Tr2.mult(hh1,hh0);
          }

         if(moveX!=null)
          {
            d=moveX.getVal();
            hh0=Tr2.move(d, 0.0);
            hh1=Tr2.mult(hh1,hh0);
          }

        if(moveY!=null)
          {
            d=moveY.getVal();
            hh0=Tr2.move(0.0, d);
            hh1=Tr2.mult(hh1,hh0);
          }

          h = Tr2.inv(hh1);////
          h=Tr2.mult(h,hh2);

       h_old = Tr2.mult(hh1,hh2_old);
       h_old = Tr2.mult(hh1_old,h_old);
                //  l.p1.x,l.p1.y,  l.p2.x,l.p2.y,
                //  lb0.p1.x,lb0.p1.y,  lb0.p2.x,lb0.p2.y
                //  );
       }
   }

public void findVal(Gstructure  g)
{
   if(moveX!=null)  moveX.findVal(g);
   if(moveY!=null)  moveY.findVal(g);
   if(scaleX!=null)  scaleX.findVal(g);
   if(scaleY!=null)  scaleY.findVal(g);
   if(shiftX!=null)  shiftX.findVal(g);
   if(shiftY!=null)  shiftY.findVal(g);

   if(rotate!=null)  rotate.findVal(g);

   if(modXX!=null)  modXX.findVal(g);
   if(modYY!=null)  modYY.findVal(g);
   if(modZZ!=null)  modZZ.findVal(g);
}

}



class GLabel0 implements  Serializable
{
  static final long serialVersionUID =1L;
  Tr2 h=null;
  Lin lb0=null;

 GLabel0 copy()
 {
    return new GLabel0(lb0.p1.x, lb0.p1.y, lb0.p2.x, lb0.p2.y);
 }

 GLabel0(double x1, double y1, double x2, double y2)
 {
    lb0= new Lin(x1,y1,x2,y2,1.0,1.0,0.0);
 }


  void setH()
   {
    if(lb0==null)
       h=Tr2.move(0.0,0.0);
    else
       h=Tr2.sovm(lb0.p1.x,lb0.p1.y,  lb0.p2.x,lb0.p2.y,
                    0.0,0.0,   0.0,1.0);
   }
}



class RealVal implements  Serializable  //Real constant object
{
   static final long serialVersionUID =1L;
        String name=null;
        double val=0.0;

public RealVal copy()
{
 return  new RealVal(val, name);
}

public RealVal(double v)
{val=v;}

void mutate(double a)
    {
      if(name!=null)
        return;
      if( Math.random()>0.5)
        val=val+a*(Math.random()-0.5);
     }

public RealVal(double v,String s)
{
        val=v;
        name=s;
}


String getStringValue()
{
if(name!=null)
    return name;
else
    return (""+val);
}

String getName() { return name;}

double getVal() {  return val;}

void   setVal(double d) {val=d;}

void   findVal(Gstructure  g)
{
    if(name!=null)
        if(g!=null)
            val=g.findConst(name);
}
}

//////////////////////////////////////////
class RealVal6  implements  Serializable //Condition
{
      static final long serialVersionUID =1L;
        RealVal c1,c2,c3,c4,c5,c6;

public RealVal6 copy()
{
 return  new RealVal6(c1.copy(), c2.copy(), c3.copy(), c4.copy(), c5.copy(), c6.copy());
}

public RealVal6(RealVal a1,RealVal a2,RealVal a3,RealVal a4,RealVal a5,RealVal a6)
{

 c1=a1;
 c2=a2;
 c3=a3;
 c4=a4;
 c5=a5;
 c6=a6;
}

public boolean calc(double x,double y,double z)
        {
           if(
              (x>=c1.val)&&
              (x<=c2.val)&&
              (y>=c3.val)&&
              (y<=c4.val)&&
              (z>=c5.val)&&
              (z<=c6.val)
              )
                return true;
            else
                return false;
        }

void   findVal(Gstructure  g)
{
  if(c1!=null)   c1.findVal(g);
  if(c2!=null)   c2.findVal(g);
  if(c3!=null)   c3.findVal(g);
  if(c4!=null)   c4.findVal(g);
  if(c5!=null)   c5.findVal(g);
  if(c6!=null)   c6.findVal(g);
}

}

//////////////////////////////////////////
class RealVal4 implements  Serializable  //Real constant object
{
static final long serialVersionUID =1L;
        RealVal c1,c2,c3,c4;

RealVal4 copy()
{
 return  new RealVal4(c1.copy(), c2.copy(), c3.copy(), c4.copy());
}

public RealVal4(RealVal a1,RealVal a2,RealVal a3,RealVal a4)
{
        c1=a1;
        c2=a2;
        c3=a3;
        c4=a4;
}

void mutate(double a)
    {
     if(c1!=null)  c1.mutate(a);
     if(c2!=null)  c2.mutate(a);
     if(c3!=null)  c3.mutate(a);
     if(c4!=null)  c4.mutate(a);
     }

public double calc(double x,double y,double z)
        {
        return c1.val+c2.val*x+c3.val*y+c4.val*z;
        }

public void findVal(Gstructure  g)
{
   if(c1!=null)  c1.findVal(g);
   if(c2!=null)  c2.findVal(g);
   if(c3!=null)  c3.findVal(g);
   if(c4!=null)  c4.findVal(g);
}



}



/////////////////////////////////////
//GEDIT/////
