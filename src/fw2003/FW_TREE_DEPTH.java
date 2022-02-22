/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fw2003;

/**
 *
 * @author okislyuk
 */
public class FW_TREE_DEPTH {

    int depth = -1;
    boolean is_min = true;
    
    public FW_TREE_DEPTH() {
        depth = -1;
        is_min = true;
    }

    public FW_TREE_DEPTH(int depth0, boolean is_min0) {
        depth = depth0;
        is_min = is_min0;
    }

    boolean isChangable(int dpt) {
        if (is_min) {
            return dpt >= depth;
        } else {
            return dpt == depth;
        }
    }
}
