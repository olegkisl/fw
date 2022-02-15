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
public class FW_MUTATOR extends JDialog {

    static String resultsFolder = "Mresults";
    static int rootBlock = 1;
    static int nnmutants = 11;
    static double mutateProbub = 0.5;
    static int nMutations = 2;
    static int nStrMmutations = 2;
    static int nClrMutations = 0;
    static String injectionFolder = "0_BLOCKS";
    static int injection_terminal_nodes_only = 0;
    static double injection_probability = 0.0;
    static int injection_root = 0;
    static String treeinjectionFolder = "0_BLOCKS";
    static double treeinjection_probability = 0.0;
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

    public FW_MUTATOR(Frame parent) {
        super(parent);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jOK.setText("RUN");
        jOK.addActionListener(new FW_MUTATOR_jOK_actionAdapter(this));
        jCancel.setText("Cancel");
        jCancel.addActionListener(new FW_MUTATOR_jCancel_actionAdapter(this));
        jPanel1.setLayout(gridLayout1);
        gridLayout1.setColumns(2);
        gridLayout1.setHgap(2);
        gridLayout1.setRows(13);
        gridLayout1.setVgap(2);
        jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        jLabel1.setText("Result Models Folder Name");
        jLabel2.setText("Input model: 1-curent, 2-build selected, 3- build randomly, 4-build all");
        jLabel3.setText("Number of mutant models to build:");
        jLabel4.setText("Mutations: Mutation Probubility");
        jLabel5.setText("Mutations: N parameters mutations");
        jLabel6.setText("Mutations: N structural mutations");
        jLabel7.setText("Mutations: N color mutations");
        jLabel7a.setText("Injections: Folder Name");
        jLabel8.setText("Injections: Probabilituy of node injection from Injection Folder");
        jLabel9.setText("Injections: 0-injection to all nodes, 1-injection to terminal nodes");
        jLabel10.setText("RootSubstitution: 0-none 1- selected object root node to processed object root node");
        jLabel11.setText("SubTreeInjections: Folder Name");
        jLabel12.setText("SubTreeInjections: Probabilituy of node injection from SubTreeInjections Folder");
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
        jPanel1.add(jLabel7a, null);
        jPanel1.add(jInjectionFolder, null);
        jPanel1.add(jLabel8, null);
        jPanel1.add(jinjection_probability, null);
        jPanel1.add(jLabel9, null);
        jPanel1.add(jinjection_terminal_nodes_only, null);
        jPanel1.add(jLabel10, null);
        jPanel1.add(jinjection_root, null);

        jPanel1.add(jLabel11, null);
        jPanel1.add(jtreeinjectionFolder, null);
        jPanel1.add(jLabel12, null);
        jPanel1.add(jtreeinjection_probability, null);
        initParms();
    }

    void initParms() {

        jResultsFolder.setText("" + resultsFolder);
        jRootBlock.setText("" + rootBlock);
        jNNmutants.setText("" + nnmutants);
        jMutateProbub.setText("" + mutateProbub);
        jNmutations.setText("" + nMutations);
        jStructuralNmutations.setText("" + nStrMmutations);
        jColorlNmutations.setText("" + nClrMutations);

        jInjectionFolder.setText("" + injectionFolder);
        jinjection_probability.setText("" + injection_probability);
        jinjection_terminal_nodes_only.setText("" + injection_terminal_nodes_only);
        jinjection_root.setText("" + injection_root);

        jtreeinjectionFolder.setText("" + treeinjectionFolder);
        jtreeinjection_probability.setText("" + treeinjection_probability);
    }

    void readParams() {
        resultsFolder = jResultsFolder.getText();
        rootBlock = Integer.parseInt(jRootBlock.getText());
        nnmutants = Integer.parseInt(jNNmutants.getText());
        mutateProbub = Double.parseDouble(jMutateProbub.getText());
        nMutations = Integer.parseInt(jNmutations.getText());
        nStrMmutations = Integer.parseInt(jStructuralNmutations.getText());
        nClrMutations = Integer.parseInt(jColorlNmutations.getText());
        injection_probability = Double.parseDouble(jinjection_probability.getText());
        injection_terminal_nodes_only = Integer.parseInt(jinjection_terminal_nodes_only.getText());
        injectionFolder = jInjectionFolder.getText();
        injection_root = Integer.parseInt(jinjection_root.getText());

        treeinjectionFolder = jtreeinjectionFolder.getText();;
        treeinjection_probability = Double.parseDouble(jtreeinjection_probability.getText());
    }

    void jOK_actionPerformed(ActionEvent e) {
        readParams();

        if (rootBlock == 1) {
            FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
            FW_SetOfBlocks sb = FW_Parm.getSetOfBlocksByName(resultsFolder); //FW_Parm.getCurrentSetOfBlocks();
            FW_PalletInterface pl = FW_Parm.getCurrentPallet();
            if (blk == null) {
                FW_Utils.warning("Select model window please");
                return;
            }
            if (sb == null) {
                FW_Utils.warning(resultsFolder + " models folder not found");
                return;
            }
            ////////////////////////////////////
            // list of blocks to inject subtrees to processing blocks
            java.util.List<FW_BlockInterface> treeinjection_blocks = new java.util.ArrayList<FW_BlockInterface>();
            if (treeinjection_probability > 0.000001) {
                FW_SetOfBlocks selected = FW_Parm.getSetOfBlocksByName(treeinjectionFolder);
                if (selected == null) {
                    FW_Utils.warning(treeinjectionFolder + " injection folder not found");
                    return;
                }
                treeinjection_blocks = selected.getBlocksList();
            }
            ////////////////////////////////////         
            java.util.List<FW_BlockInterface> injection_blocks = new java.util.ArrayList<FW_BlockInterface>();
            if (injection_probability > 0.000001) {
                FW_SetOfBlocks selected = FW_Parm.getSetOfBlocksByName(injectionFolder);
                if (selected == null) {
                    FW_Utils.warning(injectionFolder + " models folder not found");
                    return;
                }
                injection_blocks = selected.getBlocksList();
            }
            ///////////////////////////////////

            FW_BlockInterface block = blk.copy();
            sb.addBlock(block);
            for (int i = 0; i < nnmutants; i++) {
                block = blk.copy();
                ////////////////////////////////////
                // injections of subtrees to processing block tree
                if (treeinjection_probability > 0.00001) {
                    FW_Builder.randomSubtreeInjections(block, injection_terminal_nodes_only,
                            treeinjection_probability, treeinjection_blocks);
                }
                ////////////////////////////////////
                if (injection_probability > 0.00001) {
                    FW_Builder.randomInjections(block, injection_terminal_nodes_only,
                            injection_probability, injection_blocks);
                }
                ///////////////////////////////////  
                for (int j = 0; j < nMutations; j++) {
                    if (mutateProbub > FW_Rand.rand01()) {
                        block.mutate();
                    }
                }
                for (int j = 0; j < nStrMmutations; j++) {
                    if (mutateProbub > FW_Rand.rand01()) {
                        block.mutateLarge();
                    }
                }
                for (int j = 0; j < nClrMutations; j++) {
                    if (mutateProbub > FW_Rand.rand01() && (pl != null)) {
                        block.mutatePallet(pl);
                    }
                }

                sb.addBlock(block);
            }
            sb.updateView();

        } else if (rootBlock == 2 || rootBlock == 3 || rootBlock == 4) {
            //FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
            FW_SetOfBlocks sb = FW_Parm.getSetOfBlocksByName(resultsFolder);
            FW_SetOfBlocksFrame csb = (FW_SetOfBlocksFrame) FW_Parm.getCurrentSetOfBlocks();
            FW_PalletInterface pl = FW_Parm.getCurrentPallet();
            if (sb == null || csb == null) {
                FW_Utils.warning(resultsFolder + "results folder or current selected folder not found");
                return;
            }
            ////////////////////////////////////
            // list of blocks to  blocks to processing blocks
            java.util.List<FW_BlockInterface> injection_blocks = new java.util.ArrayList<FW_BlockInterface>();
            if (injection_probability > 0.000001) {
                FW_SetOfBlocks selected = FW_Parm.getSetOfBlocksByName(injectionFolder);
                if (selected == null) {
                    FW_Utils.warning(injectionFolder + " injection folder not found");
                    return;
                }
                injection_blocks = selected.getBlocksList();
            }
            ////////////////////////////////////
            // list of blocks to inject subtrees to processing blocks
            java.util.List<FW_BlockInterface> treeinjection_blocks = new java.util.ArrayList<FW_BlockInterface>();
            if (treeinjection_probability > 0.000001) {
                FW_SetOfBlocks selected = FW_Parm.getSetOfBlocksByName(treeinjectionFolder);
                if (selected == null) {
                    FW_Utils.warning(treeinjectionFolder + " injection folder not found");
                    return;
                }
                treeinjection_blocks = selected.getBlocksList();
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
                ////////////////////////////////////
                // substitute root node in processing block tree
                if (injection_root == 1) {
                    FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
                    if (blk == null) {
                        FW_Utils.warning("Model window not selected");
                        return;
                    }
                    block = FW_Builder.rootInjection(blk, block);
                }
                ////////////////////////////////////
                // injections of subtrees to processing block tree
                if (treeinjection_probability > 0.00001) {
                    FW_Builder.randomSubtreeInjections(block, injection_terminal_nodes_only,
                            treeinjection_probability, treeinjection_blocks);
                }
                ////////////////////////////////////
                // injections of individual blocks to processing block tree
                if (injection_probability > 0.00001) {
                    FW_Builder.randomInjections(block, injection_terminal_nodes_only,
                            injection_probability, injection_blocks);
                }
                /////////////////////////////////// 
                // mutations of processing block tree
                for (int j = 0; j < nMutations; j++) {
                    if (mutateProbub > FW_Rand.rand01()) {
                        block.mutate();
                    }
                }
                for (int j = 0; j < nStrMmutations; j++) {
                    if (mutateProbub > FW_Rand.rand01()) {
                        block.mutateLarge();
                    }
                }
                for (int j = 0; j < nClrMutations; j++) {
                    if (mutateProbub > FW_Rand.rand01() && (pl != null)) {
                        block.mutatePallet(pl);
                    }
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

class FW_MUTATOR_jOK_actionAdapter implements java.awt.event.ActionListener {

    FW_MUTATOR adaptee;

    FW_MUTATOR_jOK_actionAdapter(FW_MUTATOR adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jOK_actionPerformed(e);
    }
}

class FW_MUTATOR_jCancel_actionAdapter implements java.awt.event.ActionListener {

    FW_MUTATOR adaptee;

    FW_MUTATOR_jCancel_actionAdapter(FW_MUTATOR adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCancel_actionPerformed(e);
    }
}
