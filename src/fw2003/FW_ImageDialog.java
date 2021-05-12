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

public class FW_ImageDialog extends JDialog {
  JPanel jPanel1 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField xSize = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField ySize = new JTextField();
  JButton jOK = new JButton();
  JButton jCancel = new JButton();
  boolean result = false;
  int x = 400;
  int y = 400;
  public FW_ImageDialog() throws HeadlessException {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jPanel1.setLayout(gridLayout1);
    jPanel1.setNextFocusableComponent(this);
    jPanel1.setToolTipText("");
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(3);
    gridLayout1.setRows(3);
    gridLayout1.setVgap(3);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel1.setText("X Size");
    xSize.setText("400");
    jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel2.setText("Y Size");
    ySize.setText("400");
    jOK.setText("OK");
    jOK.addActionListener(new FW_ImageDialog_jOK_actionAdapter(this));
    jCancel.setText("Cancel");
    jCancel.addActionListener(new FW_ImageDialog_jCancel_actionAdapter(this));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jLabel1, null);
    jPanel1.add(xSize, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(ySize, null);
    jPanel1.add(jOK, null);
    jPanel1.add(jCancel, null);
  }

  void jCancel_actionPerformed(ActionEvent e) {
    result = false;
    dispose();

  }

  void jOK_actionPerformed(ActionEvent e) {
    result = true;
    x = Integer.parseInt(xSize.getText());
    y = Integer.parseInt(ySize.getText());
    dispose();
  }

}

class FW_ImageDialog_jCancel_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageDialog adaptee;

  FW_ImageDialog_jCancel_actionAdapter(FW_ImageDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancel_actionPerformed(e);
  }
}

class FW_ImageDialog_jOK_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageDialog adaptee;

  FW_ImageDialog_jOK_actionAdapter(FW_ImageDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jOK_actionPerformed(e);
  }
}