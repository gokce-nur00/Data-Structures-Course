import java.util.Iterator;

public class DriverMap {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap=new TreeMap<>();
        Integer i=treeMap.put(9,9);
        display(treeMap);
        int count=treeMap.countLessThan(9);
        System.out.println(count);
        boolean kosul=isTrue(treeMap, treeMap.root());
        System.out.println(kosul);

        treeMap.put(8,9);
        display(treeMap);
        count=treeMap.countLessThan(9);
        System.out.println(count);
        kosul=isTrue(treeMap, treeMap.root());
        System.out.println(kosul);

        System.out.println("preOrderSuc:");
        Integer t=treeMap.preOrderSuccessor(9);
        System.out.println(t);

        treeMap.put(15,9);
        display(treeMap);
        count=treeMap.countLessThan(9);
        System.out.println(count);
        kosul=isTrue(treeMap, treeMap.root());
        System.out.println(kosul);


        treeMap.put(3,9);
        display(treeMap);
        count=treeMap.countLessThan(9);
        System.out.println(count);
        kosul=isTrue(treeMap, treeMap.root());
        System.out.println(kosul);


        treeMap.put(7, 9);
        display(treeMap);
        kosul=isTrue(treeMap, treeMap.root());
        System.out.println(kosul);

        treeMap.put(25,9);
        display(treeMap);
        kosul=isTrue(treeMap, treeMap.root());
        System.out.println(kosul);

        try {
            treeMap.remove(6);
            display(treeMap);
        }catch (IllegalArgumentException e){
            System.out.println("there is no key as you search:6");
        }
        System.out.println("**********************");

        Entry<Integer,Integer> entry=treeMap.lastEntry();
        System.out.println("last entry:"+entry);

        entry=treeMap.firstEntry();
        System.out.println("first entry:"+entry);


        entry=treeMap.lowerEntry(9);
        System.out.println("lower entry of 9:"+entry);

        try {
            entry=treeMap.lowerEntry(12);
            System.out.println("lower entry of 12:"+entry);
        }catch (IllegalArgumentException e){
            System.out.println("there is no key as you search:6");
        }

        entry=treeMap.floorEntry(9);
        System.out.println("floor entry of 9:"+entry);

        try {
            entry=treeMap.floorEntry(12);
            System.out.println("floor entry of 12:"+entry);
        }catch (IllegalArgumentException e){
            System.out.println("there is no key as you search:12");
        }
        entry= treeMap.ceilingEntry(9);
        System.out.println("ceiling entry of 9:"+entry);

        try {
            entry=treeMap.ceilingEntry(12);
            System.out.println("ceiling entry of 12:"+entry);
        }catch (IllegalArgumentException e){
            System.out.println("there is no key as you search:12");
        }
        entry=treeMap.higherEntry(9);
        System.out.println("higher entry of 9:"+entry);

        try {
            entry=treeMap.higherEntry(12);
            System.out.println("higher entry of 12:"+entry);
        }catch (IllegalArgumentException e){
            System.out.println("there is no key as you search:12");
        }






    }

    public static void display(TreeMap<Integer,Integer> treeMap ){
        System.out.println("***************************");
        System.out.println("display:");
        //preorder and indenting depend on depth,
        Iterable<Position<Entry<Integer, Integer>>> iterable= treeMap.tree.preorder();
        Iterator<Position<Entry<Integer, Integer>>> iterator=iterable.iterator();

        while (iterator.hasNext()){
            Position<Entry<Integer, Integer>> t=iterator.next();
            if (t.getElement()!=null){
                for (int j = 0; j <treeMap.tree.depth(t) ; j++)
                    System.out.print("*");
                System.out.println(t.getElement());
            }

        }
        System.out.println("***************************");
    }

    public static boolean isTrue(TreeMap<Integer,Integer> treeMap, Position<Entry<Integer, Integer>> temp){
        Position<Entry<Integer, Integer>> childTemp=null;
        if (temp.getElement()==null)
            return true;
        else if (treeMap.left(temp).getElement()!=null){
            childTemp=treeMap.left(temp);
            if (treeMap.compare(temp.getElement().getKey(), childTemp.getElement().getKey())>=0)
                return isTrue(treeMap, treeMap.left(temp));
            else if (treeMap.compare(temp.getElement().getKey(), childTemp.getElement().getKey())<=0)
                return false;
        }
        else if (treeMap.right(temp).getElement()!=null){
            childTemp=treeMap.right(temp);
            if (treeMap.compare(temp.getElement().getKey(), childTemp.getElement().getKey())<=0)
                return isTrue(treeMap, treeMap.right(temp));
            else if (treeMap.compare(temp.getElement().getKey(), childTemp.getElement().getKey())>=0)
                return false;
        }
        return true;
    }

    //calismayan ama silmeye kiyamadigim methodum
    /*public static boolean trueMethod(TreeMap<Integer,Integer> treeMap, Position<Entry<Integer,Integer>> temp){
        if (treeMap.left(temp) != null &&treeMap.compare(treeMap.left(temp).getElement().getKey(), temp.getElement().getKey())>0){
            return false;
        }
        else if (treeMap.right(temp) != null &&treeMap.compare(treeMap.right(temp).getElement().getKey(), temp.getElement().getKey())<0){
            return false;
        }
        else if (treeMap.left(temp) != null &&treeMap.compare(treeMap.left(temp).getElement().getKey(), temp.getElement().getKey())<=0)
        {
            return trueMethod(treeMap, treeMap.left(temp));
        }
        else if (treeMap.right(temp) != null &&treeMap.compare(treeMap.right(temp).getElement().getKey(), temp.getElement().getKey())>=0)
        {
            return trueMethod(treeMap, treeMap.right(temp));
        }
        return  true;
    }
    */


}
