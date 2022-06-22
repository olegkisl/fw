package fw2003;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Oleg Kislyuk
 * @version 3.1
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;




/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author not attributable
 * @version 7.0
 */


public class FW_infoDialog extends JDialog {

    SpinnerDateModel time = null;
    JPanel panel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanelOK = new JPanel();
    JButton jButtonCancel = new JButton();
    JPanel jParams = new JPanel();

    BorderLayout borderLayout2 = new BorderLayout();
    JTextArea jText = new JTextArea();
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout3 = new BorderLayout();
    Border border5;
    JScrollPane textScroll = new JScrollPane();
    String text = "";


    Border border6;
    Border border1;
    Border border2;

    public FW_infoDialog(Frame frame, String title, boolean modal, String text) {
        super(frame, title, modal);
        this.text = text;
        try {
            jbInit();
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void jbInit() throws Exception {


        jButtonCancel.setText("Exit");
        jButtonCancel.addActionListener(new
                FW_infoDialog_jButtonCancel_actionAdapter(this));

        jParams.setBackground(Color.lightGray);

//   panel1.setMinimumSize(new Dimension(454, 354));
    //    panel1.setPreferredSize(new Dimension(454, 354));
//  jText.setBorder(BorderFactory.createLoweredBevelBorder());
        jText.setBorder(BorderFactory.createLoweredBevelBorder());



        jText.setText(text);
        textScroll.setMinimumSize(new Dimension(300, 450));
        textScroll.setPreferredSize(new Dimension(304, 454));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.
                                      DISPOSE_ON_CLOSE);
        getContentPane().add(panel1);
        panel1.setLayout(new BorderLayout());
        panel1.add(jPanelOK, BorderLayout.SOUTH);
        jPanelOK.add(jButtonCancel, null);
        panel1.add(jParams, BorderLayout.CENTER);

        jParams.setLayout(new BorderLayout());
        jText.setLineWrap(true);
        jText.setEditable(true);
        jText.setWrapStyleWord(true);
// mailTextArea.setBorder( BorderFactory.createEmptyBorder(4,4,4,4));

        textScroll.getViewport().add(jText, null);
        jParams.add(textScroll, BorderLayout.CENTER);

// jText.addFocusListener(TM_Parm.getActionsSupport());
// jSubject.addFocusListener(TM_Parm.getActionsSupport());




    }


    void jButtonCancel_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}


class FW_infoDialog_jButtonCancel_actionAdapter implements ActionListener {
    private FW_infoDialog adaptee;
    FW_infoDialog_jButtonCancel_actionAdapter(FW_infoDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCancel_actionPerformed(e);
    }
}
