package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class FW_BlockFrame
    extends FW_InternalFrame {
  FW_BlockInterface block;


  JTextField jDescription = new JTextField();
  JButton Save = new JButton();
  JButton jSaveParts = new JButton();
  JButton jMutateColor = new JButton();
  JButton jMutate = new JButton();
  JPanel ppp = new JPanel();
  JButton jEdit = new JButton();
  JButton jPrint = new JButton();
  JButton jMutateLarge = new JButton();

  public FW_BlockFrame(FW_Desktop d, FW_BlockInterface bl) {
    super(d, bl.getName());
    block = bl;
    try {
      jbInit();
      jDescription.setText(block.getName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.pack();
    show();
  }

  public FW_BlockFrame() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    jDescription.setText("Block");
    Save.setText("Save");
    Save.addActionListener(new FW_BlockFrame_Save_actionAdapter(this));
    jSaveParts.setText("Decomposition");
    jSaveParts.addActionListener(new FW_BlockFrame_jSaveParts_actionAdapter(this));
    jMutateColor.setText("MutateColor");
    jMutateColor.addActionListener(new FW_BlockFrame_jMutateColor_actionAdapter(this));
    jMutate.setText("Mutate");
    jMutate.addActionListener(new FW_BlockFrame_jMutate_actionAdapter(this));
    jEdit.setText("Edit");
    if(!block.isEditable())
        jEdit.setEnabled(false);
    jEdit.addActionListener(new FW_BlockFrame_jEdit_actionAdapter(this));
    jPrint.setText("View IP Tree");
    jPrint.addActionListener(new FW_BlockFrame_jPrint_actionAdapter(this));
    jMutateLarge.setText("Mutate(Large)");
    jMutateLarge.addActionListener(new FW_BlockFrame_jMutateLarge_actionAdapter(this));
    this.getContentPane().add(ppp, BorderLayout.CENTER);
    ppp.setLayout(new GridLayout(8,1));
    ppp.add(jDescription);
    ppp.add(jMutate);
    ppp.add(jMutateLarge, null);
    ppp.add(jMutateColor);
    ppp.add(Save);
    ppp.add(jSaveParts);
    ppp.add(jEdit, null);
    ppp.add(jPrint, null);
  }

  public FW_BlockInterface getBlock(){
  return block;
}

  void jMutateColor_actionPerformed(ActionEvent e) {
    FW_PalletInterface pl = FW_Parm.getCurrentPallet();
    if (pl != null) {
      block.mutatePallet(pl);
    }
  }

  void jMutate_actionPerformed(ActionEvent e) {
    block.mutate();
  }

  void Save_actionPerformed(ActionEvent e) {
    FW_SetOfBlocks sb = FW_Parm.getCurrentSetOfBlocks();
    if (sb == null) {
      return;
    }
    FW_BlockInterface bc = block.copy();
    bc.setName(jDescription.getText());
    sb.addBlock(bc);
    sb.updateView();
  }

  void jSaveParts_actionPerformed(ActionEvent e) {
    double destruction_level = 0.3;
    FW_SetOfBlocks sb = FW_Parm.getCurrentSetOfBlocks();
    if (sb == null) {
      return;
    }
    destruction_level = FW_Parm.getDestructionProb();
    FW_Builder builder = FW_Parm.getCurrentBuilder();
    if (builder == null) {
      return;
    }
    FW_BlockInterface bc = block.copy();
    bc.setName(jDescription.getText());
    java.util.List ls = builder.destruct(bc, destruction_level);

    for (Iterator i = ls.iterator(); i.hasNext(); ) {
      FW_BlockInterface item = (FW_BlockInterface) i.next();
      sb.addBlock(item);
    }
    sb.updateView();
  }

  void jPrint_actionPerformed(ActionEvent e) {
    FW_ImageProducerView frame = new  FW_ImageProducerView(block);
      frame.pack();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    frameSize.height=frameSize.height+10;
    frameSize.width =frameSize.width+10;
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
 // ((Block_Prototype) block).print();
  }

  void jEdit_actionPerformed(ActionEvent e) {
      if(!block.isEditable())
       return;
     JFrame frame = new JFrame();
     JPanel contentPane = (JPanel) frame.getContentPane();
     contentPane.setLayout(new BorderLayout());
     contentPane.add(block.getEditor(), java.awt.BorderLayout.CENTER);
     frame.setTitle("Editor of "+block.getName());
     frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
     frame.pack();
     frame.setVisible(true);
   }


  void jMutateLarge_actionPerformed(ActionEvent e) {
     block.mutateLarge();
  }
}

class FW_BlockFrame_jMutateColor_actionAdapter
    implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_jMutateColor_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMutateColor_actionPerformed(e);
  }
}

class FW_BlockFrame_jMutate_actionAdapter
    implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_jMutate_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMutate_actionPerformed(e);
  }
}

class FW_BlockFrame_Save_actionAdapter
    implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_Save_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Save_actionPerformed(e);
  }
}

class FW_BlockFrame_jSaveParts_actionAdapter
    implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_jSaveParts_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveParts_actionPerformed(e);
  }
}

class FW_BlockFrame_jPrint_actionAdapter implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_jPrint_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPrint_actionPerformed(e);
  }
}

class FW_BlockFrame_jEdit_actionAdapter implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_jEdit_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jEdit_actionPerformed(e);
  }
}

class FW_BlockFrame_jMutateLarge_actionAdapter implements java.awt.event.ActionListener {
  FW_BlockFrame adaptee;

  FW_BlockFrame_jMutateLarge_actionAdapter(FW_BlockFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMutateLarge_actionPerformed(e);
  }
}
