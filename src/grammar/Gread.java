package grammar;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.lang.*;
public class Gread extends java.lang.Object
{
 InputStream in2=null;
 StreamTokenizer1 in=null;

public Gread(InputStream inn)
{
  in2=inn;
}

public  Gstructure read()
{
 Gstructure grm;
 String c=null;

 try{
    in=new StreamTokenizer1(in2);
        c=in.findNextCommand();
        if((c==null)||(!c.equals("LLG1")))
         { throw new IOException("Unknown Language");}
        c=in.findNextCommand();
        if(!c.equals("G_BEGIN"))
         { throw new IOException("Unknown Language");}
    //in.next();
    c=in.findNextComment();
    grm= new Gstructure(c);

    while(true)
    {
     c=in.findNextCommand();
     if("INIT".equals(c))
     {String nm,com;
      nm=in.findNextString();
      com=in.findNextComment();
      grm.rname.add(nm,com);
     }
     else if("ALF".equals(c))
     {String nm;
      nm=in.findNextString();
      grm.alfabet.addElement(nm);
     }
     else if("PARM".equals(c))
      {
        String nm,com;
        double n1, n2, n3, n4;
        nm=in.findNextString();
        n1=in.findNextDouble();
        n2=in.findNextDouble();
        n3=in.findNextDouble();
        n4=in.findNextDouble();
        com=in.findNextComment();
        Const ccc= new Const(nm,com,n1,n2,n3,n4);
        grm.consts.put(nm,ccc);
      }
     else if("RULE_BEGIN".equals(c))
        {
            Rule r=null;
            String nm,com,c1;
            nm=in.findNextString();
            r= new Rule(nm);
            grm.rules.addElement(r);
            r.comment=in.findNextComment();

            while(true)
            {
                c1=in.findNextCommand();
                if("RULE_END".equals(c1))
                    break;
                else if("IF".equals(c1))
                    r.cond=nextRealVal6();
                 else if("WEIGHT".equals(c1))
                     r.weight=nextRealVal4();
                else if("BRIGHT".equals(c1))
                     r.bright=nextRealVal4();
                else if("SCALE".equals(c1))
                     r.scale=nextRealVal4();
                else if("ANGLE".equals(c1))
                    r.angle=nextRealVal4();
                else if("RGB".equals(c1))
                    {
                    r.rMod=nextRealVal4();
                    r.gMod=nextRealVal4();
                    r.bMod=nextRealVal4();
                    }
                else if("ABS_ANGLE".equals(c1))
                    r.absAngle=true;
                else if("ABS_SCALE".equals(c1))
                     r.absScale=true;
                else if("COLOR_ABS".equals(c1))
                     r.absColor=1;
                else if("COLOR_ADD".equals(c1))
                     r.absColor=2;
                else if("COLOR_MULT".equals(c1))
                     r.absColor=3;
                else if("MUTATE".equals(c1))
                     r.mutate=true;
                else if("RAND_XX".equals(c1))
                    r.randXX=true;
                else if("RAND_YY".equals(c1))
                    r.randYY=true;
                else if("RAND_ZZ".equals(c1))
                    r.randZZ=true;
                else if("IMAGE_BEGIN".equals(c1))
                     {GeomObj g;
                      double cr=1.0,cg=1.0,cb=1.0;
                      String c2;
                      g=new GeomObj(cr,cg,cb);
                      r.geom=g;
                      while(true)
                         {
                            c2=in.findNextCommand();
                            if("IMAGE_END".equals(c2))
                                break;
                            else if("COLOR".equals(c2))
                                {
                                double x1,y1,x2,y2;
                                cr=in.findNextDouble();
                                cg=in.findNextDouble();
                                cb=in.findNextDouble();
                                }
                            else if("POINT".equals(c2))
                                {
                                double x1,y1;
                                x1=in.findNextDouble();
                                y1=in.findNextDouble();
                                g.addElement( new Pnt(x1,y1,cr,cg,cb));
                                }
                            else if("LINE".equals(c2))
                                {
                                double x1,y1,x2,y2;
                                x1=in.findNextDouble();
                                y1=in.findNextDouble();
                                x2=in.findNextDouble();
                                y2=in.findNextDouble();
                                g.addElement(new Lin(x1,y1,x2,y2,cr,cg,cb));
                                }
                              else if("POLYGON".equals(c2))
                                {
                                Poly p=new Poly(cr,cg,cb);
                                g.addElement(p);
                                readPoly(p);
                                }
                            else
                                {throw new IOException("Bad Image definition");}
                         }
                     }// End Image reading

                else if("LABEL_BEGIN".equals(c1))
                     {
                        GLabel l=null;
                        String nm2,c2;
                        nm2=in.findNextString();
                        l= new GLabel(nm2);
                        r.lbs.addElement(l);
                        while(true)
                         {
                            c2=in.findNextCommand();
                            if("LABEL_END".equals(c2))
                                break;
                            else if("LINE".equals(c2))
                                {
                                double x1,y1,x2,y2;
                                x1=in.findNextDouble();
                                y1=in.findNextDouble();
                                x2=in.findNextDouble();
                                y2=in.findNextDouble();
                                l.l = new Lin(x1,y1,x2,y2,1.0,1.0,1.0);
                                }
                            else if("XX".equals(c2))
                                 l.modXX=nextRealVal4();
                            else if("YY".equals(c2))
                                 l.modYY=nextRealVal4();
                            else if("ZZ".equals(c2))
                                 l.modZZ=nextRealVal4();
                            else if("MIRROR".equals(c2))
                                 l.mirror=true;
                            else if("MOVEX".equals(c2))
                                 l.moveX=nextRealVal();
                            else if("MOVEY".equals(c2))
                                 l.moveY=nextRealVal();
                            else if("SCALEX".equals(c2))
                                 l.scaleX=nextRealVal();
                            else if("SCALEY".equals(c2))
                                 l.scaleY=nextRealVal();
                            else if("SHIFTX".equals(c2))
                                 l.shiftX=nextRealVal();
                            else if("SHIFTY".equals(c2))
                                 l.shiftY=nextRealVal();
                            else if("ROTATE".equals(c2))
                                 l.rotate=nextRealVal();
                            else
                                {throw new IOException("Bad Command in the Label definition");}
                         }
                        if(l.l==null)
                            {throw new IOException("No vector in the Label definition");}
                     }// End Label reading

                else if("LINE".equals(c1))
                    {
                      double x1,y1,x2,y2;
                      x1=in.findNextDouble();
                      y1=in.findNextDouble();
                      x2=in.findNextDouble();
                      y2=in.findNextDouble();
                      r.lb0=new GLabel0(x1,y1,x2,y2);

                    }
                else
                    {throw new IOException(" Unknown Command Name(in RULE)");}
            }
         if(r.lb0==null)
            {throw new IOException(" No Label 0 in RULE's definition");}
        }//end Rule
     else if("G_END".equals(c))
        return grm;
     else
        {throw new IOException(" Unknown Command's Name ");}
    }
    }// End Grammar reading
catch(Exception e)
        {
            System.out.println("G_Grammar Input Error: "+e.getMessage());
            if(in!=null)
                System.out.println("line #"+in.strNumber());
        }
        return null;
}


void readPoly(Poly pl)
throws IOException
{double x0;
    while(true)
    {
    in.next();
    if(in.ttype==StreamTokenizer.TT_NUMBER)
         {
            x0=in.nval;
            in.next();
            if(in.ttype!=StreamTokenizer.TT_NUMBER)
                throw new IOException("Bad tokens in Polygone");
            pl.addPnt(x0,in.nval);
         }
    else if(in.ttype==(int)'>')
         return ;
    else throw new IOException("Unexpected token in Insted of real Value");
    }
}

RealVal nextRealVal()
throws IOException
{
    in.next();
    if(in.ttype==StreamTokenizer.TT_NUMBER)
         return   new RealVal(in.nval);
    else if(in.ttype==StreamTokenizer.TT_WORD)
         return   new RealVal(0.0,in.sval);
    else if(in.ttype==(int)'>')
         return null;
    else throw new IOException("Unexpected token in Insted of real Value");

}

RealVal4 nextRealVal4()
throws IOException
{RealVal n1, n2, n3, n4;
  n1=nextRealVal();
  if(n1==null) throw new IOException("Real value expected");
  n2=nextRealVal();
  if(n2==null) throw new IOException("Real value expected");
  n3=nextRealVal();
  if(n3==null) throw new IOException("Real value expected");
  n4=nextRealVal();
  if(n4==null) throw new IOException("Real value expected");
  return new RealVal4(n1,n2,n3,n4);
}

RealVal6 nextRealVal6()
throws IOException
{RealVal n1, n2, n3, n4, n5, n6;
  n1=nextRealVal();
  if(n1==null) throw new IOException("Real value expected");
  n2=nextRealVal();
  if(n2==null) throw new IOException("Real value expected");
  n3=nextRealVal();
  if(n3==null) throw new IOException("Real value expected");
  n4=nextRealVal();
  if(n4==null) throw new IOException("Real value expected");
  n5=nextRealVal();
  if(n5==null) throw new IOException("Real value expected");
  n6=nextRealVal();
  if(n6==null) throw new IOException("Real value expected");
  return new RealVal6(n1,n2,n3,n4,n5,n6);
}
}

/////////////////////////////////////////////////
class StreamTokenizer1 extends StreamTokenizer
{
    private int nstr=1;

    StreamTokenizer1(InputStream in)
    {super(in);
     // StreamTokenizer in=new StreamTokenizer(in2);
         resetSyntax();
         quoteChar((int)'\"');
         slashStarComments(true);
         slashSlashComments(true);
         wordChars((int)'A', (int)'Z');
         wordChars((int)'a', (int)'z');
         wordChars((int)'_', (int)'_');
         //n.wordChars((int)'0', (int)'9');
         parseNumbers();
         whitespaceChars((int)' ',(int)' ');
         //whitespaceChars((int)'\n',(int)'\n');
     whitespaceChars((int)'\t',(int)'\t');
    }

    public int next()
    throws IOException
    {int i=0;
        i=super.nextToken();
        while(ttype==(int)'\n')
            {
            i=super.nextToken();
            nstr++;
            }
         return i;
    }

    int strNumber() { return nstr;}


 String findNextCommand()
    throws IOException
    {
    while(true)
        {
        next();
        if(ttype==StreamTokenizer.TT_EOF)
         { throw new IOException("Unexpected EndOfFile");}
        else if(ttype==(int)'<')
            {
            next();
            if(ttype==StreamTokenizer.TT_WORD)
                return  new String(sval);
            else
               { throw new IOException("Bad command");}
            }

        }

    }

 String findNextString()
    throws IOException
    {
        next();
        if(ttype==StreamTokenizer.TT_EOF)
         { throw new IOException("Unexpected EndOfFile");}
        if(ttype==StreamTokenizer.TT_WORD)
                return  new String(sval);
         else
               { throw new IOException("Bad command");}
    }

 String findNextComment()
    throws IOException
    {
      next();
      if(ttype==StreamTokenizer.TT_EOF)
         { throw new IOException("Unexpected EndOfFile");}
      else if(ttype==(int)'\"')
         return  new String(sval);
      else
         return null;
    }


double  findNextDouble()
    throws IOException
    {
      next();
      if(ttype==StreamTokenizer.TT_EOF)
         { throw new IOException("Unexpected EndOfFile");}
      else if(ttype==StreamTokenizer.TT_NUMBER)
         return   nval;
      else
         { throw new IOException("Unexpected token");}
    }

}
