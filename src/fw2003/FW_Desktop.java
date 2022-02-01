package fw2003;
import java.awt.*;
import javax.swing.JDesktopPane;
import java.util.*;
import javax.swing.BorderFactory;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_Desktop extends JDesktopPane
 {
  FW_InternalFrame focus = null;
  java.util.List frames = new LinkedList();

  public void setFocus(FW_InternalFrame f){
  focus = f;
  System.out.println("focus "+f);
  }

  public FW_InternalFrame getFocus(){return focus;}

  public FW_Desktop() {
      super();
     setBackground( new Color(180, 170 ,150) );
     setBorder(BorderFactory.createLoweredBevelBorder());
  }

  public void addFrame( FW_InternalFrame f) {
  frames.add(f);
  }

  public void removeFrame( FW_InternalFrame f) {
  if(focus == f)
    focus = null;
  frames.remove(f);
  }

  public void removeAllFrames() {
  focus = null;
  for (Iterator i = frames.iterator(); i.hasNext(); ) {
    FW_InternalFrame item = (FW_InternalFrame)i.next();
    item.dispose();
  }
  frames.clear();
  }
 
    public FW_InternalFrame getByName(String name) {
        for (Iterator i = frames.iterator(); i.hasNext();) {
            FW_InternalFrame item = (FW_InternalFrame) i.next();
            System.out.println(item.name);
            System.out.println(name);
            if (item.name == null ? name == null : item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

  public int nn() {
   return frames.size();
 }


}