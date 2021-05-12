package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 */
/*
 FW_Desktop blockSetsDesktop = new FW_Desktop();
  FW_Desktop blocksDesktop = new FW_Desktop();
  FW_Desktop palletsDesktop = new FW_Desktop();
  FW_Desktop imagesDesktop = new FW_Desktop();
 */
import java.io.File;

public class FW_Parm {
  private static mainWindow mainWin = null;
  private static FW_Builder builder = null;

  public static String rootDir = "";
  public static String rootBlockClassDir = "";
  public static String rootExtensionClassDir = "";

  public static FW_MacroRecoder getMacroRecorder(){
    return  mainWin.macroRecoder;
  }

  static void init(mainWindow frame) {
    mainWin = frame;
    builder = new FW_Builder();
    rootDir = System.getProperty("user.dir", "");
    String rd1 = rootDir + File.separator + "build"; //changes in netBeans
    rootBlockClassDir = rd1 + File.separator + "classes" + File.separator +
        "blocks";
    rootExtensionClassDir = rd1 + File.separator + "classes" + File.separator +
        "extension";
    System.out.println(System.getProperty("user.dir", ""));
  }

  static FW_Builder getCurrentBuilder() {
    return builder;
  }

  static FW_PalletFrame getCurrentPallet() {
    return (FW_PalletFrame) mainWin.palletsDesktop.getFocus();
  }

  static FW_SetOfBlocks getCurrentSetOfBlocks() {
    return (FW_SetOfBlocks) mainWin.blockSetsDesktop.getFocus();
  }

  static FW_BlockInterface getCurrentBlockInterface() {
    FW_BlockFrame gg =(FW_BlockFrame) mainWin.blocksDesktop.getFocus();
    if(gg==null)
      return null;
    return gg.getBlock();
    //return ( (FW_BlockFrame) mainWin.blocksDesktop.getFocus()).getBlock();
  }

  static FW_ImageFrame getCurrentImageFrame() {
    return (FW_ImageFrame)getImageDesktop().getFocus();
  }


/////////////////////////////////////////////////////////////
  static FW_Desktop getBlockDesktop() {
    return mainWin.blocksDesktop;
  }

  static FW_Desktop getPalletDesktop() {
    return mainWin.palletsDesktop;
  }

  static FW_Desktop getImageDesktop() {
    return mainWin.imagesDesktop;
  }

  static FW_Desktop getBlockSetDesktop() {
    return mainWin.blockSetsDesktop;
  }

///////////////////Counter////////////////////////////////////////////
  static int code = 0;
  static String getCode() {
    code++;
    return "" + code;
  }



//////////////// Parameters Values ////////////////////////////////////////////////
  private static double mutateProbub = 0.5;      // probubility of point mutation
  private static double destructionProbub = 0.3; // probubility of objects links destruction
  private static double briteness = 1.0;         // brightness modifier
  private static int    maxBlockDepth = 5;       // max deptht of blocks tree
  private static int    drawRepetition = 4;      // for L and R repetition of drawing
  //

  private static int    sliceForMutation = 0;    // 0 all nodes
                                                 // positive slice of the tree from top
                                                 // negative: slice from bottom not recomended
  private static double nonterminalProbability = 0.0;  // nonterm. node only selection prob in tree building
                                                       //  0 <= use random choice
                                                       // useful to build tree with more branches

  private static double recombinationMutationProbability = 0.5;  // nodes recombination on one slice
                                                                 // change the nature of "Large" mutations
  private static int    paintType = 1;  // values : 1-9  paint quality
  private static int    paintMode = 1;  // 0 -default, 1 - save transformation, 2-use transformation
  ///////////////////////////////////////////////////////////////////////////////////

    public static int getPaintType() {
        return paintType;
    }

    public static int getPaintMode() {
        return paintMode;
    }

    public static void setPaintMode(int paintMode) {
        FW_Parm.paintMode = paintMode;
    }

    public static void setPaintType(int paintType) {
        FW_Parm.paintType = paintType;
    }


  public static void setRecombinationMutationProbability(double recombinationMutationProbability) {
        FW_Parm.recombinationMutationProbability = recombinationMutationProbability;
    }
    public static double getRecombinationMutationProbability() {
        return recombinationMutationProbability;
    }

    public static void setNonterminalProbability(double nonterminalProbability) {
        FW_Parm.nonterminalProbability = nonterminalProbability;
    }

    public static void setSliceForMutation(int sliceForMutation) {
        FW_Parm.sliceForMutation = sliceForMutation;
    }

    public static double getNonterminalProbability() {
        return nonterminalProbability;
    }

    public static int getSliceForMutation() {
        return sliceForMutation;
    }


  static void setMutateProb(double mProbub) {
    mutateProbub = mProbub;
  }

  static void setDestructionProb(double mProbub) {
    destructionProbub = mProbub;
  }

  static void setBriteness(double b) {
    briteness = b;
  }

  static void setMaxBlockDepth(int b) {
   maxBlockDepth = b;
 }

 static void setDrawRepetition(int b) {
  drawRepetition = b;
}





  public static double getDestructionProb()             {return destructionProbub;}// mainWin.getProb();} //destruction probub
  public static double getMutateProb()       {return  mutateProbub;} // point mutation probability
  public static int    getMaxBlockDepth()    { return maxBlockDepth; }
  public static int    getDrawRepetition()    { return drawRepetition; }

  public static int    getMaxPaintDepth()    { return 40; }
  public static int    getMutateSpeed()      { return 1; }
  public static int    getColorMutateSpeed() { return 1; }
  public static long   getRepaintTime()      { return 2000;}
  public static double getBritness()         { return briteness;} // britness multiplier
}
