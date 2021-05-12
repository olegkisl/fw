package fw2003;
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

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class FW_Utils {

  public static String getDif(String a, String b) {
     int i = b.length();
     if(i<0)
       return a;
     if(i>=a.length())
       return a;
     return a.substring(i+1);
  }

  public static boolean checktExtension(File f,String ext) {
    if(f == null) return false;
    String ff =getExtension(f);
    if(ff == null) return false;
    return ff.equalsIgnoreCase(ext);
  }

  public static String getExtension(File f) {
          String ext = null;
          String s = f.getName();
          int i = s.lastIndexOf('.');

          if (i > 0 &&  i < s.length() - 1) {
              ext = s.substring(i+1).toLowerCase();
          }
          return ext;
      }

 public static File setExtension(File f, String ext) {
        String s = f.getPath();

        if((ext!=null)&&checktExtension(f, ext))
            ;
        else
            s = s+"."+ext;
        File  ff = new File(s);
    return ff;
  }

  public static String delExtension( String s) {
         int i = s.lastIndexOf('.');
         if (i > 0 &&  i < s.length() - 1) {
            s = s.substring(0,i);
        }
   return s;
 }

 static int nn0 = (int)'0';
 static int nn9 = (int)'9';
 public static int getDigitName(String name) {
   int len = name.length();
   if(len<2)
     return -1;
   if(name.charAt(0)!= '_')
     return -1;
   int a=(int)name.charAt(1);
   if(a<nn0) return -1;
   if(a>nn9) return -1;
   if(len<=2){
     return a-nn0;
   }
   else{
     int b=(int)name.charAt(2);
     if(b<nn0) return a-nn0;
     if(b>nn9) return a-nn0;
     return 10*(a-nn0)+ b -nn0;
   }
 }

 public static String[] getPatterns(String name) {
   if(name == null)
     return new String[0];
   char[] nm = name.toCharArray();
   int cond = 0;
   int nn = -1;
   int start =0;
   java.util.List ls = new LinkedList();
   for (int i = 0; i < nm.length; i++) {
     if(cond ==0){
        if(nm[i]=='('){
          cond =1;
          nn=0;
          start = i+1;
        }
     }else{
       if(nm[i]==','){
         ls.add(new String(nm,start,i-start));
         nn++;
         start = i+1;
       }
       if(nm[i]==')'){
         ls.add(new String(nm,start,i-start));
         nn++;
         String[] u = new String[nn];
         for (int j = 0; j < u.length; j++) {
           u[j]=(String)ls.get(j);
         }
         return u;
       }
     }
   }//for

   return new String[0];
 }


 public static void warning(String message) {
   JOptionPane.showMessageDialog(null, message, "",
                                 JOptionPane.WARNING_MESSAGE);
 }

}
