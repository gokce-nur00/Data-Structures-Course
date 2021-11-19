

public class HeapSort {
    HeapPriorityQueue<Integer,Integer> heap;

    public static void main(String[] args) {
        HeapPriorityQueue<Integer,Integer> heap=new HeapPriorityQueue<>();
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(0,8);
        arr.add(0,23);
        arr.add(0,32);
        arr.add(0,9);
        arr.add(0,17);
        System.out.println("array:");
        heapSort(arr);

        heap.insert(8,2);
        heap.insert(23,4);
        heap.insert(32,5);
        heap.insert(9,6);
        heap.insert(17,8);
        System.out.println("");
        System.out.println("heap:");
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.heap.get(i).getKey()+" ");
        }
        System.out.println("");
        Integer oldKey=heap.setKey(2,14);

        if (oldKey==null){
            System.out.println("null");
        }
        else
            System.out.println("old:"+oldKey);
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.heap.get(i).getKey()+" ");
        }
        System.out.println("");
        Integer in=heap.removeEntry(1);
        if (in==null)
            System.out.println("null");
        else
            System.out.println("removed entry:"+in);

        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.heap.get(i).getKey()+" ");
        }
    }

    public static void heapSort(ArrayList<Integer> arr){
        HeapPriorityQueue<Integer,Integer> tempHeap=new HeapPriorityQueue<>();
        for (int i = 0; i < arr.size(); i++) {
            tempHeap.insert(arr.get(i),i);
        }
        for (int i = 0; i <arr.size()-1 ; i++) {
            int mid=i;
            for (int j = i+1; j < arr.size(); j++) {
                if (tempHeap.heap.get(j).getKey()<tempHeap.heap.get(mid).getKey()){
                    mid=j;
                }
            }
            tempHeap.swap(i,mid);
        }
        for (int i = 0; i <arr.size() ; i++) {
            arr.remove(0);
        }
        for (int i = 0; i <tempHeap.size() ; i++) {
            arr.add(i,tempHeap.heap.get(i).getKey());
            System.out.print(arr.get(i)+" ");
        }

    }


}
