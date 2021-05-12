package fw2003;

/**
 * <p>Title: Images generator FW2003</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Kislyuk
 * @version 3.0
 *
 * Build Blocks Tree / Decompose Block Tree to the separate Blocks
 *
 *
 *
 *
 */
import java.util.*;

public class FW_Builder {

    static int maxDepth = 5;

    public FW_Builder() {
    }

//////////////////////////////////////////////////////////////////////////
    private boolean compareName(String name, String pattern) {
        int nn = name.indexOf("(");
        if (nn >= 0) {
            name = name.substring(0, nn); // delete (a,b,c..) at the end of the name
        }
        String a = name.toLowerCase();
        String p = pattern.toLowerCase();
        return (a.indexOf(p) >= 0);
        //   return (name.indexOf(pattern)>=0) ;
    }
///////////////////////////////////////////////////////////////////////////
///////// Can we use block "target" a son of the current block?
////////  This important function set building strategy
////////
///////////////////////////////////////////////////////////////////////////
    private static final String uString = "_u_";// Uniq element name substring

    private boolean isCompartible(Class sonType, String sonNamePattern,
            FW_BlockInterface target, String targetName,
            int targetSonsNumber, int depth, boolean nonTerm) {
        if (sonType.isInstance(target) != true) { // may not be changed
            return false;
        }
        if (nonTerm) { // preferable nodes with > 0 sons
            return isCompartibleNonTerminal(sonType, sonNamePattern,
                    target, targetName,
                    targetSonsNumber, depth);
        }
        /////////////////// prefix and depth restriction strategy //////////////
        if ((sonNamePattern == null)
                || (sonNamePattern.equals(""))
                || (targetName == null)
                || (targetName.equals(""))
                || compareName(targetName, sonNamePattern)) {
            if (depth < maxDepth) {
                return true;
            } else if (targetSonsNumber == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isCompartibleNonTerminal(Class sonType, String sonNamePattern,
            FW_BlockInterface target, String targetName,
            int targetSonsNumber, int depth) {
        if (sonType.isInstance(target) != true) { // may not be changed
            return false;
        }

        /////////////////// prefix and depth restriction strategy //////////////
        if ((sonNamePattern == null)
                || (sonNamePattern.equals(""))
                || (targetName == null)
                || (targetName.equals(""))
                || compareName(targetName, sonNamePattern)) {
            if ((depth < maxDepth) && (targetSonsNumber > 0)) {
                return true;
            } else if ((depth >= maxDepth) && (targetSonsNumber == 0)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    ////////Uniq nodes staff
    private List uniq_nodes = new LinkedList();

    private void setUNode(FW_BlockInterface bb) {
        String nm = bb.getName();
        if (compareName(nm, uString)) {
            uniq_nodes.add(bb);
        }
    }

    private void clearUNodes() {
        uniq_nodes.clear();
    }

    private FW_BlockInterface getUNode(Class cl, String pattern) {
        FW_BlockInterface bb;
        for (Iterator t = uniq_nodes.iterator(); t.hasNext();) {
            bb = (FW_BlockInterface) t.next();
            if (isCompartible(cl, pattern, bb, bb.getName(), 0,
                    0, false)) {
                return bb;
            }
        }

        return null;
    }

    //////////////////////////////////////////////////////
    //////////// BUILD BLOCKS TREE //////////////////////
    public FW_BlockInterface construct(java.util.List blocksList,
            FW_BlockInterface blockInit) {
        clearUNodes();
        maxDepth = FW_Parm.getMaxBlockDepth();
        FW_BlockInterface b = blockInit.copy();
        if (b == null) {
            b = getInitBlock(blocksList);
        }
        if (b == null) {
            return null;
        }
        int depth = 0;
        constr(depth, blocksList, b);
        return b;
    }

    private FW_BlockInterface getInitBlock(java.util.List blocksList) {
        FW_BlockInterface bb;
        int nn = 0;
        for (Iterator t = blocksList.iterator(); t.hasNext();) {
            bb = (FW_BlockInterface) t.next();
            if (bb.isInitial()) {
                nn++;
            }
        }
        if (nn == 0) {
            return null;
        }
        int pr = FW_Rand.rand(nn);
        nn = 0;
        for (Iterator t = blocksList.iterator(); t.hasNext();) {
            bb = (FW_BlockInterface) t.next();
            if (bb.isInitial()) {
                if (nn == pr) {
                    return bb;
                }
                nn++;
            }
        }
        return null;
    }

    private void constr(int depth, java.util.List blocksList, FW_BlockInterface b) {
        if (depth > maxDepth) {
            return;
        }
        int depthN = depth + 1;
        FW_BlockInterface bb;
        String pattern = null;
        Class cl = null;
        int nn, pr;

        // add sons
        for (int i = 0; i < b.getNumberOfSons(); i++) {
            if (b.getSon(i) != null) {
                continue;// son already exsist
            }
            pattern = b.getSonPattern(i);
            cl = b.getSonClass(i);
            nn = 0;

            ////
            FW_BlockInterface bu = getUNode(cl, pattern);//Uniq nodes addition
            if (bu != null) {
                b.setSon(i, bu.copy());
                continue;
            }
            //// if nonTerm positive will select sons>0 nodes if depth < max
            boolean nonTerm = FW_Rand.rand01() < FW_Parm.getNonterminalProbability();

            for (Iterator t = blocksList.iterator(); t.hasNext();) {
                bb = (FW_BlockInterface) t.next();
                if (isCompartible(cl, pattern, bb, bb.getName(), bb.getNumberOfSons(),
                        depthN, nonTerm)) {
                    nn++;
                }
            }
            if (nn == 0) {
                continue;
            }
            pr = FW_Rand.rand(nn);
            nn = 0;
            for (Iterator t = blocksList.iterator(); t.hasNext();) {
                bb = (FW_BlockInterface) t.next();
                if (isCompartible(cl, pattern, bb, bb.getName(), bb.getNumberOfSons(),
                        depthN, nonTerm)) {
                    if (nn == pr) {
                        b.setSon(i, bb.copy());
                        break;
                    }
                    nn++;
                }
            }
        }

        // processing of sons
        for (int i = 0; i < b.getNumberOfSons(); i++) {
            bb = b.getSon(i);
            if (bb != null) {
                constr(depthN, blocksList, bb);
                setUNode(bb);//if _u_ node add it to the list
            }
        }
    }

    //////////// Destruct BLOCKS TREE //////////////////////
    public java.util.List destruct(FW_BlockInterface tree, double probub) {
        List<FW_BlockInterface> ls = new LinkedList<FW_BlockInterface>();
        int sliceType = FW_Parm.getSliceForMutation();
        if (sliceType != 0) {
            ls.add(tree);
            List<FW_BlockInterface> lsl = getSlice(tree, sliceType);
            for (FW_BlockInterface bb : lsl) {
                for (int i = 0; i < bb.getNumberOfSons(); i++) {
                    FW_BlockInterface bb1 = bb.getSon(i);
                    if (bb1 != null) {
                        if (probub > FW_Rand.rand01()) {
                            bb.setSon(i, null);
                            ls.add(bb1);
                        }
                    }
                }
            }
            return ls;
        }
        ls.add(tree);
        destr(ls, tree, probub);
        return ls;
    }

    private void destr(java.util.List blocksList, FW_BlockInterface tree, double probub) {
        FW_BlockInterface bb;

        // processing of sons
        for (int i = 0; i < tree.getNumberOfSons(); i++) {
            bb = tree.getSon(i);
            if (bb != null) {
                if (probub > FW_Rand.rand01()) {
                    tree.setSon(i, null);
                    blocksList.add(bb);
                }
                destr(blocksList, bb, probub);
            }
        }
    }

    /////// Utils

    // Tree randomRecombination
    public static void randomRecombination(FW_BlockInterface tree, int sliceType) {
        for (int i = 0; i < 10; i++) {
            randomRecombination_1(tree, sliceType);

        }
    }

    public static void randomRecombination_1(FW_BlockInterface tree, int sliceType) {
        if (tree == null) {
            return;
        }
        if (sliceType <= 0) {
            sliceType = 1+FW_Rand.rand(10);
        }
        List<FW_BlockInterface> ls = getSlice(tree, sliceType);
        int nn = ls.size();
        if (nn == 0) {
            return;
        }

        FW_BlockInterface b1 = ls.get(FW_Rand.rand(nn));
        FW_BlockInterface b2 = ls.get(FW_Rand.rand(nn));
        int n1 = b1.getNumberOfSons();
        int n2 = b2.getNumberOfSons();
        if (n1 <= 0 || n2 <= 0) {
            return;
        }

        int s1 = FW_Rand.rand(n1);
        int s2 = FW_Rand.rand(n2);

        FW_BlockInterface b1s = b1.getSon(s1);
        FW_BlockInterface b2s = b2.getSon(s2);

        /// check classes compartebility
        Class c1 = b1.getSonClass(s1);
        Class c2 = b2.getSonClass(s2);
        if(c1.isInstance(b2s) != true) return;
        if(c2.isInstance(b1s) != true) return;
        ///

        b1.setSon(s1, b2s);
        b2.setSon(s2, b1s);

    }

    // get slice from the tree
    public static java.util.List getSlice(FW_BlockInterface tree, int sliceType) {
        List<FW_BlockInterface> ls = new ArrayList<FW_BlockInterface>();
        int depth = 1;
        sliceSubTree(tree, sliceType, ls, depth);
        return ls;
    }

    private static int sliceSubTree(FW_BlockInterface tree, int sliceType, List<FW_BlockInterface> ls, int topDepth) {
        if (tree == null) {
            return -1;
        }
        int bottomDepth = 0;
        int nsons = tree.getNumberOfSons();
        if (nsons == 0) {
            bottomDepth = -1;
        } else {
            FW_BlockInterface bb;
            for (int i = 0; i < tree.getNumberOfSons(); i++) {
                bb = tree.getSon(i);
                if (bb != null) {
                    int bd = sliceSubTree(bb, sliceType, ls, topDepth + 1);
                    if (bd < bottomDepth) {
                        bottomDepth = bd;
                    }
                }
            }
            bottomDepth--;
        }
        if (sliceType == 0) { // no slice get all nodes
            ls.add(tree);
        } else if (sliceType > 0 && sliceType == topDepth) {
            ls.add(tree);
        } else if (sliceType < 0 && sliceType == bottomDepth) {
            ls.add(tree);
        }
        //  System.out.println("uuuuuuuuuuu=" + bottomDepth + " " + sliceType);
        return bottomDepth;
    }
}