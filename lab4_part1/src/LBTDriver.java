import java.util.Iterator;
import java.util.Scanner;
public class LBTDriver {
    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        LinkedBinaryTree<Integer> linkedBinaryTree=new LinkedBinaryTree<>();
        boolean temp=true;
        while (temp){
            int num=keyboard.nextInt();
            if (num==-1)
                break;
            linkedBinaryTree.addOrdered(num);
        }
        Iterator<Integer> iter=linkedBinaryTree.iterator();
        while (iter.hasNext()){
            System.out.print(iter.next()+" ");
        }

    }
}
