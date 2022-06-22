package fw2003;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author Oleg Kislyuk
 * @version 3.0
 */
public class FW_COLLAGE extends JDialog {

    static String endwith = "";
    static String xmax = ""; // coordinates and size of collage elements
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JButton jOK = new JButton();
    JButton jCancel = new JButton();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel = new JLabel("model name postfix");
    JTextField jEndwith = new JTextField();
    JLabel jLabel1 = new JLabel("x y sizex sizey repeted coordinates");
    JTextField jxmax = new JTextField();

    public static int[] parser(String str) {
        int[] result = new int[0];
        try {
            String[] arr = str.split("\\s+", 0);
            if (arr.length == 0) {
                return result;
            }
            result = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
                result[i] = Integer.parseInt(arr[i]);
                System.out.println(result[i]);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return new int[0];
        }
        return result;
    }

    public FW_COLLAGE(Frame parent) {
        super(parent);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jOK.setText("RUN");
        jOK.addActionListener(new FW_COLLAGE_jOK_actionAdapter(this));
        jCancel.setText("Cancel");
        jCancel.addActionListener(new FW_COLLAGE_jCancel_actionAdapter(this));
        jPanel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setHgap(2);
        gridLayout1.setRows(4);
        gridLayout1.setVgap(2);
        jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        //jLabel1.setText("model name postfix");
        //jEndwith.setText("");

        ///////////////

        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(jOK, null);
        jPanel2.add(jCancel, null);
        jPanel1.add(jLabel, null);
        jPanel1.add(jEndwith, null);
        jPanel1.add(jLabel1, null);
        jPanel1.add(jxmax, null);


        initParms();
    }

    void initParms() {
        jEndwith.setText("" + endwith);
        jxmax.setText("" + xmax);
    }
    
    void saveParms() {
       endwith = jEndwith.getText();
       xmax = (jxmax.getText());
    }

    void jOK_actionPerformed(ActionEvent e) {
        saveParms();
        try {
            //System.out.println("1>>>>" );
            FW_SetOfBlocks sb = FW_Parm.getCurrentSetOfBlocks();
            if (sb == null) {
                return;
            }
            //System.out.println("2>>>>" );
            FW_ImageFrame f = (FW_ImageFrame) FW_Parm.getCurrentImageFrame();
            if (f == null) {
                return;
            }
            //System.out.println("3>>>>" );

            int[] rectangles = parser(xmax);
            if (rectangles.length < 4) {
                return;
            }

            java.util.List ls = sb.getBlocksList();
            if (ls.isEmpty()) {
                return;
            }
            //System.out.println("4>>>>" );
            Collections.sort(ls, new CompareBlocks());
            int n_image = 0;
            for (Iterator iter = ls.iterator(); iter.hasNext();) {
                FW_BlockInterface b = (FW_BlockInterface) iter.next();
                if (!b.getName().endsWith(endwith)) {
                    continue;
                }
                FW_BlockInterface bc = b.copy();
                new FW_BlockFrame(FW_Parm.getBlockDesktop(), bc);
                // System.out.println(">>" + b.getName());
                //f.paint_synchronized();
                int dx = rectangles[n_image];
                int dy = rectangles[n_image + 1];
                f.paint_collage(
                        rectangles[n_image * 4 + 2],
                        rectangles[n_image * 4 + 3],
                        rectangles[n_image * 4],
                        rectangles[n_image * 4 + 1]);
                //System.out.println("END>>>>" + b.getName());
                n_image++;
                if (rectangles.length < n_image * 4 + 4) {
                    return;
                }
            }
        } catch (Exception ee) {
            System.out.println(ee);
            ee.printStackTrace(System.out);
        }
        dispose();
        return;
    }

    void jCancel_actionPerformed(ActionEvent e) {
        saveParms();
        dispose();
    }
}

class CompareBlocks implements Comparator {

    public int compare(Object o1, Object o2) {
        FW_BlockInterface b1 = (FW_BlockInterface) o1;
        FW_BlockInterface b2 = (FW_BlockInterface) o2;
        String s1 = b1.getName();
        String s2 = b2.getName();
        return s1.compareTo(s2);
    }
}

class FW_COLLAGE_jOK_actionAdapter implements java.awt.event.ActionListener {

    FW_COLLAGE adaptee;

    FW_COLLAGE_jOK_actionAdapter(FW_COLLAGE adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jOK_actionPerformed(e);
    }
}

class FW_COLLAGE_jCancel_actionAdapter implements java.awt.event.ActionListener {

    FW_COLLAGE adaptee;

    FW_COLLAGE_jCancel_actionAdapter(FW_COLLAGE adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCancel_actionPerformed(e);
    }
}
