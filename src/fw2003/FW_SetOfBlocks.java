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

public interface FW_SetOfBlocks {

  public java.util.List getBlocksList();

  public FW_BlockInterface  getCurrentBlock();

  public void  addBlock(FW_BlockInterface b);

  public void  updateView();


}