package grammar;

import fw2003.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;
//
//   Takes Block_Prototype_Fxy as possible argument
//   use Block_Prototype_Fxy_C.fc(x,y) to calculate Clr
//   implement Clr fc(double x, double y)
//


public class Block_Prototype_G extends Block_Prototype {
static final long serialVersionUID =1L;

Gstructure gram= new Gstructure("Empty");

public Block_Prototype_G(String name, Gstructure gr, int nson, String[] ptrn) {
    super(name, name, nson, Block_Prototype.class );
    if(gr!=null)
         gram =gr;
    for (int i = 0; i < patterns.length; i++) {
      patterns[i] = ptrn[i];
    }


  }

  public void mutate_this(){
    if(gram!=null)
      gram.mutate();
  }

  // public void mutateColor_this(){;}

  public void mutatePallet_this(FW_PalletInterface p){
    if(gram!=null)
      gram.mutatePallet(p);
}


  public void mutateLarge_this(){
    if(gram!=null)
      gram.mutate_back();
 }




  public void preparePaintingProcess(){
   gram.setConstValues();
   gram.setHValues();
  }

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    if(ct.isPaintStoped()){
        ct.repaint();
        return;
      }
    Active act = null;
    String nm = null;

    if(ct.getApplicationContext() == null){
      nm= gram.getInitName();
      act = new Active(nm,new Tr2(), 10.0, 0.0,0.0);
      ct.setApplicationContext(null);
    }
    else{
     act = (Active)ct.getApplicationContext();
     if((act.name == null)||(act.name.length()==0))
       act.name = gram.getInitName();
    }

    gram.step(ct,this,act,depth);
  }
}

//////////////////////////////////////////////////

/*
class Active //Label for drawing
{
double x,y,z;       // attributes with user defined semantic
Tr2 h=null;         // Current Transformation
String name=null;   // Name of the simbol of the  rule to invoke

public  Active(String nm, Tr2 h1, double x1,double y1,double z1)
{
        name=nm;
        h=h1;
        x=x1;
        y=y1;
        z=z1;
}

}*/


