package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;
import java.io.*;

public class FWAdapter_BlockSetTree implements TreeModel {
  private Vector treeModelListeners;
  protected File root;
  protected F fRoot;

  public FWAdapter_BlockSetTree(File root) {
    this.root = root;
    fRoot = new F(root, root);
  }

  // The model knows how to return the root object of the tree
  public Object getRoot() {
    return fRoot;
  }

  // Tell JTree whether an object in the tree is a leaf or not
  public boolean isLeaf(Object node) {
    return ( (F) node).file.isFile();
  }

  // Tell JTree how many children a node has
  public int getChildCount(Object parent) {
    String[] children = ( (F) parent).file.list();
    if (children == null)
      return 0;
    return children.length;
  }

  // Fetch any numbered child of a node for the JTree.
  // Our model returns File objects for all nodes in the tree.  The
  // JTree displays these by calling the File.toString() method.
  public Object getChild(Object parent, int index) {
    String[] children = ( (F) parent).file.list();
    if ( (children == null) || (index >= children.length))
      return null;
    return new F(new File( ( (F) parent).file, children[index]),root);
  }

  // Figure out a child's position in its parent node.
  public int getIndexOfChild(Object parent, Object child) {
    String[] children = ( (F) parent).file.list();
    if (children == null)
      return -1;
    String childname = ( (F) child).file.getName();
    for (int i = 0; i < children.length; i++) {
      if (childname.equals(children[i]))
        return i;
    }
    return -1;
  }

  // This method is only invoked by the JTree for editable trees.
  public void valueForPathChanged(TreePath path, Object newvalue) {
    System.out.println("valueForPathChanged");
  }

  //////////  EVENTS MANAGEMENT /////////////////////////
  public void changed() { //fire tree model events
    Object[] nn = {
        fRoot};
    TreeModelEvent e = new TreeModelEvent(this, nn);
    fireTreeStructureChanged(e);
  }

  /* public void changed(F node) { //fire tree model events
     List ls = new LinkedList();
     ls.add(node);
     while(node!=root){
    node = node.getParentFile();
    if(node == null)
        return;
    ls.add(0,node);
     }
     Object  [] nn = ls.toArray();
     TreeModelEvent e = new TreeModelEvent(this, nn);
     fireTreeStructureChanged(e);
     }*/

  public synchronized void removeTreeModelListener(TreeModelListener l) {
    if (treeModelListeners != null && treeModelListeners.contains(l)) {
      Vector v = (Vector) treeModelListeners.clone();
      v.removeElement(l);
      treeModelListeners = v;
    }
  }

  public synchronized void addTreeModelListener(TreeModelListener l) {
    Vector v = treeModelListeners == null ? new Vector(2) :
        (Vector) treeModelListeners.clone();
    if (!v.contains(l)) {
      v.addElement(l);
      treeModelListeners = v;
    }
  }

  protected void fireTreeNodesChanged(TreeModelEvent e) {
    if (treeModelListeners != null) {
      Vector listeners = treeModelListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ( (TreeModelListener) listeners.elementAt(i)).treeNodesChanged(e);
      }
    }
  }

  protected void fireTreeNodesInserted(TreeModelEvent e) { // not used now
    if (treeModelListeners != null) {
      Vector listeners = treeModelListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ( (TreeModelListener) listeners.elementAt(i)).treeNodesInserted(e);
      }
    }
  }

  protected void fireTreeNodesRemoved(TreeModelEvent e) { // not used now
    if (treeModelListeners != null) {
      Vector listeners = treeModelListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ( (TreeModelListener) listeners.elementAt(i)).treeNodesRemoved(e);
      }
    }
  }

  protected void fireTreeStructureChanged(TreeModelEvent e) {
    if (treeModelListeners != null) {
      Vector listeners = treeModelListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ( (TreeModelListener) listeners.elementAt(i)).treeStructureChanged(e);
      }
    }
  }
}
  ///////////////////////////////////
  class F{
    public File file, root;
    public F(File f, File r){
      file = f;
      root = r;
    }
    public String toString(){
      return FW_Utils.getDif(file.getAbsolutePath(),root.getAbsolutePath());
    }
  }






