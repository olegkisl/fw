
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
public class  FW_COLLAGE extends JDialog {
    static String endwith = "";
    static int xmax = 400; // image size of collage element
    static int ymax = 500;
    static int xinit = 1;  // initial delta
    static int yinit = 1;
    static int xdelta = 401; //  add to current delta
    static int ydelta = 501;
    static int xnn = 3;   // number of images in a row
    //static int ynn= 4;    // number of images in column
    
    
   
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JButton jOK = new JButton();
    JButton jCancel = new JButton();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel = new JLabel("model name postfix");
    JTextField jEndwith = new JTextField();
   
    JLabel jLabel1 = new JLabel("xmax");
    JTextField jxmax = new JTextField();
    JLabel jLabel2 = new JLabel("ymax");
    JTextField jymax = new JTextField();
    
    JLabel jLabel3 = new JLabel("xinit");
    JTextField jxinit = new JTextField();
    JLabel jLabel4 = new JLabel("yinit");
    JTextField jyinit = new JTextField();
    
    JLabel jLabel5 = new JLabel("xdelta");
    JTextField jxdelta = new JTextField();
    JLabel jLabel6 = new JLabel("ydelta");
    JTextField jydelta = new JTextField();
    
    JLabel jLabel7 = new JLabel("NN x");
    JTextField jxnn = new JTextField();
    

    public  FW_COLLAGE(Frame parent) {
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
        gridLayout1.setColumns(2);
        gridLayout1.setHgap(2);
        gridLayout1.setRows(8);
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
        jPanel1.add(jLabel2, null);
        jPanel1.add(jymax, null);
        
        jPanel1.add(jLabel3, null);
        jPanel1.add(jxinit, null);
        jPanel1.add(jLabel4, null);
        jPanel1.add(jyinit, null);
        
        jPanel1.add(jLabel5, null);
        jPanel1.add(jxdelta, null);
        jPanel1.add(jLabel6, null);
        jPanel1.add(jydelta, null);
        
        jPanel1.add(jLabel7, null);
        jPanel1.add(jxnn, null);

        initParms();
    }

    void initParms() {
        jEndwith.setText("" + endwith);
        jxmax.setText("" + xmax);
        jymax.setText("" + ymax);
        jxinit.setText("" + xinit);
        jyinit.setText("" + yinit);
        jxdelta.setText("" + xdelta);
        jydelta.setText("" + ydelta);
        jxnn.setText("" + xnn);

    }
    
    
     void jOK_actionPerformed(ActionEvent e) {
          endwith = jEndwith.getText();
          xmax = Integer.parseInt(jxmax.getText());
          ymax = Integer.parseInt(jymax.getText());
          
          xinit = Integer.parseInt(jxinit.getText());
          yinit = Integer.parseInt(jyinit.getText());
          
          xdelta = Integer.parseInt(jxdelta.getText());
          ydelta = Integer.parseInt(jydelta.getText());
          xnn = Integer.parseInt(jxnn.getText());
          
        

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

                java.util.List ls = sb.getBlocksList();
                if(ls.isEmpty())return;
                //System.out.println("4>>>>" );
                Collections.sort(ls, new CompareBlocks()); 
                int n_image=0;
                for (Iterator iter = ls.iterator(); iter.hasNext();) {
                    FW_BlockInterface b = (FW_BlockInterface) iter.next();
                    if(!b.getName().endsWith(endwith)) continue;
                    FW_BlockInterface bc = b.copy();
                    new FW_BlockFrame(FW_Parm.getBlockDesktop(), bc);
                    System.out.println(">>" + b.getName());
                    //f.paint_synchronized();
                    int dx =xinit+xdelta*(n_image % xnn);
                    int dy =yinit+ydelta*(n_image / xnn);
                    f.paint_collage(xmax, ymax, dx, dy);
                    //System.out.println("END>>>>" + b.getName());
                    n_image++;
                }
            } catch (Exception ee) {
                System.out.println(ee);
                ee.printStackTrace(System.out);
            }
        dispose();
        return;
    }
     

    void jCancel_actionPerformed(ActionEvent e) {
        dispose();
    }
}

class CompareBlocks implements Comparator {
    public int compare(Object o1, Object o2) {
        FW_BlockInterface b1 = (FW_BlockInterface)o1;
        FW_BlockInterface b2 = (FW_BlockInterface)o2;
        String s1 = b1.getName();
        String s2 = b2.getName();
        return  s1.compareTo(s2);
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
