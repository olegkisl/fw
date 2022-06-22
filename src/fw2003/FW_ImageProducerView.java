package fw2003;

import java.awt.*;
import java.util.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class FW_ImageProducerView extends JFrame {
    FW_BlockInterface ip = null;
    int nnNodes = 1;
    java.util.List ls = new ArrayList(300);

    public FW_ImageProducerView(FW_BlockInterface p) throws HeadlessException {
        super("Model Structure");
        String info="";
        if (p instanceof Block_Prototype_Fxy) {
            PaintTransformation pt = ((Block_Prototype_Fxy)p).paintTransformation;
            if( pt != null){
                info=("    image X/Y ratio = "+ pt.maxX+"/"+pt.maxY);
            }
        }
        int depth = FW_Builder.treeDepth(p);
        int nulls = FW_Builder.treeNullNodesNN(p);
        info+=(" |h="+depth+"|nl="+nulls+"|");
        this.ip = p;
        try {
            treeInit();
            jbInit();
            jTextField1.setText("Number of nodes = " + nnNodes+info);
            jTree1.getSelectionModel().setSelectionMode
                    (TreeSelectionModel.SINGLE_TREE_SELECTION);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public FW_ImageProducerView() throws HeadlessException {
        super();
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jTextField1.setBackground(Color.lightGray);
        jTextField1.setBorder(BorderFactory.createEtchedBorder());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jScrollPane1.setMinimumSize(new Dimension(120, 121));
        jScrollPane1.setPreferredSize(new Dimension(400, 400));
        jMenuMutate.setText("Mutate");
        jMenuMutate.addActionListener(new
                                      FW_ImageProducerView_jMenuMutate_actionAdapter(this));
        jMenuMutateL.setText("Mutate(Large)");
        jMenuMutateL.addActionListener(new
                                       FW_ImageProducerView_jMenuMutateL_actionAdapter(this));
        jMenuMutateClr.setText("Mutate Color");
        jMenuMutateClr.addActionListener(new
                                         FW_ImageProducerView_jMenuMutateClr_actionAdapter(this));
        jTree1.addMouseListener(new FW_ImageProducerView_jTree1_mouseAdapter(this));
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        this.getContentPane().add(jTextField1, java.awt.BorderLayout.NORTH);
        jScrollPane1.getViewport().add(jTree1);
        jPopupMenu1.add(jMenuMutate);
        jPopupMenu1.add(jMenuMutateL);
        jPopupMenu1.add(jMenuMutateClr);
    }

    JScrollPane jScrollPane1 = new JScrollPane();
    JTree jTree1 = new JTree();
    JTextField jTextField1 = new JTextField();
    JPopupMenu jPopupMenu1 = new JPopupMenu();
    JMenuItem jMenuMutate = new JMenuItem();
    JMenuItem jMenuMutateL = new JMenuItem();
    JMenuItem jMenuMutateClr = new JMenuItem();

    void treeInit() {
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode(new TNode(ip, getBlockName(ip)));
        ls.add(ip);
        createNodes(top, ip);
        jTree1 = new JTree(top);
    }


    private String getBlockName(FW_BlockInterface p){

    return p.getName()+" ("+p.getClass().getName()+")";
    }

    private void createNodes(DefaultMutableTreeNode top, FW_BlockInterface ip) {
        FW_BlockInterface p;
        DefaultMutableTreeNode category = null;
        int nn = ip.getNumberOfSons();
        if (nn == 0) {
            return;
        }

        for (int i = 0; i < nn; i++) {
            p = ip.getSon(i);
            if (p != null) {
                if (ls.contains(p)) {
                    category = new DefaultMutableTreeNode(new TNode(p,
                            "LINKED TO ===> " + getBlockName(p)));
                    top.add(category);
                } else {
                    ls.add(p);
                    category = new DefaultMutableTreeNode(new TNode(p,
                            getBlockName(p)));
                    top.add(category);
                    nnNodes++;
                    createNodes(category, p);
                }
            } else{
                category = new DefaultMutableTreeNode(
                            "-NULL-");
                top.add(category);
            }
        }
    }

    public FW_BlockInterface getIP() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                      jTree1.getLastSelectedPathComponent();
        if (node == null) {
            return null;
        }
        Object g = node.getUserObject();
        if (g == null) {
            return null;
        }
        return ((TNode) g).ip;
    }


    public void jMenuMutate_actionPerformed(ActionEvent e) {
        FW_BlockInterface ipp = getIP();
        if (ipp == null) {
            return;
        }
        ipp.mutate();
        // System.out.println("Mutate "+ipp.getName());
    }

    public void jMenuMutateL_actionPerformed(ActionEvent e) {
        FW_BlockInterface ipp = getIP();
        if (ipp == null) {
            return;
        }
        ipp.mutateLarge();
        // System.out.println("MutateL "+ipp.getName());
    }

    public void jMenuMutateClr_actionPerformed(ActionEvent e) {
        FW_BlockInterface ipp = getIP();
        if (ipp == null) {
            return;
        }
        FW_PalletInterface pl = FW_Parm.getCurrentPallet();
        if (pl != null) {
            ipp.mutatePallet(pl);
            //  System.out.println("MutateClr "+ipp.getName());
        }
    }

    public void jTree1_mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            jPopupMenu1.show(e.getComponent(),
                             e.getX(), e.getY());
        }
    }


    static class TNode {
        FW_BlockInterface ip = null;
        String message = "";
        TNode(FW_BlockInterface p, String m) {
            ip = p;
            message = m;
        }

        public String toString() {
            return message;
        }
    }


}


class FW_ImageProducerView_jTree1_mouseAdapter extends MouseAdapter {
    private FW_ImageProducerView adaptee;
    FW_ImageProducerView_jTree1_mouseAdapter(FW_ImageProducerView adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseReleased(MouseEvent e) {
        adaptee.jTree1_mouseReleased(e);
    }
}


class FW_ImageProducerView_jMenuMutateClr_actionAdapter implements
        ActionListener {
    private FW_ImageProducerView adaptee;
    FW_ImageProducerView_jMenuMutateClr_actionAdapter(FW_ImageProducerView
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMutateClr_actionPerformed(e);
    }
}


class FW_ImageProducerView_jMenuMutateL_actionAdapter implements ActionListener {
    private FW_ImageProducerView adaptee;
    FW_ImageProducerView_jMenuMutateL_actionAdapter(FW_ImageProducerView
            adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMutateL_actionPerformed(e);
    }
}


class FW_ImageProducerView_jMenuMutate_actionAdapter implements ActionListener {
    private FW_ImageProducerView adaptee;
    FW_ImageProducerView_jMenuMutate_actionAdapter(FW_ImageProducerView adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMutate_actionPerformed(e);
    }
}
