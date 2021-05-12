package fw2003;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;

public class ClipBoard {
        // If an image is on the system clipboard, this method returns it;
        // otherwise it returns null.
        public static Image getClipboard() {
            Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

            try {
                if (t != null && t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    Image text = (Image)t.getTransferData(DataFlavor.imageFlavor);
                    return text;
                }
            } catch (UnsupportedFlavorException e) {
            } catch (IOException e) {
            }
            return null;
        }

  //  Setting an image on the system clipboard requires a custom Transferable object to hold the image while on the clipboard.
        // This method writes a image to the system clipboard.
        // otherwise it returns null.
        public static void setClipboard(Image image) {
            ImageSelection imgSel = new ImageSelection(image);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel, null);
        }

        // This class is used to hold an image while on the clipboard.
        public static class ImageSelection implements Transferable {
            private Image image;

            public ImageSelection(Image image) {
                this.image = image;
            }

            // Returns supported flavors
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            // Returns true if flavor is supported
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            // Returns image
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (!DataFlavor.imageFlavor.equals(flavor)) {
                    throw new UnsupportedFlavorException(flavor);
                }
                return image;
            }
        }


        public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
      //  boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
           // if (hasAlpha) {
           //     transparency = Transparency.BITMASK;
           // }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
           // if (hasAlpha) {
           //     type = BufferedImage.TYPE_INT_ARGB;
           // }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }



}
