

package fw2003;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author Oleg Kislyuk
 * @version 3.0
 */
public class FW_DESTRUCTOR extends JDialog {

    static String resultsFolder = "Mresults";
    static int rootBlock = 1;
    static int nnmutants = 11;
    static double startProbub = 1.0;
    static int minDepts = 2;
    static int maxDepts = 5;
    static double trimProbab = 0.0;
    
    
    static String injectionFolder = "0_BLOCKS";
    static int injection_terminal_nodes_only = 0;
    static double injection_probability = 0.0;
    static int injection_root = 0;
    static String treeinjectionFolder = "0_BLOCKS";
    static double treeinjection_probability = 0.0;
    static int treeinjection_min_depth = -1;
    
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JButton jOK = new JButton();
    JButton jCancel = new JButton();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField jResultsFolder = new JTextField();
    JLabel jLabel2 = new JLabel();
    JTextField jRootBlock = new JTextField();
    JLabel jLabel3 = new JLabel();
    JTextField jNNmutants = new JTextField();
    JLabel jLabel4 = new JLabel();
    JTextField jMutateProbub = new JTextField();
    JLabel jLabel5 = new JLabel();
    JTextField jNmutations = new JTextField();
    JLabel jLabel6 = new JLabel();
    JTextField jStructuralNmutations = new JTextField();
    JLabel jLabel7 = new JLabel();
    JTextField jColorlNmutations = new JTextField();
    
    
    JLabel jLabel7a = new JLabel();
    JTextField jInjectionFolder = new JTextField();
    JLabel jLabel8 = new JLabel();
    JTextField jinjection_probability = new JTextField();
    JLabel jLabel9 = new JLabel();
    JTextField jinjection_terminal_nodes_only = new JTextField();
    JLabel jLabel10 = new JLabel();
    JTextField jinjection_root = new JTextField();
    JLabel jLabel11 = new JLabel();
    JTextField jtreeinjectionFolder = new JTextField();
    JLabel jLabel12 = new JLabel();
    JTextField jtreeinjection_probability = new JTextField();
    JLabel jLabel13 = new JLabel();
    JTextField jtreeinjection_min_depth = new JTextField();

    public FW_DESTRUCTOR(Frame parent) {
        super(parent);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jOK.setText("RUN");
        jOK.addActionListener(new FW_DESTRUCTOR_jOK_actionAdapter(this));
        jCancel.setText("Cancel");
        jCancel.addActionListener(new FW_DESTRUCTOR_jCancel_actionAdapter(this));
        jPanel1.setLayout(gridLayout1);
        gridLayout1.setColumns(2);
        gridLayout1.setHgap(2);
        gridLayout1.setRows(7);
        gridLayout1.setVgap(2);
        jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        jLabel1.setText("Result Models Folder Name");
        jLabel2.setText("Input model: 1-curent, 2-build selected, 3- build randomly, 4-build all");
        jLabel3.setText("Number of  models to decompose:");
        jLabel4.setText("New Tree Probubility");
        jLabel5.setText("Min Depth");
        jLabel6.setText("Max Depth");
        jLabel7.setText("Trim Probability");
       
        jLabel7a.setText("Node_Injections: Folder Name");
        jLabel8.setText("Node_Injections: Probabilituy of node injection from Injection Folder");
        jLabel9.setText("Node_Injections: 0-injection to all nodes, 1-injection to terminal nodes");
        jLabel10.setText("ROOT_SUBSTITUTION: 0-none 1- selected object root node to processed object root node");
        jLabel11.setText("SubTree_Injections: Folder Name");
        jLabel12.setText("SubTree_Injections: Probabilituy of injection from SubTreeInjections Folder");
        jLabel13.setText("SubTree_Injections: Min depth of the node to inject subtree");
        ///////////////


        jMutateProbub.setToolTipText("");
        jNmutations.setText("");
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(jOK, null);
        jPanel2.add(jCancel, null);
        jPanel1.add(jLabel1, null);
        jPanel1.add(jResultsFolder, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jRootBlock, null);
        jPanel1.add(jLabel3, null);
        jPanel1.add(jNNmutants, null);
        jPanel1.add(jLabel4, null);
        jPanel1.add(jMutateProbub, null);
        jPanel1.add(jLabel5, null);
        jPanel1.add(jNmutations, null);
        jPanel1.add(jLabel6, null);
        jPanel1.add(jStructuralNmutations, null);
        jPanel1.add(jLabel7, null);
        jPanel1.add(jColorlNmutations, null);
        
        
        /*
        jPanel1.add(jLabel7a, null);
        jPanel1.add(jInjectionFolder, null);
        jPanel1.add(jLabel8, null);
        jPanel1.add(jinjection_probability, null);
        jPanel1.add(jLabel9, null);
        jPanel1.add(jinjection_terminal_nodes_only, null);
        
        jPanel1.add(jLabel11, null);
        jPanel1.add(jtreeinjectionFolder, null);
        jPanel1.add(jLabel12, null);
        jPanel1.add(jtreeinjection_probability, null);
        jPanel1.add(jLabel13, null);
        jPanel1.add(jtreeinjection_min_depth, null);
        
        jPanel1.add(jLabel10, null);
        jPanel1.add(jinjection_root, null);
        */
        initParms();
    }

    void initParms() {

        jResultsFolder.setText("" + resultsFolder);
        jRootBlock.setText("" + rootBlock);
        jNNmutants.setText("" + nnmutants);
        jMutateProbub.setText("" + startProbub);
        jNmutations.setText("" + minDepts);
        jStructuralNmutations.setText("" + maxDepts);
        jColorlNmutations.setText("" + trimProbab);

        
        
        jInjectionFolder.setText("" + injectionFolder);
        jinjection_probability.setText("" + injection_probability);
        jinjection_terminal_nodes_only.setText("" + injection_terminal_nodes_only);
        jinjection_root.setText("" + injection_root);

        jtreeinjectionFolder.setText("" + treeinjectionFolder);
        jtreeinjection_probability.setText("" + treeinjection_probability);
        jtreeinjection_min_depth.setText("" + treeinjection_min_depth);
    }

    void readParams() {
        resultsFolder = jResultsFolder.getText();
        rootBlock = Integer.parseInt(jRootBlock.getText());
        nnmutants = Integer.parseInt(jNNmutants.getText());
        startProbub = Double.parseDouble(jMutateProbub.getText());
        minDepts = Integer.parseInt(jNmutations.getText());
        maxDepts = Integer.parseInt(jStructuralNmutations.getText());
        trimProbab = Double.parseDouble(jColorlNmutations.getText());
        
        
        injection_probability = Double.parseDouble(jinjection_probability.getText());
        injection_terminal_nodes_only = Integer.parseInt(jinjection_terminal_nodes_only.getText());
        injectionFolder = jInjectionFolder.getText();
        injection_root = Integer.parseInt(jinjection_root.getText());

        treeinjectionFolder = jtreeinjectionFolder.getText();;
        treeinjection_probability = Double.parseDouble(jtreeinjection_probability.getText());
        treeinjection_min_depth = Integer.parseInt(jtreeinjection_min_depth.getText());
    }

    void jOK_actionPerformed(ActionEvent e) {
        readParams();

        if (rootBlock == 1) {
            FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
            FW_SetOfBlocks sb = FW_Parm.getSetOfBlocksByName(resultsFolder); //FW_Parm.getCurrentSetOfBlocks();
            if (blk == null) {
                FW_Utils.warning("Select model window please");
                return;
            }
            if (sb == null) {
                FW_Utils.warning(resultsFolder + " models folder not found");
                return;
            }
            FW_BlockInterface block = blk.copy();
            //sb.addBlock(block);
            for (int i = 0; i < nnmutants; i++) {
                block = blk.copy();
                java.util.List<FW_BlockInterface> lls = FW_Parm.getCurrentBuilder().destructor(block, startProbub, minDepts, maxDepts, trimProbab);
                for (FW_BlockInterface b:lls) {
                   sb.addBlock(b);
                } 
            }
            sb.updateView();

        } else if (rootBlock == 2 || rootBlock == 3 || rootBlock == 4) {
            //FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
            FW_SetOfBlocks sb = FW_Parm.getSetOfBlocksByName(resultsFolder);
            FW_SetOfBlocksFrame csb = (FW_SetOfBlocksFrame) FW_Parm.getCurrentSetOfBlocks();
            if (sb == null || csb == null) {
                FW_Utils.warning(resultsFolder + "results folder or current selected folder not found");
                return;
            }
            
            ///////////////////////////////////
            // list of blocks of curent selected folder  to get for further mutations
            FW_BlockInterface block = null;
            java.util.List<FW_BlockInterface> blocks_list = csb.getBlocksList();
            if (blocks_list == null || blocks_list.size() == 0) {
                FW_Utils.warning(" select not empty models folder please");
                return;
            }
            //////////////////////////////////


            int num = 0;
            for (int i = 0; i < nnmutants; i++) {
                // create block tree for further processing
                if (rootBlock == 2) {
                    block = csb.build_Object();
                    if (block == null) {
                        FW_Utils.warning("Can not build model. Block in folder not selected");
                        return;
                    }
                } else if (rootBlock == 3) {
                    block = csb.build_Random_Object();
                    if (block == null) {
                        FW_Utils.warning("Can not build model. Folder is empty.");
                        return;
                    }
                } else if (rootBlock == 4) {
                    int n = num % blocks_list.size();
                    num++;
                    block = csb.build_Object(blocks_list.get(n));
                    if (block == null) {
                        FW_Utils.warning("Can not build model. Folder is empty.");
                        return;
                    }
                }
                FW_BlockInterface tree = block.copy();
                java.util.List<FW_BlockInterface> lls = FW_Parm.getCurrentBuilder().destructor(tree, startProbub, minDepts, maxDepts, trimProbab);
                for (FW_BlockInterface b:lls) {
                   sb.addBlock(b);
                } 
                /////////////////////////////////////
                sb.addBlock(block);
            }
            sb.updateView();
        }

        dispose();
    }

    void jCancel_actionPerformed(ActionEvent e) {
        readParams();
        dispose();
    }
}

class FW_DESTRUCTOR_jOK_actionAdapter implements java.awt.event.ActionListener {

    FW_DESTRUCTOR adaptee;

    FW_DESTRUCTOR_jOK_actionAdapter(FW_DESTRUCTOR adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jOK_actionPerformed(e);
    }
}

class FW_DESTRUCTOR_jCancel_actionAdapter implements java.awt.event.ActionListener {

    FW_DESTRUCTOR adaptee;

    FW_DESTRUCTOR_jCancel_actionAdapter(FW_DESTRUCTOR adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCancel_actionPerformed(e);
    }
}

