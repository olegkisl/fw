package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
import java.io.*;
import java.util.List;

public class Block_Prototype implements FW_BlockInterface, Serializable {

    static final long serialVersionUID = 1L;
    String name = "";
    String description = "";
    public FW_BlockInterface[] sons = new FW_BlockInterface[0];
    protected String[] patterns = new String[0];
    protected Class[] classes = new Class[0];

    public Block_Prototype() {
        int nSons = 0;
        this.name = this.getClass().getName();
        description = "";
    }

    public boolean isInitial() {
        return true;
    }// Can be used as initial block

    public Block_Prototype(String name, String descr, int nSons) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = this.getClass().getName();
        }
        if (descr != null) {
            description = descr;
        }
        sons = new FW_BlockInterface[nSons];
        patterns = new String[nSons];
        classes = new Class[nSons];
        for (int i = 0; i < sons.length; i++) {
            sons[i] = null;
            patterns[i] = "";
            classes[i] = FW_BlockInterface.class;
        }

    }

    public Block_Prototype(String name, String descr, int nSons, Class cls) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = this.getClass().getName();
        }
        if (descr != null) {
            description = descr;
        }
        sons = new FW_BlockInterface[nSons];
        patterns = new String[nSons];
        classes = new Class[nSons];
        for (int i = 0; i < sons.length; i++) {
            sons[i] = null;
            patterns[i] = "";
            classes[i] = cls;
        }

    }

/////////////////////////////General
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/////////////////////////// Build process
    public int getNumberOfSons() {
        return sons.length;
    }

    public String getSonPattern(int n) {
        return patterns[n];
    }

    public Class getSonClass(int n) {
        return classes[n];
    }

    public FW_BlockInterface getSon(int n) {
        return sons[n];
    }

    public void setSon(int n, FW_BlockInterface block) {
        sons[n] = block;
    }

    public void clearAllSons() {
        for (int i = 0; i < sons.length; i++) {
            sons[i] = null;
        }
    }

    public FW_BlockInterface copy() {
        Object cop = null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();

            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            cop = in.readObject();
            in.close();
            return (FW_BlockInterface) cop;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Mutations
    static private int nMut = 10;

    public void mutateLarge() {
        double recomb = FW_Parm.getRecombinationMutationProbability();
        int sliceType = FW_Parm.getSliceForMutation();



        if(recomb >  FW_Rand.rand01()){
            FW_Builder.randomRecombination(this, sliceType);
            return;
        }

        if (sliceType != 0) {
            sliceMutationLarge(sliceType);
            return;
        }
        
        mutateLarge_this();
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                sons[i].mutateLarge();
            }
        }
    }

     private void sliceMutationLarge(int sliceType) {
        List<FW_BlockInterface> lsl = FW_Builder.getSlice(this, sliceType);
        for (FW_BlockInterface bb : lsl) {
            if (bb instanceof Block_Prototype) {
                ((Block_Prototype) bb).mutate_this();
            }
        }
        return;
    }

    public void mutateLarge_this() {
        for (int i = 0; i < nMut; i++) {
            mutate_this();
        }
    }

    public void mutate() {
        int sliceType = FW_Parm.getSliceForMutation();
        if (sliceType != 0) {
            sliceMutation(sliceType);
            return;
        }
        mutate_this();
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                sons[i].mutate();
            }
        }
    }

    private void sliceMutation(int sliceType) {
        List<FW_BlockInterface> lsl = FW_Builder.getSlice(this, sliceType);
        for (FW_BlockInterface bb : lsl) {
            if (bb instanceof Block_Prototype) {
                ((Block_Prototype) bb).mutateLarge_this();
            }
        }
        return;
    }



    public void mutate_this() {
        ;
    } // REDEFINE

    public void mutateColor() {
        mutateColor_this();
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                sons[i].mutateColor();
            }
        }
    }

    public void mutateColor_this() {
        ;
    } // REDEFINE

    public void mutatePallet(FW_PalletInterface p) {
        mutatePallet_this(p);
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                sons[i].mutatePallet(p);
            }
        }
    }

    public void mutatePallet_this(FW_PalletInterface p) {
        ;
    } // REDEFINE

    //Standard Editor////////////////////////////////////////////////////
    public boolean isParamEditable() {
        return false;
    }

    public java.util.List getParamList() {
        return null;
    }

    public void setParamList(java.util.List param) { // REDEFINE
        ;
    }

    //Free Form Editor////////////////////////////////////////////////
    public boolean isEditable() {
        return false;
    }

    public javax.swing.JPanel getEditor() {
        return null;
    }

    ////////////// Painting //////////////////////////////////////////////////
    public void preparePaintingProcess() {
        ;
    }

    public void prepareToPaint() {
        preparePaintingProcess();
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                sons[i].prepareToPaint();
            }
        }
    }

    public void paint(FW_ImageContext ct, int depth) { // default painting
        if (depth <= 0) {
            return;
        }
        if (ct.isPaintStoped()) {
            return;
        }
        int d = depth - 1;
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                sons[i].paint(ct, d);
            }
        }
    }

    ////////////// Debuguging Staff
    public void print() {
        System.out.println();
        System.out.println("======= Object " + getName());
        printR(" ");
    }

    public void printR(String add) {
        System.out.println(add + " " + getName());
        String c = add + "**";
        for (int i = 0; i < sons.length; i++) {
            if (sons[i] != null) {
                ((Block_Prototype) (sons[i])).printR(c);
            } else {
                System.out.println(c + " null");
            }
        }

    }
}
