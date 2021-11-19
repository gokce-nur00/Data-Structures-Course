public class DnaTree {

    protected static class TreeNode{
        private int level;
        //this class is created to get and set level of all kind of nodes

        protected int getLevel() {
            return level;
        }

        protected void setLevel(int level) {
            this.level = level;
        }

        protected String name(){
            return "";
        }



    }

    private static class EmptyNode extends TreeNode{
        //this class is for null sequence
        //save space
        TreeNode parent;

        public boolean isEmpty(){
            return true;
        }
        @Override
        public int getLevel(){
            return 0;
        }

        @Override
        public String name(){
            return "E";
        }

        public void setParent(TreeNode theNode){
            parent=theNode;
        }

        public TreeNode getParent() {
            return parent;
        }
    }

    private static class InternalNode extends TreeNode{
        //this class is created to store sequences.
        //there is 5 different sequence type that are A, C, T, G and final one is $
        //$ = final
        // Internal nodes serve only as placeholders to help direct search,
        // they store no data.
        @Override
        public String name(){
            return "I";
        }

        private TreeNode A, C, T, G, FINAL;

        public InternalNode(int level){
            A=new EmptyNode(); C=new EmptyNode(); T=new EmptyNode(); G=new EmptyNode(); FINAL=new EmptyNode();
            setLevel(level);
        }

        public void setNode(TreeNode node,int which){
            switch (which)
            {
                case 1:
                    A=node;
                    break;
                case 2:
                    C=node;
                    break;
                case 3:
                    G=node;
                    break;
                case 4:
                    T=node;
                    break;
                case 5:
                    FINAL=node;
                    break;
            }
        }

        public TreeNode getNode(int which){
            TreeNode returnNode=null;
            switch (which){
                case 1:
                    returnNode=A;
                    break;
                case 2:
                    returnNode=C;
                    break;
                case 3:
                    returnNode=G;
                    break;
                case 4:
                    returnNode=T;
                    break;
                case 5:
                    returnNode=FINAL;
                    break;
            }
            return returnNode;
        }

        public TreeNode getA() {
            return A;
        }

        public void setA(TreeNode a) {
            A = a;
        }

        public TreeNode getC() {
            return C;
        }

        public void setC(TreeNode c) {
            C = c;
        }

        public TreeNode getG() {
            return G;
        }

        public void setG(TreeNode g) {
            G = g;
        }

        public TreeNode getT() {
            return T;
        }

        public void setT(TreeNode t) {
            T = t;
        }

        public TreeNode getFINAL() {
            return FINAL;
        }

        public void setFINAL(TreeNode FINAL) {
            this.FINAL = FINAL;
        }

    }

    private static class LeafNode extends TreeNode{
        //DNA trees store sequences only in their leaf nodes.
        //Some leaf nodes may be empty
        //A leaf node is either empty, or stores a single sequence.
        // that leaf node must be converted to an internal node with 5 children.
        private String sequence;
        private TreeNode parent;
        protected LeafNode(String sequence){
            this.sequence=sequence;
            this.setLevel(0);
        }

        @Override
        public String name(){
            return getSequence();
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public boolean isEmpty(){
            if (sequence.equals(""))
                return true;
            return false;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public TreeNode getParent() {
            return parent;
        }
    }

    //I'm gonna use this node to learn tree's sequence what it has.
    private TreeNode node;
    //that is for searched sequences

    private int size;
    //Firstly, node has to be empty node
    public DnaTree(){
        node=new EmptyNode();
    }

    private int getSize(){
        return size;
    }

    public TreeNode getNode() {
        return node;
    }

    public int insert(String sequence){
        EmptyNode emptyNode=new EmptyNode();
        LeafNode leafNode=new LeafNode("");
        InternalNode internalNode=new InternalNode(0);
        int which;
        if (node.getClass().equals(emptyNode.getClass())){//if it is empty
            leafNode.setSequence(sequence);
            node=leafNode;
            return node.getLevel();
        }
        if (node.getClass().equals(leafNode.getClass())){//if it is leaf
            leafNode=(LeafNode) node;
            if (sequence.equals(leafNode.getSequence()))//sequence is already exist
                return -1;
            else
                setInternal(leafNode, 0);//node is leafNode
        }
        else{
            //if the sequence's length is smaller than level that means
            //we set up the sequence to $ as FINAL
            if (sequence.length()< node.getLevel())
                which=5;
            else{//if the seq's  length is bigger than level that means
                //we set up the char of level index of the sequence
                which=returnWhich(sequence.charAt(node.getLevel()));
            }
            InternalNode second=(InternalNode)node;
            TreeNode son=second.getNode(which);
            //is son empty?
            if (son.getClass().equals(emptyNode.getClass())){
                leafNode=new LeafNode(sequence);
                leafNode.setLevel(node.getLevel()+1);
                ((InternalNode) node).setNode(leafNode,which);
                return leafNode.getLevel();
            }
            if (son.getClass().equals(leafNode.getClass())){
                //if the sequence is already in the node
                if (((LeafNode) son).getSequence().equals(sequence)){
                    return -1;
                }
                //Otherwise, replace the leaf node
                second=new InternalNode(son.getLevel());
                second.setNode(son, returnWhich(((LeafNode) son).getSequence().charAt(son.getLevel())));
                ((InternalNode) node).setNode(second, returnWhich(sequence.charAt(node.getLevel()+1)));
                son.setLevel(son.getLevel()+1);
                son=second;
            }

        }
        return insert(sequence);
    }
    /*
    helper method for insertion
    In this method we are gonna set Internal node instead of leaf node and slide leaf
     */
    private void setInternal(LeafNode leafNode, int level){
            InternalNode internalNode=new InternalNode(level);
            internalNode.setNode(leafNode, returnWhich(leafNode.getSequence().charAt(level)));
            leafNode.setLevel(level+1);
            node=leafNode;
    }

    //This method is designed to understand which information
    //it is an internal node according to the given chart.
    private int returnWhich(char c){
        switch (c){
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
                return 4;
            case 'E':
                return 5;
        }
        return -1;
    }
    /*
    @param sequence -> the sequence is gonna removed from tree with this method
     */
    public int remove(String sequence){
        EmptyNode emptyNode=new EmptyNode();
        LeafNode leafNode=new LeafNode("");
        InternalNode internalNode=new InternalNode(0);
        //if there is no node
        if (node.getClass().equals(emptyNode.getClass()))
            return 0;
        //if there is a leaf node
        if (node.getClass().equals(leafNode.getClass())){
            if (((LeafNode)node).getSequence().equals(sequence)){
                //we found it
                node=emptyNode;
                return 1;
            }
            return 0;
        }
        //InternalNode
        return removeSequence(sequence,(InternalNode) node);
    }

    /*
    helper method for remove
    this method is used for just Internal nodes
     */
    private int removeSequence(String sequence, InternalNode next){
       int which;
       if (sequence.length()<=node.getLevel()){//that means we have to set the sequence to $ node
           which=5;
       }
       which=returnWhich(sequence.charAt(next.getLevel()));//changing type
       TreeNode nextNode=next.getNode(which);//we take the node from tree to set

       if (nextNode.getClass().equals(EmptyNode.class)){//if it is empty we don't have it
           return 0;
       }
       if (nextNode.getClass().equals((LeafNode.class))){//if it is empty that means there is a sequence
           if (sequence.equals(((LeafNode) nextNode).getSequence()))//if the nextNode's sequence is equal to the sequence which is gonna be removed
           {
               next.setNode(new EmptyNode(),which);//change the node type
               return 1;//remove method is finished with success
           }
       }
       if (nextNode.getClass().equals(InternalNode.class)){//if the node is again internal
           return removeSequence(sequence,(InternalNode) nextNode);//return recursive
       }

        return 0;//if anyone is not true the removing cannot be done
    }

    /*
    If kosul=true, display method is running as display-length
    If kosul= false, display is running just String
     */
    public void display(TreeNode treeNode, boolean kosul){
        EmptyNode emptyNode=new EmptyNode();
        LeafNode leafNode=new LeafNode(null);
        InternalNode internalNode=new InternalNode(0);


        dot(treeNode.getLevel());//helper method

        if (treeNode.getClass().equals(emptyNode.getClass()))
            System.out.println(((EmptyNode) treeNode).name());//E
        if (treeNode.getClass().equals(leafNode.getClass())) {
            System.out.print(((LeafNode) treeNode).name());//sequence
            if (kosul){//kosul is for printing length as display-length method
                System.out.println(" length:"+((LeafNode)treeNode).getSequence().length());
            }
            System.out.println("");
        }
        else{
            System.out.println(((InternalNode) treeNode).name());//I
            printInternal((InternalNode) treeNode, kosul);//helper method

        }

    }

    //the method is dotting depend on the level of nodes
    //It is used in display
    private void dot(int level){
        for (int i = 0; i <level ; i++) {
            System.out.print(".");
        }
    }

    /*
    helper method for display method
    It is used for just Internal nodes' childs
     */
    private void printInternal(InternalNode internalNode, boolean kosul){
        for (int i = 1; i <=5 ; i++) {
            display(internalNode.getNode(i),kosul);
        }
    }

    /*
    search
    the method is searching the sequence
    if the sequence's last char is equal to $
    that means we just print the number of visited nodes and exact sequence
    if the sequence's last char is not equal to $
    we print the number of visited nodes and visited all leaf's sequences
     */
    public void search(String sequence){
        if (node.getClass().equals(((new EmptyNode()).getClass()))){
            System.out.println("Tree is empty");
        }
        else if (sequence.charAt(sequence.length()-1)=='$'){
            String sequence1 = sequence.substring(0, sequence.length() - 2);//without $
            searchHelp(sequence1,node, node.getLevel());//helper method
            System.out.println("the number of nodes visited:"+getReal());//real is the number of visited nodes
            System.out.println("sequence:"+ sequence1);//just searched sequence
        }
        else{//there is no $ at the end of the sequence
            searchHelp(sequence,node,node.getLevel());
            System.out.println("the number of nodes visited:"+getReal());
            if (!allString.contains(sequence)){//if the sequence is not in the tree
                System.out.println(sequence+" couldn't be found.");
            }
            else
                printAllString();//print searched sequences
        }



    }
    /*
    helper method for search method.
    this method is used to real searching.
    @param sequence is variable which is searched
    @param next is node which is analyzing for sequence
    @param i is used for counting visited nodes
     */
    private void searchHelp(String sequence, TreeNode next,int i){
        if (next.getClass().equals(InternalNode.class)){
            i++;
            setReal(i);
            searchHelp(sequence, ((InternalNode) next).getNode(returnWhich(sequence.charAt(next.getLevel()))),i);
        }
        if (next.getClass().equals((LeafNode.class))){
            if (((LeafNode) next).getSequence().equals(sequence)){
                i++;
                setReal(i);
                sequencesOfsearch(sequence);
            }
            sequencesOfsearch(((LeafNode) next).getSequence());//adding the sequence of the leaf node
        }

    }

    private int real=0;

    private int getReal(){
        return real;
    }

    private void setReal(int i){
        real=i;
    }

    /*
    helper method for searchHelp
    this method is used for adding the next found sequence to allString variable.
     */
    private String allString="";
    private void sequencesOfsearch(String sequence){
        allString+=","+sequence;
    }

    /*
    helper method for search
    this method is used for printing sequences during search the sequence.
     */
    private void printAllString(){
        int count=0;
        for (int i = 0; i <allString.length() ; i++) {
            System.out.println("sequence: "+allString.substring(0,allString.indexOf(',')));
            i=allString.indexOf(',');
            allString=allString.substring(0,i);
        }
    }

}
