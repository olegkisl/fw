package fw2003;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.tree.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_SetOfBlocksFrame extends FW_InternalFrame implements
        FW_SetOfBlocks {
    JScrollPane jScrollPane1 = new JScrollPane();
    JTree jTree1 = new JTree();
    File root = null;
    FWAdapter_BlockSetTree model = null;

    //
    JPanel jPanel = new JPanel();
    JButton jBuild = new JButton();
    JButton jCopyBlock = new JButton();

    // Popup menu
    JPopupMenu jPopupMenu = new JPopupMenu();
    JMenuItem jCopy = new JMenuItem();
    JMenuItem jDelete = new JMenuItem();
    JMenuItem jSaveStart = new JMenuItem();
    JMenuItem jRestoreStart = new JMenuItem();
    JMenuItem jViewInfo = new JMenuItem();

    private void tryShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            // checkEnable();
            jPopupMenu.show(e.getComponent(),
                            e.getX(), e.getY());
        }
    }


    //////////////////////////////Interface implementation
    public java.util.List getBlocksList() {
        java.util.List blocks = new LinkedList();
        for (Iterator i = treeMap.values().iterator(); i.hasNext(); ) {
            blocks.add(i.next());
        }
        return blocks;
    }

    public java.util.List getCurrentBlocks() {
        DefaultMutableTreeNode[] nodes = getCurrentNodes();
        if ((nodes == null) || (nodes.length == 0)) {
            return null;
        }
        java.util.List ls = new LinkedList();
        for (int i = 0; i < nodes.length; i++) {
            String nm = (String) nodes[i].getUserObject();
            if (nm == null) {
                continue;
            }
            if (!nodes[i].isLeaf()) {
                continue;
            }
            Object b = treeMap.get(nm);
            if (b != null) {
                ls.add(b);
            }
        }
        return ls; //(FW_BlockInterface)FW_Rand.getRandObject(ls);
    }

    public FW_BlockInterface getCurrentBlock() {
        /*  DefaultMutableTreeNode t =(DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
          if(t==null)
            return null;
          String nm = (String)t.getUserObject();
          if(nm == null)
            return null;
          if(!t.isLeaf())
            return null;
          Object b = treeMap.get(nm);
          return (FW_BlockInterface)b ;*/
        return (FW_BlockInterface) FW_Rand.getRandObject(getCurrentBlocks());
    }


    public DefaultMutableTreeNode[] getCurrentNodes() {
        TreePath[] pp = jTree1.getSelectionModel().getSelectionPaths();
        int ln = 0;
        if (pp != null) {
            ln = pp.length;
        }
        DefaultMutableTreeNode[] t = new DefaultMutableTreeNode[ln];
        for (int i = 0; i < t.length; i++) {
            t[i] = (DefaultMutableTreeNode) pp[i].getLastPathComponent();
        }
        return t;
    }


    public void addBlock(FW_BlockInterface b) {
        if (b == null) {
            return;
        }
        if (b.getName() == null) {
            return;
        }
        String name = b.getName();

        while (treeMap.containsKey(name)) {
            name = name + "_" + FW_Parm.getCode();
        }
        b.setName(name);
        FW_SaveRestore.saveBlock(b, treeFile);
       // treeMap.put(name, b);

  //     File file = new File(treeFile,name); 
       File file = new File(treeFile,name+"."+FW_SaveRestore.BLOCK_EXTENSION);///fix
        if(file!=null){
        b = FW_SaveRestore.restoreBlock(file);
        b.setName(name);////
        if(b!=null){
            treeMap.put(name, b);
          }
        }

    }

    public void deleteBlock(FW_BlockInterface b) {
        if (b == null) {
            return;
        }
        if (b.getName() == null) {
            return;
        }
        String name = b.getName();

        if (treeMap.containsKey(name)) {
            treeMap.remove(name);
            FW_SaveRestore.deleteBlock(b, treeFile);
        }
        //   b.setName(name);
        //   FW_SaveRestore.deleteBlock(b,treeFile);
        //   treeMap.put(name,b);
    }


    public void updateView() {
        reReadTree();
        TreeModel m = new DefaultTreeModel(treeModel);
        jTree1.setModel(m);
        jTree1.invalidate();
        jTree1.validate();
        jTree1.repaint();
    }

    /////////////// Tree ad Map creation ////////////////////////////////////////////
    DefaultMutableTreeNode treeModel = null;
    String treeName = null;
    File treeFile = null;
    Map treeMap = new HashMap();

    private DefaultMutableTreeNode createTree(File root, boolean addObj) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(root.
                getAbsolutePath(), true);
        treeName = root.getAbsolutePath();
        treeModel = t;
        treeFile = root;

        String[] children = root.list();
        if ((children == null) || (0 == children.length)) {
            ;
        } else {
            for (int i = 0; i < children.length; i++) {
                createTreeN(t, new File(root, children[i]), addObj);
            }
        }
        return t;
    }

    private void reReadTree() {
        DefaultMutableTreeNode t = treeModel;
        t.removeAllChildren();
        String[] children = root.list();
        if ((children == null) || (0 == children.length)) {
            ;
        } else {
            for (int i = 0; i < children.length; i++) {
                createTreeN(t, new File(root, children[i]), false);
            }
        }
    }


    private void createTreeN(DefaultMutableTreeNode p, File fff, boolean addObj) {
        DefaultMutableTreeNode t = null;
        if (fff.isFile()) {
            String nm = FW_Utils.getDif(fff.getAbsolutePath(), treeName);
            nm = FW_Utils.delExtension(nm); /// DElete Extension
            t = new DefaultMutableTreeNode(nm, false);
            if(FW_SaveRestore.isReadableBlock(fff)){
                p.add(t);
            }
            if (addObj) {
                FW_BlockInterface blk = FW_SaveRestore.restoreBlock(fff);
                if (blk != null) {
                    blk.setName(nm);
                    treeMap.put(nm, blk);
                    System.out.println(nm);
                }
            }

        } else {
            t = new DefaultMutableTreeNode(fff.getName(), true);
            p.add(t);
            String[] children = fff.list();
            if ((children == null) || (0 == children.length)) {
                ;
            } else {
                for (int i = 0; i < children.length; i++) {
                    createTreeN(t, new File(fff, children[i]), addObj);
                }
            }
        }

    }

    private FW_BlockInterface getDefaultStartBlock() {
        java.util.List ls = new LinkedList();
        for (Iterator iter = treeMap.keySet().iterator(); iter.hasNext(); ) {
            String k = (String) iter.next();
            if (k.startsWith("start") || k.startsWith("Start") ||
                k.startsWith("START")) {
                ls.add(treeMap.get(k));
            }
        }
        return (FW_BlockInterface) FW_Rand.getRandObject(ls);
    }


///////////////////////////////////////////////////////////////////////////////////
    /* private void addToBlocks(Object in) {
       if (model.isLeaf(in)) {
         File f = ( (F) in).file;
     String name = FW_Utils.getDif(f.getAbsolutePath(), root.getAbsolutePath());
         System.out.println(name);
         blocks.add(in); //TBD
       }
       else {
         int nn = model.getChildCount(in);
         for (int i = 0; i < nn; i++) {
           addToBlocks(model.getChild(in, i));
         }
       }
     }*/

    public FW_SetOfBlocksFrame(FW_Desktop d, File root) {
        super(d, root.getName());
        this.root = root;
        model = new FWAdapter_BlockSetTree(root);
        createTree(root, true);
        // addToBlocks(model.getRoot());

        jTree1 = new JTree(treeModel);
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.
                DISCONTIGUOUS_TREE_SELECTION);

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.pack();
        this.setSize(230,350);
        show();
    }

    public FW_SetOfBlocksFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jBuild.setText("Build");
        jBuild.addActionListener(new FW_SetOfBlocksFrame_jBuild_actionAdapter(this));
        jCopyBlock.setText("Copy");
        jCopyBlock.addActionListener(new
                                     FW_SetOfBlocksFrame_jCopyBlock_actionAdapter(this));
        this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel, BorderLayout.SOUTH);
        jScrollPane1.getViewport().add(jTree1, null);
        jPanel.add(jBuild, null);
        //   jPanel.add(jCopyBlock, null);

        //////////////////////
        MouseListener popupListener = new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                tryShowPopup(e);
            }
        };
        jTree1.addMouseListener(popupListener);
        jScrollPane1.addMouseListener(popupListener);
        //////////////
        jPopupMenu.addSeparator();
        jPopupMenu.add(jCopy);
        jCopy.setText("Copy Elements from IP Factory to IP Pane");
        jCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCopyBlock_actionPerformed(null);
            }
        });

        jPopupMenu.add(jDelete);
        jDelete.setText("Delete Elements From IP Factory");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jDelete();
            }
        });

        /*    jPopupMenu.addSeparator();
            jPopupMenu.add(jSaveStart);
            jSaveStart.setText("Save Start");
            jSaveStart.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(ActionEvent e) {
                //printXML();
              }
            });

            jPopupMenu.add(jRestoreStart);
            jRestoreStart.setText("Restore Start");
         jRestoreStart.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(ActionEvent e) {
                //printXML();
              }
            });*/

        jPopupMenu.addSeparator();

        jPopupMenu.add(jViewInfo);
        jViewInfo.setText("Open IP Factory Information Window");
        jViewInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
               showInfo();
            }
        });

    }

    void showInfo(){
        try {
                   File ff = new File(root, "readme.txt");
                   if (ff.exists()) {
                       FileReader fff = new FileReader(ff);
                       BufferedReader in = new BufferedReader(fff);
                       String str, strall = "";
                       while ((str = in.readLine()) != null) {
                           strall = strall +"\n"+ str;
                       }
                       in.close();
                       JDialog frame =new FW_infoDialog(null, "Image Producer Factory "+root.getAbsolutePath(), true, strall);
                       frame.pack();
                       frame.setLocation(100,100);
                       frame.setSize(500,350);
                       frame.setVisible(true);
                   }
               } catch (Exception et) {
                   System.out.println(et);
               }

    }
    
 FW_BlockInterface build_Object() {
        FW_BlockInterface b = getCurrentBlock();
        if (b == null) {
            b = getDefaultStartBlock();
        }
        if (b == null) { 
            return null;
        }
        FW_BlockInterface bb = FW_Parm.getCurrentBuilder().construct(
                getBlocksList(), b);    
        return bb;      
    }

    void jBuild_actionPerformed(ActionEvent e) {
        FW_BlockInterface b = getCurrentBlock();
        if (b == null) {
            b = getDefaultStartBlock();
        }
        if (b == null) {
            FW_Utils.warning("Please select starting node");
            return;
        }
        FW_BlockInterface bb = FW_Parm.getCurrentBuilder().construct(
                getBlocksList(), b);
        if (bb == null) {
            return;
        }
        new FW_BlockFrame(FW_Parm.getBlockDesktop(), bb);
    }

    void jCopyBlock_actionPerformed(ActionEvent e) {
        /*  FW_BlockInterface b =getCurrentBlock();
          if (b ==null)
            return;
           FW_BlockInterface bc = b.copy();
           new FW_BlockFrame(FW_Parm.getBlockDesktop(),bc);*/
        java.util.List ls = getCurrentBlocks();
        if (ls.size() == 0) {
            FW_Utils.warning(
                    "Please select nodes to copy to Image Producers Pane");
            return;
        }

        for (Iterator iter = ls.iterator(); iter.hasNext(); ) {
            FW_BlockInterface b = (FW_BlockInterface) iter.next();
            FW_BlockInterface bc = b.copy();
            new FW_BlockFrame(FW_Parm.getBlockDesktop(), bc);
        }
    }

    void jDelete() {
        java.util.List ls = getCurrentBlocks();
        for (Iterator iter = ls.iterator(); iter.hasNext(); ) {
            FW_BlockInterface b = (FW_BlockInterface) iter.next();
            deleteBlock(b);
        }
        this.updateView();
    }


}


class FW_SetOfBlocksFrame_jBuild_actionAdapter implements java.awt.event.
        ActionListener {
    FW_SetOfBlocksFrame adaptee;

    FW_SetOfBlocksFrame_jBuild_actionAdapter(FW_SetOfBlocksFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBuild_actionPerformed(e);
    }
}


class FW_SetOfBlocksFrame_jCopyBlock_actionAdapter implements java.awt.event.
        ActionListener {
    FW_SetOfBlocksFrame adaptee;

    FW_SetOfBlocksFrame_jCopyBlock_actionAdapter(FW_SetOfBlocksFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCopyBlock_actionPerformed(e);
    }
}
