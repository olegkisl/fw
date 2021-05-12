package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.JDesktopPane;
import java.util.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.image.*;
import javax.imageio.*;





public class FW_SaveRestore {
    public FW_SaveRestore() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static final String PALLET_EXT = "pallet";

  static JFileChooser pal = null;
  static void initPalletFileChooser(){
    if(pal !=null) return;
    pal =  new JFileChooser();
    pal.setCurrentDirectory(new File(FW_Parm.rootDir));
    pal.setFileFilter(new PFilter());
    pal.setDialogTitle("Pallet Chooser");
  }

  static void savePallet(FW_PalletFrame f) {
    initPalletFileChooser();
    int rs = pal.showSaveDialog(f);
    if (rs == JFileChooser.APPROVE_OPTION) {
      try {
        File file = pal.getSelectedFile();
        file = FW_Utils.setExtension(file,PALLET_EXT);
        FileOutputStream fs = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(f.v);
        os.flush();
        os.close();
      }
      catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
      }
    }
  }

  static void restorePallet(FW_Desktop ds) {
    initPalletFileChooser();
    int rs = pal.showOpenDialog(ds);
    if (rs == JFileChooser.APPROVE_OPTION) {
      try {
        File file = pal.getSelectedFile();
        FileInputStream fs = new FileInputStream(file);
        ObjectInputStream os = new ObjectInputStream(fs);
        Vector vv = (Vector) os.readObject();
        os.close();
        new FW_PalletFrame(ds, file.getName() , vv);
      }
      catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
      }
    }

  }

  static class PFilter extends javax.swing.filechooser.FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = FW_Utils.getExtension(f);
        if (extension != null) {
            if (extension.equalsIgnoreCase(PALLET_EXT)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return PALLET_EXT;
    }
}


//////////////////////////////////////////////////IMAGES
  static JFileChooser img = null;
  static void initImgFileChooser(){
    if(img !=null) return;
    img =  new JFileChooser();
    img.setCurrentDirectory(new File(FW_Parm.rootDir));
    img.setFileFilter(new ImageFilter());
    img.setDialogTitle("Image Chooser");
  }

  static void saveImageJPEG(FW_ImageFrame f){saveImage(f, "jpg");}
  static void saveImagePNG(FW_ImageFrame f){saveImage(f, "png");}
  static void saveImage(FW_ImageFrame f, String type) {
        //String type = "jpg";
        initImgFileChooser();
        int rs = img.showSaveDialog(f);
        if (rs == JFileChooser.APPROVE_OPTION) {
          try {
            File file = img.getSelectedFile();
            file = FW_Utils.setExtension(file,type);
            ImageIO.write(f.ip.img,type,file);
          }
          catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
          }
        }
  }

  static FW_ImageFrame restoreImage(FW_Desktop ds) {
   initImgFileChooser();
   int rs = img.showOpenDialog(ds);
   if (rs == JFileChooser.APPROVE_OPTION) {
     try {
       File file = img.getSelectedFile();
       BufferedImage im =ImageIO.read(file);
       new FW_ImageFrame(ds, file.getName() , im);
     }
     catch (Exception e) {
       System.out.println(e);
       e.printStackTrace();
     }
   }

    return null;
  }

  static class ImageFilter extends javax.swing.filechooser.FileFilter {

  public boolean accept(File f) {
      if (f.isDirectory()) {
          return true;
      }
      String extension = FW_Utils.getExtension(f);
      if (extension != null) {
          if (extension.equalsIgnoreCase("jpg")||
              extension.equalsIgnoreCase("jpeg")||
              extension.equalsIgnoreCase("gif")||
              extension.equalsIgnoreCase("png")||
              extension.equalsIgnoreCase("jif")
              ) {
                  return true;
          } else {
              return false;
          }
      }

      return false;
  }

  //The description of this filter
  public String getDescription() {
      return "Jpeg;Png;Gif Images";
  }
}


////////////////////////////////////////////////// BLOCKS
  static final String  BLOCK_EXTENSION = "blk";
  static final String  GRAMMAR_EXTENSION = "G_grammar";

  static void saveBlock(FW_BlockInterface f, File root) {
  //  if(f instanceof Block_Prototype){
    String name = f.getName()+ "." + BLOCK_EXTENSION;
    File file = new File(root,name);
    File dir = new File(file.getParent());
    try {
       dir.mkdirs();
       file.createNewFile();
       FileOutputStream fs = new FileOutputStream(file);
       ObjectOutputStream os = new ObjectOutputStream(fs);
       os.writeObject(f);
       os.flush();
       os.close();
     }
     catch (Exception e) {
       System.out.println(e);
       e.printStackTrace();
     }
   // }

  }

  static void deleteBlock(FW_BlockInterface f, File root) {
    String name = f.getName()+ "." + BLOCK_EXTENSION;
    File file = new File(root,name);
    try {
        File ff = file.getParentFile();
        file.delete();

        if((ff!=null) && ff.isDirectory() && (ff.list()!=null) &&(ff.list().length==0)){
          if(ff.compareTo(root)!=0)
            ff.delete();
        }
     }
     catch (Exception e) {
       System.out.println(e);
       e.printStackTrace();
     }
  }


  static boolean isReadableBlock(File file){
      if(FW_Utils.checktExtension(file,"jpg")||
      FW_Utils.checktExtension(file,"jpeg")||
      FW_Utils.checktExtension(file,"gif")||
      FW_Utils.checktExtension(file,"png")||
      FW_Utils.checktExtension(file,GRAMMAR_EXTENSION)||
      FW_Utils.checktExtension(file,BLOCK_EXTENSION)
       )
      return true;
  return false;
  }

  static FW_BlockInterface restoreBlock(File file) {
    FW_BlockInterface bb= null;

    if(FW_Utils.checktExtension(file,"jpg")||
       FW_Utils.checktExtension(file,"jpeg")||
       FW_Utils.checktExtension(file,"gif")||
       FW_Utils.checktExtension(file,"png")
        ){
       try {
       BufferedImage im =ImageIO.read(file);
      // new FW_ImageFrame(ds, file.getName() , im);
      Block_Prototype_Image bim = new Block_Prototype_Image(file.getName(),im);
      return bim;
     }
     catch (Exception e) {
       System.out.println(e);
       e.printStackTrace();
     }
     return null;
   }

    if(FW_Utils.checktExtension(file,GRAMMAR_EXTENSION)){
      try {
      FileInputStream fs = new FileInputStream(file);
      grammar.Gread inn= new grammar.Gread(fs);
      System.out.println("==============READING G-GRAMMAR: "+file.getName());
      grammar.Gstructure gs = inn.read();
      fs.close();
      String[] ptrn =FW_Utils.getPatterns(file.getName());
      int sonsN =ptrn.length;
      System.out.println(file.getName()+ " : " + sonsN);
      for (int i = 0; i < ptrn.length; i++) {
        System.out.println(ptrn[i]);
      }

      bb = new grammar.Block_Prototype_G(file.getName(),gs, sonsN, ptrn);
      return bb;
    }
    catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      return null;
    }
    }

    if(!FW_Utils.checktExtension(file,BLOCK_EXTENSION))
      return null;

    try {
       FileInputStream fs = new FileInputStream(file);
       ObjectInputStream os = new ObjectInputStream(fs);
       bb = (FW_BlockInterface) os.readObject();

       if(bb instanceof Block_Prototype){// important modification to support factories
         String[] ptrn =FW_Utils.getPatterns(file.getName());
         Block_Prototype uu=(Block_Prototype)bb;
         uu.name = file.getName();
         int ln=uu.patterns.length;
       /*  if((ptrn.length>0)&&(ln>0)){
           if(ln>=ptrn.length){
             ln=ptrn.length;
           }
           for (int i = 0; i < ln; i++) {
             uu.patterns[i] = ptrn[i];
           }
         }*/
           for (int i = 0; i < ln; i++) {
              if(i<ptrn.length)
                uu.patterns[i] = ptrn[i];
              else
                uu.patterns[i] = "";
           }
       }

       os.close();
       return bb;
     }
     catch (Exception e) {
       System.out.println(e);
       e.printStackTrace();
     }

    return null;
  }


/////////////////// BLOCKS SET //////////////////////////////////
// static void saveSet(String filename) {
// }
 static JFileChooser set = null;
 static void initSetFileChooser(){
   if(set !=null) return;
   set =  new JFileChooser();
   set.setCurrentDirectory(new File(FW_Parm.rootDir));
   set.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   set.setDialogTitle("Select Root For Blocks Tree");
 }


  static void restoreSet(FW_Desktop ds) {
    initSetFileChooser();
    int rs = set.showOpenDialog(ds);
    if (rs == JFileChooser.APPROVE_OPTION) {
     try {
       File file = set.getSelectedFile();
       new FW_SetOfBlocksFrame(ds, file);
     }
     catch (Exception e) {
       System.out.println(e);
       e.printStackTrace(System.out);
     }
   }

    return ;
  }

    private void jbInit() throws Exception {
    }

}

