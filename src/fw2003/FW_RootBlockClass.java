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

public abstract class FW_RootBlockClass //implements FW_BlockInterface, Serializable
{

String name ="";
String description ="";


public FW_RootBlockClass(String name, String dsecription, int nsons) {
  }

//General
public String getName(){ return name;}
public String getDescription(){ return description; }
//public int    getType();

/*
// Build process
public int getNumberOfSons();
public String getSonPattern(int n);
public Class getSonClass(int n);
public FW_BlockInterface getSon(int n);
public void setSon(int n, FW_BlockInterface block);
public void clearAllSons();
public FW_BlockInterface copy();

//Mutations
public void mutate();
public void mutateColor();
public void mutatePallet(FW_PalletInterface p);

//Standard Editor
public boolean isParamEditable();
public java.util.List    getParamList();
public void    setParamList(java.util.List param);

//Free Form Editor
public boolean isEditable();
public javax.swing.JPanel getEditor();
*/

}