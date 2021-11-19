import java.util.Iterator;

public class SwapSortDriver extends LinkedPositionalList<String> {
    private static LinkedPositionalList<String> lPList;
    public SwapSortDriver(){
        lPList=new LinkedPositionalList<String>();
    }
    /*public LinkedPositionalList<String> getList(){
        return lPList;
    }*/

    public static void main(String[] args) {
        SwapSortDriver the=new SwapSortDriver();
        //LinkedPositionalList<String> myList=the.getList();
        lPList.addFirst("a");
        lPList.addFirst("b");
        lPList.addFirst("g");
        lPList.addFirst("h");
        lPList.addFirst("c");
        System.out.println(lPList);
        the.sort();
        System.out.println(lPList);
    }

    private void swap(Position<String> node1, Position<String> node2){
        //cannot creat new node in this progress
        lPList.set(lPList.before(lPList.after(node1)), lPList.after(node1).getElement());
        //node1.getPrev().setNext(node1.getNext());
        lPList.set(lPList.after(lPList.before(node1)),lPList.before(node1).getElement());
        //node1.getNext().setPrev(node1.getPrev());
        lPList.set(lPList.after(node1),lPList.before(node1).getElement());
        //node1.setNext(node1.getPrev());
        //now before and after nodes of node1 are connected.
        //same thing for node2
        lPList.set(lPList.before(lPList.after(node2)),lPList.after(node2).getElement());
        //node2.getPrev().setNext(node2.getNext());
        lPList.set(lPList.after(lPList.before(node2)),lPList.before(node2).getElement());
        //node2.getNext().setPrev(node2.getPrev());
        lPList.set(lPList.after(node2),lPList.before(node2).getElement());
        //node2.setNext(node2.getPrev());
        //node1.next=node1.prev, node2.next=node2.prev
        lPList.set(lPList.before(node1),lPList.before(node2).getElement());
        //node1.setPrev(node2.getPrev());//node1.prev=node2.prev
        lPList.set(lPList.before(node2),lPList.after(node1).getElement());
        //node2.setPrev(node1.getNext());//node2.prev=node1.next(node1.next=node1.prev before above)
        //node1.next=node1.prev, node2.next=node2.prev
        lPList.set(lPList.after(node1),lPList.before(node1).getElement());
        //node1.setNext(node1.getPrev());
        lPList.set(lPList.after(node2),lPList.before(node2).getElement());
        //node2.setNext(node2.getPrev());
        //setting prev and next
        //node1.next=node1.next.next , node1.next.prev=node1 , node1.prev.next=node1
        lPList.set(lPList.after(node1),lPList.after(lPList.after(node1)).getElement());
        //node1.setNext(node1.getNext().getNext());
        lPList.set(lPList.after(lPList.before(node1)),node1.getElement());
        //node1.getNext().setPrev(node1);
        lPList.set(lPList.before(lPList.after(node1)),node1.getElement());
        //node1.getPrev().setNext(node1);
        lPList.set(lPList.after(node2),lPList.after(lPList.after(node2)).getElement());
        //node2.setNext(node2.getNext().getNext());
        lPList.set(lPList.after(lPList.before(node2)),node2.getElement());
        //node2.getNext().setPrev(node2);
        lPList.set(lPList.before(lPList.after(node2)),node2.getElement());
        //node2.getPrev().setNext(node2);

    }

    public void sort(){
       Position<String> firstElement=lPList.first();
       Position<String> secondElement=lPList.after(firstElement);
        for (int i = 0; i <size()-2 ; i++) {
            for (int j = 1; j <size()-2 ; j++) {
                if (firstElement.getElement().compareTo(secondElement.getElement())>0){
                    swap(firstElement,secondElement);
                }
                firstElement=lPList.after(firstElement);
                secondElement=lPList.after(secondElement);
            }
        }

    }


}
