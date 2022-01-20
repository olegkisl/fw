package fw2003;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class mainWindow extends JFrame {
  JPanel contentPane;
  JSlider jSlider =new JSlider(0,100,50);
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  ImageIcon imageMain;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JMenu jMenuEdit = new JMenu();
  JMenu jMenu2 = new JMenu();
  JMenuItem newImage = new JMenuItem();
  JMenuItem newPallet = new JMenuItem();
  JMenuItem newBlock = new JMenuItem();
  JMenu jMenu3 = new JMenu();
  JMenuItem getImage = new JMenuItem();
  JMenuItem openPallet = new JMenuItem();
  JMenu jMenu4 = new JMenu();
  JMenuItem saveImage = new JMenuItem();
  JMenuItem saveAllImages = new JMenuItem();
  JMenuItem savePallet = new JMenuItem();
  JMenuItem blockSetOpen = new JMenuItem();
  JMenuItem jMenuItem12 = new JMenuItem();
  JSplitPane jSplitPane1 = new JSplitPane();
  JSplitPane jSplitPane2 = new JSplitPane();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JPanel jBlocks = new JPanel();
  JPanel jPallet = new JPanel();

  FW_Desktop blockSetsDesktop = new FW_Desktop();
  FW_Desktop blocksDesktop = new FW_Desktop();
  FW_Desktop palletsDesktop = new FW_Desktop();
  FW_Desktop imagesDesktop = new FW_Desktop();

  FW_MacroRecoder macroRecoder = new FW_MacroRecoder();

  //Construct the frame
  public mainWindow() {
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
    image1 = new ImageIcon(fw2003.mainWindow.class.getResource("openFile.png"));
    image2 = new ImageIcon(fw2003.mainWindow.class.getResource("closeFile.png"));
    image3 = new ImageIcon(fw2003.mainWindow.class.getResource("help.png"));
    imageMain = new ImageIcon(fw2003.mainWindow.class.getResource("main.png"));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(840, 640));
    this.setTitle("IPF Editor");
    this.setIconImage(imageMain.getImage());
    statusBar.setText(" ");
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new mainWindow_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new mainWindow_jMenuHelpAbout_ActionAdapter(this));
    jButton1.setIcon(image1);
    jButton1.setToolTipText("Open File");
    jButton2.setIcon(image2);
    jButton2.setToolTipText("Close File");
    jButton3.setIcon(image3);
    jButton3.setToolTipText("Help");
        jMenuEdit.setText("Edit");
    jMenu2.setText("New");
    newImage.setText("Image Window");
    newImage.addActionListener(new mainWindow_newImage_actionAdapter(this));
    newPallet.setText("Pallet");
    newPallet.addActionListener(new mainWindow_newPallet_actionAdapter(this));
    newBlock.setText("Image Producer Node");
    newBlock.addActionListener(new mainWindow_newBlock_actionAdapter(this));
    jMenu3.setText("Open");
    getImage.setText("Image");
    getImage.addActionListener(new mainWindow_getImage_actionAdapter(this));
    openPallet.setText("Pallet");
    openPallet.addActionListener(new mainWindow_openPallet_actionAdapter(this));
    jMenu4.setText("Save");
    saveImage.setText("Image(JPEG)");
    saveImage.addActionListener(new mainWindow_saveImage_actionAdapter(this));
    saveAllImages.setText("BuildAndSaveImages(JPEG)");
    saveAllImages.addActionListener(new mainWindow_saveAllImages_actionAdapter(this));
    savePallet.setText("Pallet");
    savePallet.addActionListener(new mainWindow_savePallet_actionAdapter(this));
    blockSetOpen.setText("Image Producer Factory");
    blockSetOpen.addActionListener(new mainWindow_blockSetOpen_actionAdapter(this));
    jMenuItem12.setText("Help");
    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jTabbedPane1.setTabPlacement(JTabbedPane.BOTTOM);
    jImagePng.setText("Image(Png)");
    jImagePng.addActionListener(new mainWindow_jImagePng_actionAdapter(this));
    jMenu5.setText("Tools");
    jOptions.setSelected(false);
    jOptions.setText("Options");
    jOptions.addActionListener(new mainWindow_jOptions_actionAdapter(this));
    jDeleteAllBlocks.setText("Delete All Image Producers from IP Pane");
    jDeleteAllBlocks.addActionListener(new mainWindow_jDeleteAllBlocks_actionAdapter(this));
    jMacro.setText("Macro");
    jMacro.addActionListener(new mainWindow_jMacro_actionAdapter(this));
    jMacroType.setMaximumSize(new Dimension(32767, 120));

    jMacroType.addItem("All");
    jMacroType.addItem("One Random");
    jMacroType.addItem("75% Prob");
    jMacroType.addItem("50% Prob");
    jMacroType.addItem("25% Prob");
    jMacroType.setSelectedIndex(0);
        jMenuCopy.setText("Copy Image to Clipboard");
        jMenuCopy.addActionListener(new mainWindow_jMenuCopy_actionAdapter(this));
        jMenuPaste.setText("Paste Image from Clipboard");
        jMenuPaste.addActionListener(new mainWindow_jMenuPaste_actionAdapter(this));
        jMenu1.setText("Delete All Image Producers from IP Pane");
        jMenuStopMacro.setEnabled(false);
        jMenuStopMacro.setText("Stop Macro");
        jMenuStopMacro.addActionListener(new
                mainWindow_jMenuStopMacro_actionAdapter(this));
        jMenuDeleteIPF.setText("Delete Elements From IP Factory");
        jMenuDeleteIPF.addActionListener(new
                mainWindow_jMenuDeleteIPF_actionAdapter(this));
        jMenuCopyIPF.setText("Copy Elements from IP Factory to IP Pane");
        jMenuCopyIPF.addActionListener(new
                                       mainWindow_jMenuCopyIPF_actionAdapter(this));
        jMenuInfoIPF.setText("Open IP Factory Information Window");
        jMenuInfoIPF.addActionListener(new
                                       mainWindow_jMenuInfoIPF_actionAdapter(this));
        jMenuStartMacro.setText("Start Macro");
        jMenuMacroAll.setText("Paint All");
        jMenuMacroAll.addActionListener(new
                                        mainWindow_jMenuMacroAll_actionAdapter(this));
        jMenuMacroOne.setText("Paint One");
        jMenuMacroOne.addActionListener(new
                                        mainWindow_jMenuMacroOne_actionAdapter(this));
        jMenuMacro25.setText("Paint 25%");
        jMenuMacro25.addActionListener(new
                                       mainWindow_jMenuMacro25_actionAdapter(this));
        jMenuMacro50.setText("Paint 50%");
        jMenuMacro50.addActionListener(new
                                       mainWindow_jMenuMacro50_actionAdapter(this));
        jMenuMacro75.setText("Paint 75%");
        jMenuMacro75.addActionListener(new
                                       mainWindow_jMenuMacro75_actionAdapter(this));

        jToolBar.add(jButton1);
    jToolBar.add(jButton2);
    jToolBar.add(jButton3);
    jToolBar.add(jSlider);
    jToolBar.add(jMacro, null);
    jToolBar.add(jMacroType, null);
    jSlider.setPreferredSize(new Dimension(150,20));
    jSlider.setMaximumSize(new Dimension(150,35));
    jMenuFile.add(jMenu2);
    jMenuFile.add(jMenu3);
    jMenuFile.add(jMenu4);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuItem12);
    jMenuItem12.addActionListener(Help.getHelpListener());
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuEdit);
    jMenuBar1.add(jMenu5);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jSplitPane1, BorderLayout.CENTER);
        jSplitPane1.add(jSplitPane2, JSplitPane.RIGHT);
    jSplitPane2.add(jTabbedPane1, JSplitPane.BOTTOM);

    jTabbedPane1.add(blocksDesktop,  "Image Producers");
    jTabbedPane1.add(palletsDesktop,    "Pallets");
    jSplitPane1.add(blockSetsDesktop,JSplitPane.LEFT);
  //  contentPane.add(jToolBar, BorderLayout.NORTH);
    jSplitPane2.add(imagesDesktop,JSplitPane.TOP);
        jMenu2.add(newImage);
    jMenu2.add(newPallet);
    jMenu2.add(newBlock);
    jMenu3.add(getImage);
    jMenu3.add(openPallet);
    jMenu3.add(blockSetOpen);
    jMenu4.add(saveImage);
    jMenu4.add(saveAllImages);
    jMenu4.add(jImagePng);
    jMenu4.add(savePallet);
    jMenu5.add(jOptions);
    jMenu5.add(jDeleteAllBlocks);
        jMenu5.addSeparator();
        jMenu5.add(jMenuStartMacro);
        jMenu5.add(jMenuStopMacro);
        jMenu5.addSeparator();
        jMenu5.add(jMenuDeleteIPF);
        jMenu5.add(jMenuCopyIPF);
        jMenu5.add(jMenuInfoIPF);

        jMenuEdit.add(jMenuCopy);
        jMenuEdit.add(jMenuPaste);
        jMenuStartMacro.add(jMenuMacroAll);
        jMenuStartMacro.add(jMenuMacroOne);
        jMenuStartMacro.add(jMenuMacro25);
        jMenuStartMacro.add(jMenuMacro50);
        jMenuStartMacro.add(jMenuMacro75);
        jTabbedPane1.setSelectedIndex(0);
        jSplitPane1.setDividerLocation(200);
        jSplitPane2.setDividerLocation(400);
    }
  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  public double getProb(){
    int n = jSlider.getValue();
    return 0.01*n;
  }

  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    mainWindow_AboutBox dlg = new mainWindow_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  int palNN = 0;
  void newPallet_actionPerformed(ActionEvent e) {
    jTabbedPane1.setSelectedIndex(1);
    new FW_PalletFrame(palletsDesktop,"Color Pallet"+(++palNN));
  }

 /* public Color getRandomColor(){
    FW_PalletFrame f = (FW_PalletFrame) palletsDesktop.getFocus();
    if(f==null)
      return Color.white;
    else
      return f.getColor();
  }*/

 int imNN = 0;
  void newImage_actionPerformed(ActionEvent e) {
    FW_ImageDialog dlg = new  FW_ImageDialog();
    dlg.pack();
    dlg.setLocation(300,300);
    dlg.setModal(true);
    dlg.show();
    if(dlg.result)
       new FW_ImageFrame(imagesDesktop,"Image"+(++imNN), dlg.x,dlg.y);
  }



  int blNN = 0;
  JMenuItem jImagePng = new JMenuItem();
  JMenu jMenu5 = new JMenu();
  JMenuItem jOptions = new JMenuItem();
  JMenuItem jDeleteAllBlocks = new JMenuItem();
  JCheckBox jMacro = new JCheckBox();
  JComboBox jMacroType = new JComboBox();
    JMenuItem jMenuCopy = new JMenuItem();
    JMenuItem jMenuPaste = new JMenuItem();


    void newBlock_actionPerformed(ActionEvent e) {
    FW_newBlocksAdd dlg =  new FW_newBlocksAdd(this, blocksDesktop);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = this.getSize();
    Point loc = this.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
   /*
    blNN++;
    File root = new File(FW_Parm.rootBlockClassDir);
    String[] children = root.list();
    if ( (children == null) || (0 == children.length)) {
      return;
    }
    else {
      Object selectedValue = JOptionPane.showInputDialog(null,
      "Build new Block", "Build new Block",
      JOptionPane.INFORMATION_MESSAGE, null,
      children, children[0]);
      if(selectedValue!=null){
        try {
          String name = "blocks."+  FW_Utils.delExtension((String)selectedValue);
          Class c = Class.forName(name);
          new FW_BlockFrame(blocksDesktop, (FW_BlockInterface)c.newInstance() );
        }
        catch (Exception ex) {
           System.out.println(ex);
           ex.printStackTrace();
        }


      }
    }

*/

  }




  void openPallet_actionPerformed(ActionEvent e) {
     jTabbedPane1.setSelectedIndex(1);
     FW_SaveRestore.restorePallet(palletsDesktop);
  }

  void savePallet_actionPerformed(ActionEvent e) {
     FW_PalletFrame f = (FW_PalletFrame)palletsDesktop.getFocus();
     if(f!=null)
     FW_SaveRestore.savePallet(f);
  }

  void blockSetOpen_actionPerformed(ActionEvent e) {
    FW_SaveRestore.restoreSet(blockSetsDesktop);
  }

  void getImage_actionPerformed(ActionEvent e) {
         FW_SaveRestore.restoreImage(imagesDesktop);
  }

  void saveImage_actionPerformed(ActionEvent e) {
   FW_ImageFrame f= (FW_ImageFrame)imagesDesktop.getFocus();
   if(f!=null)
        FW_SaveRestore.saveImageJPEG(f);
  }

 void saveAllImages_actionPerformed(ActionEvent e) {
   //FW_ImageFrame f= (FW_ImageFrame)imagesDesktop.getFocus();
   //if(f!=null)
       // FW_SaveRestore.saveImageJPEG(f);
     System.out.println("Save Group of JPEG images");
     FW_SaveRestore.SaveImagesToFolder(blockSetsDesktop);
  }       
               
  void jImagePng_actionPerformed(ActionEvent e) {
    FW_ImageFrame f= (FW_ImageFrame)imagesDesktop.getFocus();
       if(f!=null)
            FW_SaveRestore.saveImagePNG(f);

  }

  void jOptions_actionPerformed(ActionEvent e) {
       FW_Parm_Dialog dlg = new FW_Parm_Dialog(this);
       Dimension dlgSize = dlg.getPreferredSize();
       Dimension frmSize = getSize();
       Point loc = getLocation();
       dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
       dlg.setModal(true);
       dlg.pack();
       dlg.show();
  }

  void jDeleteAllBlocks_actionPerformed(ActionEvent e) {
    blocksDesktop.removeAllFrames();
  }

  void jMacro_actionPerformed(ActionEvent e) {
    if(jMacro.isSelected())
        macroRecoder.start();
    else{
       if(FW_Parm.getCurrentImageFrame()!=null){
         int l = FW_Parm.getCurrentImageFrame().l;
         int h = FW_Parm.getCurrentImageFrame().h;
         FW_BlockInterface b = macroRecoder.getNewBlock(l, h,jMacroType.getSelectedIndex());
         new FW_BlockFrame(blocksDesktop, b);
       }
       macroRecoder.stop();
    }
  }

 private int macroType = 0;

 private void  startMacro(int mt) {
  macroType = mt;
  macroRecoder.start();
 }

 void stopMacro() {
      if(FW_Parm.getCurrentImageFrame()!=null){
        int l = FW_Parm.getCurrentImageFrame().l;
        int h = FW_Parm.getCurrentImageFrame().h;
        FW_BlockInterface b = macroRecoder.getNewBlock(l, h,macroType);
        new FW_BlockFrame(blocksDesktop, b);
      }
      macroRecoder.stop();
 }






    public void jMenuCopy_actionPerformed(ActionEvent e) {
        FW_ImageFrame f= (FW_ImageFrame)imagesDesktop.getFocus();
          if(f!=null)
        ClipBoard.setClipboard(f.ip.img);
    }

    public static int clip_nn=0;
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuStopMacro = new JMenuItem();
    JMenuItem jMenuDeleteIPF = new JMenuItem();
    JMenuItem jMenuCopyIPF = new JMenuItem();
    JMenuItem jMenuInfoIPF = new JMenuItem();
    JMenu jMenuStartMacro = new JMenu();
    JMenuItem jMenuMacroAll = new JMenuItem();
    JMenuItem jMenuMacroOne = new JMenuItem();
    JMenuItem jMenuMacro25 = new JMenuItem();
    JMenuItem jMenuMacro50 = new JMenuItem();
    JMenuItem jMenuMacro75 = new JMenuItem();
    public void jMenuPaste_actionPerformed(ActionEvent e) {
        BufferedImage im= null;
        Image m = ClipBoard.getClipboard();
        if(m==null)
            return;
        im = ClipBoard.toBufferedImage(m);
        if(im==null)
            return;
        clip_nn++;
        new FW_ImageFrame(imagesDesktop, "Image "+clip_nn, im);
    }

    public void jMenuMacroAll_actionPerformed(ActionEvent e) {
        jMenuStartMacro.setEnabled(false);
        jMenuStopMacro.setEnabled(true);
        startMacro(0);
    }

    public void jMenuMacroOne_actionPerformed(ActionEvent e) {
        jMenuStartMacro.setEnabled(false);
        jMenuStopMacro.setEnabled(true);
        startMacro(1);
    }

    public void jMenuMacro25_actionPerformed(ActionEvent e) {
        jMenuStartMacro.setEnabled(false);
        jMenuStopMacro.setEnabled(true);
        startMacro(2);
    }

    public void jMenuMacro50_actionPerformed(ActionEvent e) {
        jMenuStartMacro.setEnabled(false);
        jMenuStopMacro.setEnabled(true);
        startMacro(3);
    }

    public void jMenuMacro75_actionPerformed(ActionEvent e) {
        jMenuStartMacro.setEnabled(false);
        jMenuStopMacro.setEnabled(true);
        startMacro(4);
    }

    public void jMenuStopMacro_actionPerformed(ActionEvent e) {
       jMenuStartMacro.setEnabled(true);
       jMenuStopMacro.setEnabled(false);
       stopMacro();
   }


    public void jMenuDeleteIPF_actionPerformed(ActionEvent e) {
    FW_InternalFrame f =  FW_Parm.getBlockSetDesktop().getFocus();
    if ((f!= null)&&(f instanceof FW_SetOfBlocksFrame)){
        ((FW_SetOfBlocksFrame) f).jDelete();
    }
    }

    public void jMenuCopyIPF_actionPerformed(ActionEvent e) {
        FW_InternalFrame f =  FW_Parm.getBlockSetDesktop().getFocus();
       if ((f!= null)&&(f instanceof FW_SetOfBlocksFrame)){
           ((FW_SetOfBlocksFrame) f).jCopyBlock_actionPerformed(null);
       }

    }

    public void jMenuInfoIPF_actionPerformed(ActionEvent e) {
        FW_InternalFrame f =  FW_Parm.getBlockSetDesktop().getFocus();
            if ((f!= null)&&(f instanceof FW_SetOfBlocksFrame)){
                ((FW_SetOfBlocksFrame) f).showInfo();
    }
    }




}


class mainWindow_jMenuStopMacro_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuStopMacro_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuStopMacro_actionPerformed(e);
    }
}


class mainWindow_jMenuInfoIPF_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuInfoIPF_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuInfoIPF_actionPerformed(e);
    }
}


class mainWindow_jMenuCopyIPF_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuCopyIPF_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuCopyIPF_actionPerformed(e);
    }
}


class mainWindow_jMenuDeleteIPF_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuDeleteIPF_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuDeleteIPF_actionPerformed(e);
    }
}


class mainWindow_jMenuMacro75_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuMacro75_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMacro75_actionPerformed(e);
    }
}


class mainWindow_jMenuMacro50_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuMacro50_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMacro50_actionPerformed(e);
    }
}


class mainWindow_jMenuMacro25_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuMacro25_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMacro25_actionPerformed(e);
    }
}


class mainWindow_jMenuMacroOne_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuMacroOne_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMacroOne_actionPerformed(e);
    }
}


class mainWindow_jMenuMacroAll_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuMacroAll_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuMacroAll_actionPerformed(e);
    }
}


class mainWindow_jMenuPaste_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuPaste_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuPaste_actionPerformed(e);
    }
}


class mainWindow_jMenuCopy_actionAdapter implements ActionListener {
    private mainWindow adaptee;
    mainWindow_jMenuCopy_actionAdapter(mainWindow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuCopy_actionPerformed(e);
    }
}


class mainWindow_jMenuFileExit_ActionAdapter implements ActionListener {
  mainWindow adaptee;

  mainWindow_jMenuFileExit_ActionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}




class mainWindow_jMenuHelpAbout_ActionAdapter implements ActionListener {
  mainWindow adaptee;

  mainWindow_jMenuHelpAbout_ActionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class mainWindow_newPallet_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_newPallet_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.newPallet_actionPerformed(e);
  }
}

class mainWindow_newImage_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_newImage_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.newImage_actionPerformed(e);
  }
}

class mainWindow_newBlock_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_newBlock_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.newBlock_actionPerformed(e);
  }
}

class mainWindow_openPallet_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_openPallet_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openPallet_actionPerformed(e);
  }
}

class mainWindow_savePallet_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_savePallet_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.savePallet_actionPerformed(e);
  }
}

class mainWindow_blockSetOpen_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_blockSetOpen_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.blockSetOpen_actionPerformed(e);
  }
}

class mainWindow_getImage_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_getImage_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.getImage_actionPerformed(e);
  }
}

class mainWindow_saveImage_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_saveImage_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveImage_actionPerformed(e);
  }
}

class mainWindow_saveAllImages_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_saveAllImages_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveAllImages_actionPerformed(e);
  }
}

class mainWindow_jImagePng_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_jImagePng_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jImagePng_actionPerformed(e);
  }
}

class mainWindow_jOptions_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_jOptions_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jOptions_actionPerformed(e);
  }
}

class mainWindow_jDeleteAllBlocks_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_jDeleteAllBlocks_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDeleteAllBlocks_actionPerformed(e);
  }
}

class mainWindow_jMacro_actionAdapter implements java.awt.event.ActionListener {
  mainWindow adaptee;

  mainWindow_jMacro_actionAdapter(mainWindow adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMacro_actionPerformed(e);
  }
}
