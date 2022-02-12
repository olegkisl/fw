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
 *
 * @author Oleg Kislyuk
 * @version 3.0
 */
public class FW_BlockPalletEditor extends javax.swing.JPanel {

    FW_PalletEditorInterface block;
    Vector v = new Vector();
    Vector v_new = new Vector();
    Vector v_old = new Vector();
    Vector v_to_restore = new Vector();
    int size = 0;

    void set_init() {
        java.util.List<java.awt.Color> cc = block.getColorsFromObject();
        size = cc.size();
        for (int i = 0; i < size; i++) {
            v_to_restore.add(cc.get(i));
            v_new.add(cc.get(i));
            v_old.add(cc.get(i));
        }
    }

    void restore() {
        v_old.clear();
        for (int i = 0; i < size; i++) {
            v_old.add(v_to_restore.get(i));
        }
        block.getColorsToObject(v_old);
    }

    void new_to_old() {
        v_old.clear();
        for (int i = 0; i < size; i++) {
            v_old.add(v_new.get(i));
        }
        block.getColorsToObject(v_old);
    }

    void old_to_new() {
        v_new.clear();
        for (int i = 0; i < size; i++) {
            v_new.add(v_old.get(i));
        }
    }
    BorderLayout borderLayout1 = new BorderLayout();
    JList colorList_old = new JList();
    JList colorList_new = new JList();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JButton saveColors = new JButton();
    JButton restoreColor = new JButton();
    JButton renew = new JButton();
    JButton editColor = new JButton();

    public FW_BlockPalletEditor(FW_PalletEditorInterface blk) {
        // super(d,name);
        block = blk;
        try {
            set_init();
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // this.pack();
        show();
    }

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        saveColors.setText("put new colors");
        saveColors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putColors_actionPerformed(e);
            }
        });
        restoreColor.setText("restore colors");
        restoreColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restoreColors_actionPerformed(e);
            }
        });
        renew.setText("left->right");
        renew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                old2new_actionPerformed(e);
            }
        });

        editColor.setText("edit selected color");
        editColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_color_actionPerformed(e);
            }
        });

        JScrollPane listScroller_1 = new JScrollPane(colorList_old);
        JScrollPane listScroller_2 = new JScrollPane(colorList_new);

        listScroller_1.setPreferredSize(new Dimension(250, 100));
        listScroller_2.setPreferredSize(new Dimension(250, 100));
        //colorList_old.setBorder(BorderFactory.createEtchedBorder());
       colorList_old.setVisibleRowCount(5);
        colorList_new.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorList_new.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //colorList_old.setVisibleRowCount(5);
        this.add(jPanel2, BorderLayout.CENTER);
        jPanel2.add(listScroller_1, null);
        jPanel2.add(listScroller_2, null);
        this.add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(saveColors, null);
        jPanel1.add(editColor, null);
        jPanel1.add(restoreColor, null);
        jPanel1.add(renew, null);


        colorList_old.setListData(v_old);
        colorList_new.setListData(v_new);

        Renderer rend = new Renderer(false);
        rend.setPreferredSize(new Dimension(100, 20));
        colorList_old.setCellRenderer(rend);

        Renderer rend1 = new Renderer(true);
        rend1.setPreferredSize(new Dimension(100, 20));
        colorList_new.setCellRenderer(rend1);


    }

    void putColors_actionPerformed(ActionEvent e) {
        new_to_old();
        colorList_old.setListData(v_old);
        this.validate();
    }

    void restoreColors_actionPerformed(ActionEvent e) {
        restore();
        colorList_old.setListData(v_old);
        this.validate();
    }

    void old2new_actionPerformed(ActionEvent e) {
        old_to_new();
        colorList_new.setListData(v_new);
        this.validate();
    }

    void edit_color_actionPerformed(ActionEvent e) {
        int nn = colorList_new.getSelectedIndex();
        if (nn < 0 || nn > size) {
            return;
        }
        Color c = JColorChooser.showDialog(this, "Pick a Color", Color.white);
        if (c != null) {
            v_new.set(nn, c);
            colorList_new.setListData(v_new);
        }
        this.validate();
    }

    //////////////////////
    class Renderer extends JLabel
            implements ListCellRenderer {

        boolean selection = true;

        public Renderer(boolean select) {
            selection = select;
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
            Color cc = (Color) value;
            setBackground(cc);
            int nnn = cc.getRed() + cc.getBlue() + cc.getGreen();
            if (nnn < 200) {
                setForeground(Color.white);
            } else {
                setForeground(Color.black);
            }
            if (selection) {
                if (isSelected) {
                    setText("Selected");
                } else {
                    setText("---");
                }
            }
            return this;
        }
    }
}
