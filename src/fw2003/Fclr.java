package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
import java.io.*;
public class Fclr implements  Serializable {
  static final long serialVersionUID =1L;
  public double r,g,b,t;
  public Fclr(double rr,double gg,double bb){
    r =rr;
    g=gg;
    b=bb;
    t=0.0;
  }
}
