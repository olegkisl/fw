package blocks;
import fw2003.*;


// 
public class AA1_IFS   extends Block_Prototype_Fxy {
static final long serialVersionUID =1L;
  double sc = 3.0;
  public AA1_IFS() {
     super("AA1_IFS", 0, 10);
     mutate_this();
  }

  Tr2 ha1 = new Tr2(0.5, 0.0, 0.2, 0.0, 0.5, 0.3);
  Tr2 ha2 = new Tr2(0.5, 0.0, 0.4, 0.0, 0.5, 0.2);
  Tr2 ha3 = new Tr2(0.5, 0.0, 0.6, 0.0, 0.5, 0.1);
  Tr2 ha4 = new Tr2(0.5, 0.0, 0.0, 0.0, 0.5, 0.7);

  private Tr2 make(double xx, double yy) {
    Tr2 t = Tr2.turn(0.0, 0.0, 20.0 -40.0*FW_Rand.getDouble());
    Tr2 t1 = Tr2.move(xx-0.2+0.4*FW_Rand.getDouble(), yy-0.2+0.4*FW_Rand.getDouble());
    Tr2 t2 = Tr2.scale(0.5+ FW_Rand.getDouble() * 0.4, 0.5+ FW_Rand.getDouble() * 0.4);
    t = Tr2.mult(t, t1);
    t = Tr2.mult(t, t2);
    t=  Tr2.inv(t);
    return t;
  }

  public void mutate_this() {
    ha1 = make(0.0,0.0);
    ha2 = make(0.0,0.5);
    ha3 = make(0.5,0.0);
    ha4 = make(0.5,0.5);
  }

   P2 next(P2 p) {
    double clrM = 0.5; // Coeficient of Transformation

    double x0 = p.x;
    double y0 = p.y;
    double r0 = 0, r, a, b;
    P2 pp = new P2(x0, y0);
    switch (FW_Rand.rand(4)) {
      case 0:
        pp.act(ha1);
        break;
      case 1:
        pp.act(ha2);
        break;
      case 2:
        pp.act(ha3);
        break;
      case 3:
        pp.act(ha4);
    }
    return pp;
  }

   P2 next(P2 p, int n) {
    double clrM = 0.5; // Coeficient of Transformation

    double x0 = p.x;
    double y0 = p.y;
    double r0 = 0, r, a, b;
    P2 pp = new P2(x0, y0);
    switch (n) {
      case 0:
        pp.act(ha1);
        break;
      case 1:
        pp.act(ha2);
        break;
      case 2:
        pp.act(ha3);
        break;
      case 3:
        pp.act(ha4);
    }
    return pp;
  }


  public double f(double x, double y){
    P2 p = new P2(x,y);
    //for (int i = 0; i < 1; i++) {
    //  p = next(p);
    //}
    p = next(p,0);
    int nx =(int)(p.x*100);
    int ny =(int)(p.y*100);
    int k = (nx*11111+ny*34127+ 222)%20;
    double ttt =FW_Rand.getDouble()*k*0.05;
    p = next(p,1);
    nx =(int)(p.x*50);
    ny =(int)(p.y*50);
    k = (nx*113111+ny*314127+ 2422)%20;
    double ttt1 =FW_Rand.getDouble()*k*0.05;
    p = next(p,2);
    nx =(int)(p.x*25);
    ny =(int)(p.y*25);
    k = (nx*116111+ny*341287+ 2252)%20;
    double ttt2 =FW_Rand.getDouble()*k*0.05;
    p = next(p,3);
    nx =(int)(p.x*12);
    ny =(int)(p.y*12);
    k = (nx*111411+ny*348127+ 21272)%20;
    double ttt3 =FW_Rand.getDouble()*k*0.05;




   // if( p.x<1.0 && p.x>0.0 && p.y<1.0 && p.y>0.0){
   //        ttt=1.0;
   //   }
   return  (ttt+ttt1+ttt2+ttt3)*0.25;
  }

}


