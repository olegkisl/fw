
package extension.ggrammar.util;
import  extension.ggrammar.*;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.Color;
import java.io.*;
import java.util.*;

public class GGElement implements  Serializable{
  static final long serialVersionUID = 1L;

  private java.util.List pointCoords = new ArrayList(20);
  private transient GeneralPath sh = null;
  private Color clr = null;
  private AffineTransform t = new AffineTransform();

  public GGElement(){
     Color clr = Color.GRAY;
   }

   public GGElement(AffineTransform tr, Color clr){
     t =tr;
     this.clr = clr;
   }

   public GGElement(float [] points, Color clr){
     moveTo(points[0], points[1]);
     for (int i = 2; (i+1) < points.length; i=i+2) {
       lineTo(points[i], points[i+1]);
     }
     closePath();
     this.clr = clr;
   }

 //public void setTransform(AffineTransform tr){
 //  t=tr;
 //  createGeneralPath();
 //}


  public void createGeneralPath(){
   GeneralPath gShape = new GeneralPath();
   Iterator iter = pointCoords.iterator();
   float x,y,x1,y1,x2,y2,x3,y3;
   int nn;
     if(pointCoords.size()<2){
       sh=gShape;
       return;
     }
     x=((Float)iter.next()).floatValue();
     y=((Float)iter.next()).floatValue();
     gShape.moveTo(x,y);
    while(iter.hasNext()) {
      nn = ((Integer) iter.next()).intValue();
      if(nn==0){
        gShape.closePath();
        continue;
      }
      x=((Float)iter.next()).floatValue();
      y=((Float)iter.next()).floatValue();
      if(nn==1){
        gShape.lineTo(x,y);
        continue;
      }
      x1=((Float)iter.next()).floatValue();
      y1=((Float)iter.next()).floatValue();
      if(nn==2){
        gShape.quadTo(x, y, x1, y1 );
        continue;
      }
      x2=((Float)iter.next()).floatValue();
      y2=((Float)iter.next()).floatValue();
      if(nn==3){
        gShape.curveTo(x,y, x1,y1, x2,y2);
      }
    }
    sh=gShape;
    sh.transform(t);
  }

  public void moveTo(float x, float y){
    pointCoords.clear();
    pointCoords.add(new Float(x));
    pointCoords.add(new Float(y));
  }

  public void lineTo(float x, float y){
    pointCoords.add(new Integer(1));
    pointCoords.add(new Float(x));
    pointCoords.add(new Float(y));
  }

  public void quadTo(float x, float y, float x1, float y1){
    pointCoords.add(new Integer(2));
    pointCoords.add(new Float(x));
    pointCoords.add(new Float(y));
    pointCoords.add(new Float(x1));
    pointCoords.add(new Float(y1));
  }

  public void curveTo(float x, float y, float x1, float y1, float x2, float y2){
    pointCoords.add(new Integer(3));
    pointCoords.add(new Float(x));
    pointCoords.add(new Float(y));
    pointCoords.add(new Float(x1));
    pointCoords.add(new Float(y1));
    pointCoords.add(new Float(x2));
    pointCoords.add(new Float(y2));
  }

  public void closePath(){
    pointCoords.add(new Integer(0));
    createGeneralPath();
  }

  public GeneralPath getCurrentGeneralPath(){
    createGeneralPath();
    return sh;
 }


  public GeneralPath getGeneralPath(){
    if(sh==null){
      createGeneralPath();
    }
    return sh;
 }

 public Color getColor(){
   return clr;
 }

 }

