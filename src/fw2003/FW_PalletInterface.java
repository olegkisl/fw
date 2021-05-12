package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public interface FW_PalletInterface {
  // Pallet interface for random color generation for mutatePallet(Pallet p) method

    public java.awt.Color getColor();
    public java.util.List getColorsList();


}