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
    
    public FW_BlockInterface construct(java.util.List blocksList,
            FW_BlockInterface blockInit, int initialDepth) {
        clearUNodes();
        maxDepth = FW_Parm.getMaxBlockDepth();
        FW_BlockInterface b = blockInit.copy();
        if (b == null) {
            b = getInitBlock(blocksList);
        }
        if (b == null) {
            return null;
        }
        constr(initialDepth, blocksList, b);
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
    
    // Atom injections
    public static final int son_max = 5; // max sons in injected node
    
    public static boolean classCompartable(FW_BlockInterface parent,FW_BlockInterface new_son, int nn_son) {
        Class c1 = parent.getSonClass(nn_son);
        return c1.isInstance(new_son);
    }
    
    // create one tree from two with root from 1 and all others from 2
    public static FW_BlockInterface rootInjection(FW_BlockInterface from_tree_0, FW_BlockInterface to_tree_0) {
        FW_BlockInterface from_tree = from_tree_0.copy();
        FW_BlockInterface to_tree = to_tree_0.copy();
        int nn_sons_from = from_tree.getNumberOfSons();
        int nn_sons = to_tree.getNumberOfSons();
        if (nn_sons_from != nn_sons) {
            return null;
        }
        boolean comparable = true;
        for (int j = 0; j < nn_sons; j++) {
            comparable = comparable && classCompartable(from_tree, to_tree.getSon(j), j);
        }
        if (comparable) {
            for (int j = 0; j < nn_sons; j++) {
                from_tree.setSon(j, to_tree.getSon(j));
            }
            //// assign transformation from old root block ////
            Class cc=Block_Prototype_Fxy.class;
            if(cc.isInstance(from_tree)&&cc.isInstance(to_tree) ){
               PaintTransformation p = ((Block_Prototype_Fxy)to_tree).paintTransformation;
               if (p != null)
                  ((Block_Prototype_Fxy)from_tree).paintTransformation = p.copy();
            }
            ///////////
        }
        return from_tree;
    }
    
    /// Random injection of o subtree ///////////////////////////////////////////////////
    public static void randomSubtreeInjections(FW_BlockInterface tree, int terminal_nodes_only,
            double probability, List<FW_BlockInterface> ls, FW_TREE_DEPTH check_depth) {
        if (ls.size() == 0) {
            return;
        }
        int depth = 1;
        //Subtree Injections:
        for (int i = 0; i < tree.getNumberOfSons(); i++) {
            randomSubtreeInjections_1(terminal_nodes_only, probability, ls,
                    tree, i, depth, check_depth);
        }
    }

    public static void randomSubtreeInjections_1(int terminal_nodes_only, double probability,
            List<FW_BlockInterface> list, FW_BlockInterface parent, int son_number, int depth,
            FW_TREE_DEPTH check_depth) {
        FW_BlockInterface block_old = parent.getSon(son_number);
        if (block_old == null) {
            return;
        }
        // change randomly block_old and modify links
        int nn_sons = block_old.getNumberOfSons();
        if (terminal_nodes_only == 0 || nn_sons == 0) {
            if (check_depth.isChangable(depth) && (FW_Rand.rand01() < probability)) {
                FW_BlockInterface root = (FW_BlockInterface) FW_Rand.getRandObject(list);
                boolean comparable = classCompartable(parent, root, son_number);
                if (comparable) {
                    FW_BlockInterface block = FW_Parm.getCurrentBuilder().construct(
                            list, root,depth);
                    if (block == null) {
                        return;
                    }
                    parent.setSon(son_number, block);
                }
                return;
            }
        }

        // recursively do the same with sons of block_old
        for (int i = 0; i < block_old.getNumberOfSons(); i++) {
            randomSubtreeInjections_1(terminal_nodes_only, probability, list,
                    block_old, i, depth + 1, check_depth);
        }

    }

    /// Random injection of one node /////////////////////////////////////////////////////
    public static void randomInjections(FW_BlockInterface tree, int terminal_nodes_only,
            double probability, List<FW_BlockInterface> ls) {

        // create the structure of nodes to inject
        List<List<FW_BlockInterface>> metalist = new ArrayList<List<FW_BlockInterface>>();
        for (int i = 0; i < son_max; i++) {
            metalist.add(new ArrayList<FW_BlockInterface>());
        }

        for (FW_BlockInterface b : ls) {
            FW_BlockInterface b_copy = b.copy();
            int nn = b_copy.getNumberOfSons();
            if (nn < son_max) {
                metalist.get(nn).add(b_copy);
            }
        }

        //Nodes Injections:
        for (int i = 0; i < tree.getNumberOfSons(); i++) {
            randomInjections_1(terminal_nodes_only, probability, metalist,
                    tree, i);
        }
    }
     
     public static void randomInjections_1(int terminal_nodes_only, double probability,
            List<List<FW_BlockInterface>> metalist, FW_BlockInterface parent, int son_number) {
        FW_BlockInterface block_old = parent.getSon(son_number);
        if (block_old == null) {
            return;
        }
        // change randomly block_old and modify links
        int nn_sons = block_old.getNumberOfSons();
        if (terminal_nodes_only == 0 || nn_sons == 0) {
            if (FW_Rand.rand01() < probability) {
                FW_BlockInterface block = null;
                if (nn_sons < son_max) {
                    List<FW_BlockInterface> ls = metalist.get(nn_sons);
                    int size = FW_Rand.rand(ls.size());
                    if (size > 0) {
                        int node = FW_Rand.rand(size);
                        block = ls.get(node).copy();
                        boolean comparable = classCompartable(parent, block, son_number);
                        for (int j = 0; j < nn_sons; j++) {
                            comparable = comparable && classCompartable(block, block_old.getSon(j), j);
                        }
                        if (comparable) {
                            parent.setSon(son_number, block);
                            for (int j = 0; j < nn_sons; j++) {
                                block.setSon(j, block_old.getSon(j));
                            }
                            block_old = block;
                        }
                    }
                //    if (block != null) {
                //        block_old = block;
                //    }
                }
            }
        }
        // recursively do the same with sons of block_old
        for (int i = 0; i < block_old.getNumberOfSons(); i++) {
            randomInjections_1(terminal_nodes_only, probability, metalist,
                    block_old, i);
        }

    }
    
    ////////////  TREE DESTRUCTOR//////////////////////
    
    public java.util.List<FW_BlockInterface> destructor(FW_BlockInterface tree, double start_probub, int depthMin, int depthMax, double trim_probub) {
        List<FW_BlockInterface> ls = new LinkedList<FW_BlockInterface>();
        int depth = 0;
        destructor_1(ls, tree, start_probub, depthMin, depthMax, trim_probub, depth);
        return ls;
    }
    
     private void destructor_1(List<FW_BlockInterface> ls, FW_BlockInterface tree,
            double start_probub, int depthMin, int depthMax, double trim_probub, int depth) {
        if(tree==null) return;
        if (depth == depthMin) {
            if (FW_Rand.rand01() < start_probub) {
                ls.add(tree);
                destructor_2(tree, depthMax, trim_probub, depth);
            }
            return;
        }
        for (int i = 0; i < tree.getNumberOfSons(); i++) {
            if (depth < depthMin) {
                FW_BlockInterface bb = tree.getSon(i);
                destructor_1(ls, bb, start_probub, depthMin, depthMax, trim_probub, depth + 1);
            }
        }
        return;
    }  
     
     private void destructor_2( FW_BlockInterface tree,
             int depthMax, double trim_probub, int depth) {
        if(tree==null) return;
        for (int i = 0; i < tree.getNumberOfSons(); i++) {
            if (depth >= depthMax) {
                tree.setSon(i, null);
            } else if (FW_Rand.rand01() < trim_probub) {
                tree.setSon(i, null);
            } else {
                FW_BlockInterface bb = tree.getSon(i);
                destructor_2(bb, depthMax, trim_probub, depth + 1);
            }

        }
        
    }  
   
    
     
    // Tree randomRecombination /////////////////////////////////////////////////
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
