package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
import java.util.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;


public class P2_type extends P2 {
  private int type=0;
  public P2_type(double x1,double y1, int type) {
   super(x1,y1);
   this.type= type;
  }
  public int getType() { return type;}
}