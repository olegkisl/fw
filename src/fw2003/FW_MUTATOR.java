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
        gridLayout1.setRows(7);
        gridLayout1.setVgap(2);
        jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        jLabel1.setText("Results Folder Name");
        jResultsFolder.setText("mutants");
        jLabel2.setText("Initial object: 1-curent, 2-build new");
        jRootBlock.setText("1");
        jLabel3.setText("Number of mutants");
        jNNmutants.setText("10");
        jLabel4.setText("Mutation Probubility");
        jMutateProbub.setText("0.5");
        jLabel5.setText("N mutations");
        jNmutations.setText("1");
        jLabel6.setText("N structural mutations");
        jLabel7.setText("N color mutations");

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

    }

    void jOK_actionPerformed(ActionEvent e) {
        resultsFolder = jResultsFolder.getText();
        rootBlock = Integer.parseInt(jRootBlock.getText());
        nnmutants = Integer.parseInt(jNNmutants.getText());
        mutateProbub = Double.parseDouble(jMutateProbub.getText());
        nMutations = Integer.parseInt(jNmutations.getText());
        nStrMmutations = Integer.parseInt(jStructuralNmutations.getText());
        nClrMutations = Integer.parseInt(jColorlNmutations.getText());

        if (rootBlock == 1) {    
            FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
            FW_SetOfBlocks sb = FW_Parm.getSetOfBlocksByName(resultsFolder); //FW_Parm.getCurrentSetOfBlocks();
            FW_PalletInterface pl = FW_Parm.getCurrentPallet();
            if (sb == null  || blk==null) {
                return;
            }
            FW_BlockInterface block = blk.copy();
            sb.addBlock(block);
            for (int i = 0; i < nnmutants; i++) {
                block = blk.copy();
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
            
        } else if (rootBlock == 2){
            //FW_BlockInterface blk = FW_Parm.getCurrentBlockInterface();
            FW_SetOfBlocks sb = FW_Parm.getSetOfBlocksByName(resultsFolder); 
            FW_SetOfBlocksFrame csb = (FW_SetOfBlocksFrame)FW_Parm.getCurrentSetOfBlocks();
            FW_PalletInterface pl = FW_Parm.getCurrentPallet();
            if (sb == null  || csb == null  ) {
                return;
            }
            FW_BlockInterface block=null;
            for (int i = 0; i < nnmutants; i++) {
                block = csb.build_Object();
                if(block==null) return;
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
        }
        
       dispose();
    }

    void jCancel_actionPerformed(ActionEvent e) {
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
