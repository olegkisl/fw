package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */

public class Block_Prototype_FxyP extends Block_Prototype_Fxy{

  public Block_Prototype_FxyP(String name, int nson, int nparam) {
    super(name, nson, nparam);
  }

  public void paint(FW_ImageContext ct, int depth) {
   if (depth <= 0) {
     return;
   }
   double r=0, rw=1;
   //ct.pStart();
    startPainting(ct);
   int rr;
   int gg;
   int bb;

   double bright  = FW_Parm.getBritness();
   int nnn =0;
   int nb0=0;
   int ng0=0;
   int nr0=0;
   double ccc=-0.99;
   while(ct.pNext()){
     if(ct.isPaintStoped()){
       ct.repaint();
       return;
     }
     r = f(ct.pX(),ct.pY()); // u*Math.sin(a*ct.pX())+v*Math.sin(b*ct.pY());

     if(r<ccc){
       ;
     }
     else if(r<0){
       nnn =ct.pGet();
       nb0=nnn & 0xff;
       ng0=(nnn>>8) & 0xff;
       nr0=(nnn>>16) & 0xff;
       r = -r/ccc;
       r = r * bright;
       if (r < 0)
        r = -r;
       if (r > 1)
        r = 0.9999;

      rw = 1 - r;
      rr = (int)( (nr0 * r + caa * rw));
      gg = (int) ((ng0 * r + cbb * rw));
      bb = (int) ((nb0 * r + ccc * rw));
      ct.pSet(rr, gg, bb);

     }
     else{
       if (r > 1)
         r = 1.0 / r;

       r = r * bright;
       if (r < 0)
         r = r + 1.0;
       if (r > 1)
         r = 0.9999;

       rw = 1 - r;
       rr = (int) (ca * r + caa * rw);
       gg = (int) (cb * r + cbb * rw);
       bb = (int) (cc * r + ccc * rw);
       ct.pSet(rr, gg, bb);
     }
   }

 }

}