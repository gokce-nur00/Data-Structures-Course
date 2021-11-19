

public class Driver {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> binaryTree=new LinkedBinaryTree<>();
        Position<Integer> a=binaryTree.addRoot(15);
        Position<Integer> b=binaryTree.addLeft(a,12);
        Position<Integer> c=binaryTree.addRight(a,20);
        Position<Integer> d=binaryTree.addLeft(b,5);
        Position<Integer> e=binaryTree.addRight(b,13);
        Position<Integer> f=binaryTree.addLeft(c,18);
        Position<Integer> g=binaryTree.addRight(c,22);
        System.out.println("preorder:");
        Iterable<Position<Integer>> iterable=binaryTree.preorder();
        for (Position<Integer> temp: iterable) {
            System.out.print(temp.getElement()+" ");

        }
        System.out.println("");
        System.out.println("postorder:");
        Iterable<Position<Integer>> iterable2=binaryTree.postorder();
        for (Position<Integer> temp: iterable2) {
            System.out.print(temp.getElement()+" ");

        }
        System.out.println("");
        System.out.println("countDescendants:"+ binaryTree.countDescendants(binaryTree.root()));
        System.out.println("contains 5:"+binaryTree.contains(5));
        System.out.println("contains 6:"+binaryTree.contains(6));
        System.out.println("minimum:"+binaryTree.minimum(binaryTree));
        System.out.println("sum:"+binaryTree.sum(binaryTree));


    }

}
