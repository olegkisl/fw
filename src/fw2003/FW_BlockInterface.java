package fw2003;
/*
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.tree.*;
import javax.swing.JDesktopPane;
import java.util.*;
*/
/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public interface FW_BlockInterface {
 //General
 public String getName();
 public String getDescription();
 public void setName(String name);
 public void setDescription(String description);


 // Build process
 public boolean isInitial(); // Can be used as initial block
 public int getNumberOfSons();
 public String getSonPattern(int n);
 public Class getSonClass(int n);
 public FW_BlockInterface getSon(int n);
 public void setSon(int n, FW_BlockInterface block);
 public void clearAllSons();
 public FW_BlockInterface copy();

 //Mutations
 public void mutate();
 public void mutateLarge();
 public void mutateColor();
 public void mutatePallet(FW_PalletInterface p);

 //Standard Editor
 public boolean isParamEditable();
 public java.util.List    getParamList();
 public void    setParamList(java.util.List param);

 //Free Form Editor
 public boolean isEditable();
 public javax.swing.JPanel getEditor();

 // Draw Process
  public void prepareToPaint();
  public void paint( FW_ImageContext ct, int depth);

// public double funcS(double x, double y);
// public double funcS(double x, double y, double z);
// public point  funcP(double x, double y);////////////////////
 //public java.util.List  getPrimitiveList(int n);

}