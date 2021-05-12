package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public interface FW_BlockInterface_Param {

// param interface for Standard Editor
// getParamList() returns Param list

public String getDescription();
public double getMaxValue();
public double getMinValue();
public double getValue();
public void   setValue(double val);
}