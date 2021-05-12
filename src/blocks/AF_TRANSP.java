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
import java.awt.geom.*;
import java.awt.image.*;


public class AF_TRANSP extends Block_Prototype {
static final long serialVersionUID =1L;

public double[] params = null;

public AF_TRANSP() {
    super("AF_TRANSP", "AF_TRANSP", 1, Block_Prototype.class );
    params = new double[3];
    mutate_all();
  }

 void mutate_all(){
    for (int i = 0; i < params.length; i++) {
      params[i] = FW_Rand.rand01();
    }
  }


  public void mutate_this(){
    if(FW_Rand.rand01()<FW_Parm.getMutateProb()*0.33)
      mutate_all();
    for (int i = 0; i < params.length; i++) {
     if(FW_Parm.getMutateProb()>FW_Rand.rand01())
          params[i] = params[i] + 0.1*(FW_Rand.rand01()-0.5);
    }
  }

 

  public void paint(FW_ImageContext ct, int depth) {
    if (depth <= 0) {
      return;
    }
    Block_Prototype g = (Block_Prototype) sons[0];
    if (g == null) {
      return ;
    }
    ct.setAlfa((float)(params[0]*0.7+0.05));
    g.paint(ct, depth+1);
    ct.setAlfa((float)1.0);
    }

  }

  

