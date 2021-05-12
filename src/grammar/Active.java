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


public class Active //Label for drawing
{
double x,y,z;       // attributes with user defined semantic
public Tr2 h=null;         // Current Transformation
String name=null;   // Name of the simbol of the  rule to invoke

public  Active(String nm, Tr2 h1, double x1,double y1,double z1)
{
        name=nm;
        h=h1;
        x=x1;
        y=y1;
        z=z1;
}

}
