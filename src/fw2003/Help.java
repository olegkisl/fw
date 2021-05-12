package fw2003;


import java.util.*;
import java.net.URL;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

//import javax.help.*;
import javax.swing.*;
import javax.swing.text.*;
//import javax.help.Map.ID;


public class Help {
    static int  width =  550;
    static int  height = 450;
    
/*  static final String helpsetName = "knowledgeManager";//"IdeHelp";
  // Main HelpSet & Broker
static     HelpSet mainHS = null;
static     HelpBroker mainHB = null;
static     Help h = null;
 
  public Help() {
    try {
            ClassLoader cl = Help.class.getClassLoader();
            URL url = HelpSet.findHelpSet(cl, helpsetName);
            mainHS = new HelpSet(cl, url);
        } catch (Exception ee) {
            System.out.println ("Help Set " +helpsetName+" not found");
            System.out.println(ee.toString());
            return;
        } catch (ExceptionInInitializerError ex) {
            System.err.println("initialization_error_");
            System.out.println(ex.toString());
            ex.getException().printStackTrace();
            return;
        }
     //    mainHB = mainHS.createHelpBroker();
           mainHB =  new  HelpBroker(mainHS);
  }
 
 */
    static  ActionListener getHelpListener(){
  /*   if(mainHB== null){
       h = new Help();
     }*/
        
        {
            return new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //     String url = "http://java.sun.com";
                        File file = new File("FWhelp.htm");
                        
                        // Convert the file object to a URL
                        URL url = null;
                        
                        url = file.toURL();
                        JEditorPane editorPane = new JEditorPane(url);
                        editorPane.setEditable(false);
                        JScrollPane scrollable = new JScrollPane(editorPane);
                        
                        JFrame frame = new JFrame();
                        frame.getContentPane().add(scrollable, BorderLayout.CENTER);
                        frame.setSize(width, height);
                        frame.setVisible(true);
                    } catch (Exception ue) {ue.printStackTrace();
                    }
                    
                    ;
                }
            };
        }
        
        // return new CSH.DisplayHelpFromSource(mainHB);*/
    }
    
/*
  static public void main(String[] args) {
     try {
       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     }
     catch(Exception e) {
       e.printStackTrace();
     }
 
     Fe frame = new Fe();
     frame.validate();
 
     // Center the frame
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Dimension frameSize = frame.getSize();
     if (frameSize.height > screenSize.height)
       frameSize.height = screenSize.height;
     if (frameSize.width > screenSize.width)
       frameSize.width = screenSize.width;
     frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
 
     frame.setVisible(true);
   }
 
 
 
 
 
}
 
 
 
 
 
 
 
 
 
class Fe extends JFrame {
  static ResourceBundle res = ResourceBundle.getBundle("Genesys.iknow.manager.Res");
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JToggleButton jToggleButton1 = new JToggleButton();
 
  //Construct the frame
  public Fe() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jToggleButton1.setText("jToggleButton1");
    jToggleButton1.addActionListener(TM_Help.getHelpListener());
    this.setSize(new Dimension(400, 300));
    this.setTitle("FE");
    contentPane.add(jToggleButton1, BorderLayout.CENTER);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
 
 */
    
}

