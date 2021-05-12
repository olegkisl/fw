package fw2003;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


public class FW_newBlocksAdd extends JDialog {
    FW_Desktop blocksDesktop = null;

    public FW_newBlocksAdd(JFrame f, FW_Desktop b) throws HeadlessException {
        super(f, true);
        blocksDesktop = b;
        try {
            treeInit();
            jbInit();
            jTree1.getSelectionModel().setSelectionMode
                    (TreeSelectionModel.SINGLE_TREE_SELECTION);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    private void jbInit() throws Exception {
        jButtonAdd.addActionListener(new
                                     FW_newBlocksAdd_jButtonAdd_actionAdapter(this));
        jButtonCancel.addActionListener(new
                                        FW_newBlocksAdd_jButtonCancel_actionAdapter(this));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.
                                      DISPOSE_ON_CLOSE);
        jScrollPane1.setPreferredSize(new Dimension(150, 400));
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jButtonCancel.setText("Exit");
        jScrollPane1.getViewport().add(jTree1);
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
        jButtonAdd.setText("Load Node to Image Producer Pane");
        jPanel1.add(jButtonAdd);
        jPanel1.add(jButtonCancel);
    }

    JScrollPane jScrollPane1 = new JScrollPane();
    JTree jTree1 = new JTree();
    JPanel jPanel1 = new JPanel();
    JButton jButtonCancel = new JButton();
    JButton jButtonAdd = new JButton();



    void treeInit() {
        DefaultMutableTreeNode category = null;
        File dext = new File(FW_Parm.rootExtensionClassDir);
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode(new TNode(null,
                "Image Producer Nodes"));

        File[] files = dext.listFiles();
        if(files!=null)
        for (int i = 0; i < files.length; i++) {
            File ext = files[i];
            if (ext.isDirectory()) {
                String nm = ext.getName();
                File ext1 = new File(ext, "node");
                if (ext1.exists() && ext1.isDirectory()) {
                    category = new DefaultMutableTreeNode(new TNode(null, nm));
                    top.add(category);
                    createNodes(category, "extension."+nm+".node", ext1);
                }
            }

            System.out.println(files[i].toString());
        }

        File root = new File(FW_Parm.rootBlockClassDir);
        category = new DefaultMutableTreeNode(new TNode(null, "OTHER"));
        top.add(category);
        //System.out.println("$$$$$$ "+ root.getAbsolutePath());
        createNodes(category, "blocks", root);
        /*
           String[] children = root.list();
           if ( (children == null) || (0 == children.length)) {
             return;
           }
           else {
             Object selectedValue = JOptionPane.showInputDialog(null,
             "Build new Block", "Build new Block",
             JOptionPane.INFORMATION_MESSAGE, null,
             children, children[0]);
             if(selectedValue!=null){
               try {
         String name = "blocks."+  FW_Utils.delExtension((String)selectedValue);
                 Class c = Class.forName(name);
         new FW_BlockFrame(blocksDesktop, (FW_BlockInterface)c.newInstance() );
               }
               catch (Exception ex) {
                  System.out.println(ex);
                  ex.printStackTrace();
               }

         */

        jTree1 = new JTree(top);
    }

    private void createNodes(DefaultMutableTreeNode top, String ext, File dir) {
        DefaultMutableTreeNode category = null;
        File[] files = dir.listFiles();
        Arrays.sort(files);
        for (int i = 0; i < files.length; i++) {
            File ff = files[i];
            if (ff.isFile()) {
                String nm = FW_Utils.delExtension(ff.getName());
                category = new DefaultMutableTreeNode(new TNode(ext +"."+ nm,
                        ff.getName()));
                top.add(category);
            }

            System.out.println(files[i].toString());
        }
    }


    public void jButtonAdd_actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                      jTree1.getLastSelectedPathComponent();
        if (node == null) {
            return;
        }
        Object g = node.getUserObject();
        if (g == null) {
            return;
        }
        String name = ((TNode)g).class_name;
        if (name == null) {
            return;
        }
        try {
                        //  FW_Utils.delExtension((String) selectedValue);
            Class c = Class.forName(name);
            new FW_BlockFrame(blocksDesktop, (FW_BlockInterface) c.newInstance());
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }


    }

    public void jButtonCancel_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    static class TNode {
        String class_name = "";
        String message = "";
        TNode(String cln, String m) {
            class_name = cln;
            message = m;
        }

        public String toString() {
            return message;
        }
    }


}


class FW_newBlocksAdd_jButtonCancel_actionAdapter implements ActionListener {
    private FW_newBlocksAdd adaptee;
    FW_newBlocksAdd_jButtonCancel_actionAdapter(FW_newBlocksAdd adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCancel_actionPerformed(e);
    }


}


class FW_newBlocksAdd_jButtonAdd_actionAdapter implements ActionListener {
    private FW_newBlocksAdd adaptee;
    FW_newBlocksAdd_jButtonAdd_actionAdapter(FW_newBlocksAdd adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonAdd_actionPerformed(e);
    }
}
