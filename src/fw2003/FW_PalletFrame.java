package fw2003;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_PalletFrame extends FW_InternalFrame
       implements FW_PalletInterface {
  Vector v= new Vector();
  BorderLayout borderLayout1 = new BorderLayout();

  JList colorList = new JList();
  JPanel jPanel1 = new JPanel();
  JButton addColor = new JButton();
  JButton deleteColor = new JButton();
  JButton deleteAll = new JButton();
  public FW_PalletFrame(FW_Desktop d, String name) {
    super(d,name);
    v.add(new Color(250,0,0));
    v.add(new Color(0,250,0));
    v.add(new Color(0,0,250));

    try {
     jbInit();
   }
   catch(Exception e) {
     e.printStackTrace();
   }
    this.pack();
    show();
  }

  public FW_PalletFrame(FW_Desktop d, String name, Vector vv) {
    super(d,name);
    v = vv;
    try {
     jbInit();
   }
   catch(Exception e) {
     e.printStackTrace();
   }
    this.pack();
    show();
  }


  public FW_PalletFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    addColor.setText("add color");
    addColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addColor_actionPerformed(e);
      }
    });
    deleteColor.setText("delete color");
    deleteColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteColor_actionPerformed(e);
      }
    });
    deleteAll.setText("delete all");
    deleteAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteAll_actionPerformed(e);
      }
    });

    JScrollPane listScroller = new JScrollPane(colorList);
    listScroller.setPreferredSize(new Dimension(250, 80));
  //  colorList.setBorder(BorderFactory.createEtchedBorder());
     colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 //   colorList.setVisibleRowCount(5);

    this.getContentPane().add(listScroller, BorderLayout.CENTER);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(addColor, null);
    jPanel1.add(deleteColor, null);
    jPanel1.add(deleteAll, null);



    colorList.setListData(v);

    Renderer rend = new Renderer();
    rend.setPreferredSize(new Dimension(100,20));
    colorList.setCellRenderer(rend);


  }

  void deleteColor_actionPerformed(ActionEvent e) {
    Object c = colorList.getSelectedValue();
    if(c!=null){
      v.remove(c);
      colorList.setListData(v);
      this.validate();
    }
  }

  void addColor_actionPerformed(ActionEvent e) {
    Color c = JColorChooser.showDialog(pane,"Pick a Color", Color.white);
    if(c!=null){
      v.add(c);
      colorList.setListData(v);
      this.validate();
    }

  }

  void deleteAll_actionPerformed(ActionEvent e) {
          v.clear();
          colorList.setListData(v);
          this.validate();
  }

 public Color getColor(){
   int nn = v.size();
   if(nn == 0)
     return Color.white;
   else{
     return (Color) v.get(FW_Rand.rand(nn));
   }

 }

 public java.util.List getColorsList(){
 return v;
 }

 //////////////////////
 class Renderer extends JLabel
                       implements ListCellRenderer {

    public Renderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    public Component getListCellRendererComponent(
                                       JList list,
                                       Object value,
                                       int index,
                                       boolean isSelected,
                                       boolean cellHasFocus) {
        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
       Color cc =(Color)value;
       setBackground(cc);
       int nnn = cc.getRed() + cc.getBlue() + cc.getGreen();
       if(nnn<200)
         setForeground (Color.white);
       else
         setForeground (Color.black);
        if (isSelected) {
            setText("Selected");
        } else {
           setText("---");
        }

        return this;
    }

}



}