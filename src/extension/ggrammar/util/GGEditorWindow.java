package extension.ggrammar.util;
import  extension.ggrammar.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GGEditorWindow extends JPanel{
  java.util.List ls=null;

  public GGEditorWindow(java.util.List ls) {
    super();
    this.ls = ls;
    jPanel1 = new GGEditor(ls);
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  public GGEditorWindow() {
    super();
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    deleteAll.setText("Delete All");
    deleteAll.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteAll_actionPerformed(e);
      }
    });
    this.setLayout(gridBagLayout1);
    deleteLast.setText("Delete Last");
    deleteLast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteLast_actionPerformed(e);
      }
    });
    jComboBox.setEditor(null);
    jComboBox.addItem("Line");
    jComboBox.addItem("Quad");
    jComboBox.addItem("Cubic");
    jComboBox.setSelectedIndex(0);
    jComboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jComboBox_actionPerformed(e);
      }
    });
    jColor.setText("Set Color");
    jColor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jColor_actionPerformed(e);
      }
    });
    jColorSample.setEditable(false);
    jColorSample.setText("");
    this.add(jComboBox, new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0
                                               , GridBagConstraints.CENTER,
                                               GridBagConstraints.HORIZONTAL,
                                               new Insets(24, 11, 0, 27), 63, 4));
    this.add(deleteLast, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(24, 12, 0, 0), 24, 0));
    this.add(jPanel1, new GridBagConstraints(0, 0, 4, 1, 1.0, 1.0
                                             , GridBagConstraints.CENTER,
                                             GridBagConstraints.BOTH,
                                             new Insets(27, 43, 0, 62), 281,
                                             200));
    this.add(deleteAll, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                               , GridBagConstraints.CENTER,
                                               GridBagConstraints.NONE,
                                               new Insets(24, 11, 0, 0), 34, 0));
    this.add(jColorSample, new GridBagConstraints(2, 2, 2, 1, 1.0, 0.0
                                                  , GridBagConstraints.WEST,
                                                  GridBagConstraints.HORIZONTAL,
                                                  new Insets(27, 58, 19, 64),
                                                  101, 4));
    this.add(jColor, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0
                                            , GridBagConstraints.CENTER,
                                            GridBagConstraints.NONE,
                                            new Insets(27, 60, 19, 0), 32, 1));
    jPanel1.setBackground(Color.black);
    jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());

    jColorSample.setBackground(currentColor);
    ((GGEditor)jPanel1).setColor(currentColor);
  }

  JButton deleteAll = new JButton();
  JPanel jPanel1 = new JPanel();
  JButton deleteLast = new JButton();
  JComboBox jComboBox = new JComboBox();
  JButton jColor = new JButton();
  JTextField jColorSample = new JTextField();
  public void deleteAll_actionPerformed(ActionEvent e) {
    ((GGEditor)jPanel1).deleteAll();

  }

  public void deleteLast_actionPerformed(ActionEvent e) {
     ((GGEditor)jPanel1).deleteLast();
  }

  public void jComboBox_actionPerformed(ActionEvent e) {
      int nn = jComboBox.getSelectedIndex();
      ((GGEditor)jPanel1).setCurveType(nn+1);
  }

  Color currentColor = Color.green;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  public void jColor_actionPerformed(ActionEvent e) {
    Color newColor = JColorChooser.showDialog
        (this, "Choose Color", currentColor);
    currentColor =  newColor;
    jColorSample.setBackground(currentColor);
    ((GGEditor)jPanel1).setColor(currentColor);
  }

}
