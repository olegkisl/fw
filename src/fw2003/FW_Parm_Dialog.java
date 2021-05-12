package fw2003;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_Parm_Dialog extends JDialog {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton jOK = new JButton();
  JButton jCancel = new JButton();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jBuildDepth = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jBrightness = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField jDestructionP = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jMutateProbub = new JTextField();
  JLabel jLabel5 = new JLabel();
  JTextField jDrawRepetition = new JTextField();
  JLabel jLabel6 = new JLabel();
  JTextField jSliceForMutation = new JTextField();
  JLabel jLabel7 = new JLabel();
  JTextField jNonterminalProbability = new JTextField();
  JLabel jLabel8 = new JLabel();
  JTextField jRecombinationProbability = new JTextField();
  JLabel jLabel9 = new JLabel();
  JTextField jPaintType = new JTextField();
  JLabel jLabel10 = new JLabel();
  JTextField jPaintMode = new JTextField();

  public FW_Parm_Dialog(Frame parent) {
    super(parent);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jOK.setText("OK");
    jOK.addActionListener(new FW_Parm_Dialog_jOK_actionAdapter(this));
    jCancel.setText("Cancel");
    jCancel.addActionListener(new FW_Parm_Dialog_jCancel_actionAdapter(this));
    jPanel1.setLayout(gridLayout1);
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(2);
    gridLayout1.setRows(10);
    gridLayout1.setVgap(2);
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jLabel1.setText("Maximum Depth of Image Producer");
    jBuildDepth.setText("5");
    jLabel3.setText("Destruction Probubility in IPF inference");
    jDestructionP.setText("0.5");
    jMutateProbub.setText("0.5");
    jLabel5.setText("Drawing Density");
    jLabel2.setText("Brightness");
    jLabel6.setText("Mutation Slice");
    jLabel7.setText("Nonterminal Probability");
    jLabel8.setText("Recombination Mutations Probability");
    jLabel9.setText("Image Antialiasing(1-9)");
    jLabel10.setText("0-default,1-saveTrans,2-useTrans");
    jBrightness.setText("1.0");
    jBrightness.addActionListener(new FW_Parm_Dialog_jBrightness_actionAdapter(this));
    jLabel4.setText("Mutation Probubility");
    jMutateProbub.setToolTipText("");

    jDrawRepetition.setText("");
    jDrawRepetition.addActionListener(new FW_Parm_Dialog_jDrawRepetition_actionAdapter(this));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jOK, null);
    jPanel2.add(jCancel, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jBuildDepth, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jBrightness, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(jDestructionP, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jMutateProbub, null);
    jPanel1.add(jLabel5, null);
    jPanel1.add(jDrawRepetition, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jSliceForMutation, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jNonterminalProbability, null);
    jPanel1.add(jLabel8, null);
    jPanel1.add(jRecombinationProbability, null);
    jPanel1.add(jLabel9, null);
    jPanel1.add(jPaintType, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jPaintMode, null);
    initParms();
  }

  void initParms(){
   jBuildDepth.setText(""+FW_Parm.getMaxBlockDepth());
   jBrightness.setText(""+FW_Parm.getBritness());
   jDestructionP.setText(""+FW_Parm.getDestructionProb());
   jMutateProbub.setText(""+FW_Parm.getMutateProb());
   jDrawRepetition.setText(""+FW_Parm.getDrawRepetition());
   jSliceForMutation.setText(""+FW_Parm.getSliceForMutation());
   jNonterminalProbability.setText(""+FW_Parm.getNonterminalProbability());
   jRecombinationProbability.setText(""+FW_Parm.getRecombinationMutationProbability());
   jPaintType.setText(""+FW_Parm.getPaintType());
   jPaintMode.setText(""+FW_Parm.getPaintMode());
  }

  void jBrightness_actionPerformed(ActionEvent e) {

  }

  void jOK_actionPerformed(ActionEvent e) {
  double d;
  int n;
   n = Integer.parseInt( jBuildDepth.getText());
   FW_Parm.setMaxBlockDepth(n);

   d = Double.parseDouble( jBrightness.getText());
   FW_Parm.setBriteness(d);

   d = Double.parseDouble( jDestructionP.getText());
   FW_Parm.setDestructionProb(d);

   d = Double.parseDouble( jMutateProbub.getText());
   FW_Parm.setMutateProb(d);

   int nbn = Integer.parseInt( jDrawRepetition.getText());
   FW_Parm.setDrawRepetition(nbn);

   n = Integer.parseInt( jSliceForMutation.getText());
   FW_Parm.setSliceForMutation(n);

   d = Double.parseDouble( jNonterminalProbability.getText());
   FW_Parm.setNonterminalProbability(d);

   d = Double.parseDouble( jRecombinationProbability.getText());
   FW_Parm.setRecombinationMutationProbability(d);

   n = Integer.parseInt( jPaintType.getText());
   FW_Parm.setPaintType(n);

   n = Integer.parseInt( jPaintMode.getText());
   FW_Parm.setPaintMode(n);

   dispose();
  }

  void jCancel_actionPerformed(ActionEvent e) {
  dispose();
  }

  void jDrawRepetition_actionPerformed(ActionEvent e) {

  }

}

class FW_Parm_Dialog_jBrightness_actionAdapter implements java.awt.event.ActionListener {
  FW_Parm_Dialog adaptee;

  FW_Parm_Dialog_jBrightness_actionAdapter(FW_Parm_Dialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jBrightness_actionPerformed(e);
  }
}

class FW_Parm_Dialog_jOK_actionAdapter implements java.awt.event.ActionListener {
  FW_Parm_Dialog adaptee;

  FW_Parm_Dialog_jOK_actionAdapter(FW_Parm_Dialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jOK_actionPerformed(e);
  }
}

class FW_Parm_Dialog_jCancel_actionAdapter implements java.awt.event.ActionListener {
  FW_Parm_Dialog adaptee;

  FW_Parm_Dialog_jCancel_actionAdapter(FW_Parm_Dialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancel_actionPerformed(e);
  }
}

class FW_Parm_Dialog_jDrawRepetition_actionAdapter implements java.awt.event.ActionListener {
  FW_Parm_Dialog adaptee;

  FW_Parm_Dialog_jDrawRepetition_actionAdapter(FW_Parm_Dialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDrawRepetition_actionPerformed(e);
  }
}
