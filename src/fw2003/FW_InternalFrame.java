package fw2003;

import javax.swing.JInternalFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.JDesktopPane;
import java.util.*;
import javax.swing.event.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_InternalFrame extends JInternalFrame {
  FW_Desktop desk = null;
  JComponent pane = null;
  int l_win =100;
  int h_win = 100;
  String name= null;
  public FW_InternalFrame(FW_Desktop d, String nm) {
    this();
    desk = d;
   // this.addFocusListener(desk);
    pane = (JComponent) this.getContentPane();
    desk.addFrame(this);
    desk.setFocus(this);///////////////////
    this.setClosable(true);
    this.setMaximizable(true);
    this.setIconifiable(true);
    this.setResizable(true);
    desk.add(this);
    this.setTitle(nm);
    name =nm;
    this.setFrameIcon(null);
    setBounds((d.nn()*5)%100,(d.nn()*5)%100, l_win,h_win);
   // show();
  }

  public FW_InternalFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
      public void internalFrameClosing(InternalFrameEvent e) {
        this_internalFrameClosing(e);
      }
      public void internalFrameActivated(InternalFrameEvent e) {
        this_internalFrameActivated(e);
      }
      public void internalFrameDeactivated(InternalFrameEvent e) {
       this_internalFrameDeactivated(e);
     }
    });
  }

  void this_internalFrameClosing(InternalFrameEvent e) {
    desk.removeFrame(this);
    this.dispose();
  }

  void this_internalFrameActivated(InternalFrameEvent e) {
    desk.setFocus(this);
  }

  void this_internalFrameDeactivated(InternalFrameEvent e) {
     desk.setFocus(null);
  }




}
