public class Part3Driver {

    public static void main(String[] args) {
        HeapPriorityQueue<Integer,Integer> myList=new HeapPriorityQueue<>();
        //HeapPriorityQueue metodlarinin isim veya islevlerinde degisiklik yaptim
        //upheap, downheap, min->max, removeMin->removeMax, sanityCheck

        myList.insert(10,0);
        System.out.println("Max is:"+myList.max().getKey());

        myList.insert(3,0);
        System.out.println("Max is:"+myList.max().getKey());

        myList.insert(5,0);
        System.out.println("Max is:"+myList.max().getKey());

        myList.insert(12,0);
        System.out.println("Max is:"+myList.max().getKey());

        myList.insert(25,0);
        System.out.println("Max is:"+myList.max().getKey());

        myList.insert(11,0);
        System.out.println("Max is:"+myList.max().getKey());

        myList.insert(99,0);
        System.out.println("Max is:"+myList.max().getKey());

        System.out.println("");
        System.out.println("THE HEAP IS:");

        while(!myList.isEmpty()){

            System.out.println(myList.removeMax().getKey());
            System.out.println("");
        }

        System.out.println("~D O N E~");
    }
}
