package fw2003;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.event.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_ImageFrame extends FW_InternalFrame{
  int l= 100, h=100;
  FW_ImagePanel ip = null;
  String title="";

  public FW_ImageFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  // to implement at ui
  public FW_ImageFrame(FW_Desktop d, String name) {
    super(d, name);
  }

  public FW_ImageFrame(FW_Desktop d, String name, int l,int h) {
    super(d, name);
    title = name;
    this.l =l;
    this.h=h;
    ip = new FW_ImagePanel(l,h);
    try {
     jbInit();
   }
   catch(Exception e) {
     e.printStackTrace();
   }

    setBcgrClr( FW_ImagePanel.defaultColor);
    this.setSize(new Dimension(300,300));
  //  this.setMaximumSize(new Dimension(300,300));
   // this.setMinimumSize(new Dimension(30,30));

    //this.pack();
    show();
  }

  public FW_ImageFrame(FW_Desktop d, String name, BufferedImage img) {
    super(d, name);
    this.l =img.getWidth();
    this.h=img.getHeight();
    ip = new FW_ImagePanel(l,h,img);
    try {
     jbInit();
   }
   catch(Exception e) {
     e.printStackTrace();
   }

    setBcgrClr(Color.black);
    this.pack();
    show();
  }




  private void jbInit() throws Exception {
    bckgrColor.setText("Bcgr");
    bckgrColor.addActionListener(new FW_ImageFrame_bckgrColor_actionAdapter(this));
    clearButton.setText("Clear");
    clearButton.addActionListener(new FW_ImageFrame_clearButton_actionAdapter(this));
    paintButton.setText("Paint");
    paintButton.addActionListener(new FW_ImageFrame_paintButton_actionAdapter(this));
    stopButton.setText("Stop");
    stopButton.addActionListener(new FW_ImageFrame_stopButton_actionAdapter(this));
    undoButton.setText("Undo");
    undoButton.addActionListener(new FW_ImageFrame_undoButton_actionAdapter(this));
    this.setClosable(true);
    this.addInternalFrameListener(new FW_ImageFrame_this_internalFrameAdapter(this));
        jScaleP.setText("+");
        jScaleP.addActionListener(new FW_ImageFrame_jScaleP_actionAdapter(this));
        jScaleM.setText("-");
        jScaleM.addActionListener(new FW_ImageFrame_jScaleM_actionAdapter(this));
        this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
    this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(ip, null);
        buttonsPanel.add(bckgrColor, null);
    buttonsPanel.add(clearButton, null);
    buttonsPanel.add(paintButton, null);
    buttonsPanel.add(stopButton, null);
    buttonsPanel.add(undoButton, null);
        buttonsPanel.add(jScaleP);
        buttonsPanel.add(jScaleM);
    }

  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel buttonsPanel = new JPanel();
  JButton bckgrColor = new JButton();
  JButton clearButton = new JButton();
  JButton paintButton = new JButton();
  JButton stopButton = new JButton();
  JButton undoButton = new JButton();
    JButton jScaleP = new JButton();
    JButton jScaleM = new JButton();
    void bckgrColor_actionPerformed(ActionEvent e) {
    Color b = JColorChooser.showDialog(pane,"Pick a Background Color", Color.white);
    if(b!=null){
     setBcgrClr( b);
    }
  }

  void setBcgrClr(Color b){
     setBackground(b);
     ip.setBackgroundClr(b);
     this.validate();
  }

  void clearButton_actionPerformed(ActionEvent e) {
     ip.clear();
     this.validate();
  }

  void paintButton_actionPerformed(ActionEvent e) {
   if(FW_Parm.getCurrentBlockInterface()==null) return;
   //ip.save();
   FW_Parm.getMacroRecorder().add(ip.x,ip.y,ip.xend,ip.yend, FW_Parm.getCurrentBlockInterface());
   ip.startPaint(FW_Parm.getCurrentBlockInterface());
   ip.requestFocusInWindow();  /// new
  }
  
  void paint_collage(int xmax, int ymax, int xdelta, int ydelta) {
   if(FW_Parm.getCurrentBlockInterface()==null) return;
   ip.startPaint(FW_Parm.getCurrentBlockInterface(), xmax, ymax, xdelta, ydelta);
   ip.requestFocusInWindow();  
  }
  
  void paint_synchronized() {
   if(FW_Parm.getCurrentBlockInterface()==null) return;
   //ip.save();
   //FW_Parm.getMacroRecorder().add(ip.x,ip.y,ip.xend,ip.yend, FW_Parm.getCurrentBlockInterface());
   ip.startPaint_synchronized(FW_Parm.getCurrentBlockInterface());
   ip.requestFocusInWindow();  /// new
  }

  void stopButton_actionPerformed(ActionEvent e) {
    ip.stopPaint();
  }

  void undoButton_actionPerformed(ActionEvent e) {
    ip.undo();
    FW_Parm.getMacroRecorder().undo();
  }

  void this_internalFrameClosing(InternalFrameEvent e) {
    if(ip!= null && ip.g2img!=null)
       ip.g2img.dispose();
  }

    public void jScaleM_actionPerformed(ActionEvent e) {
        showScale(ip.changeScale(0.5));
        ip.invalidate();
        validate();
        repaint();
    }

    public void jScaleP_actionPerformed(ActionEvent e) {
        showScale(ip.changeScale(2.0));
        ip.invalidate();
        validate();
        repaint();
    }

    private void showScale(double s){
        this.setTitle(title + " [Scale = "+s+"; l = "+l+"; h = "+h+"]");
    }
}


class FW_ImageFrame_jScaleP_actionAdapter implements ActionListener {
    private FW_ImageFrame adaptee;
    FW_ImageFrame_jScaleP_actionAdapter(FW_ImageFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jScaleP_actionPerformed(e);
    }
}


class FW_ImageFrame_jScaleM_actionAdapter implements ActionListener {
    private FW_ImageFrame adaptee;
    FW_ImageFrame_jScaleM_actionAdapter(FW_ImageFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jScaleM_actionPerformed(e);
    }
}


class FW_ImageFrame_bckgrColor_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageFrame adaptee;

  FW_ImageFrame_bckgrColor_actionAdapter(FW_ImageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.bckgrColor_actionPerformed(e);
  }
}

class FW_ImageFrame_clearButton_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageFrame adaptee;

  FW_ImageFrame_clearButton_actionAdapter(FW_ImageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.clearButton_actionPerformed(e);
  }
}

class FW_ImageFrame_paintButton_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageFrame adaptee;

  FW_ImageFrame_paintButton_actionAdapter(FW_ImageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.paintButton_actionPerformed(e);
  }
}

class FW_ImageFrame_stopButton_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageFrame adaptee;

  FW_ImageFrame_stopButton_actionAdapter(FW_ImageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.stopButton_actionPerformed(e);
  }
}

class FW_ImageFrame_undoButton_actionAdapter implements java.awt.event.ActionListener {
  FW_ImageFrame adaptee;

  FW_ImageFrame_undoButton_actionAdapter(FW_ImageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.undoButton_actionPerformed(e);
  }
}

class FW_ImageFrame_this_internalFrameAdapter extends javax.swing.event.InternalFrameAdapter {
  FW_ImageFrame adaptee;

  FW_ImageFrame_this_internalFrameAdapter(FW_ImageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void internalFrameClosing(InternalFrameEvent e) {
    adaptee.this_internalFrameClosing(e);
  }
}
