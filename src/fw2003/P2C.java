package fw2003;

import java.util.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;

public class P2C extends P2 implements Serializable  {
  public double c=1.0;

  public  P2C()
      {super(0.0,0.0);
      }

  public  P2C(double x1,double y1)
      {super(x1,y1);}

  public  P2C(P2 p)
      {super(p);}

  public P2 actNew(Tr2 t)
    {P2 pp= new P2C(x,y);
     pp.act(t);
     return pp;
    }


}

