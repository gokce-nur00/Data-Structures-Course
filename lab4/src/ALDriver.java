public class ALDriver {
    public static void main(String[] args) {
        ArrayList<Integer> L=new ArrayList<Integer>();
        ALDriver t=new ALDriver();
        ArrayList.ArrayListIterator temp=L.listIterator();
        temp.add(3);
        temp.add(5);
        temp.add(4);
        temp.add(8);
        System.out.println(temp);
        temp=t.addSumBetween(temp);
        System.out.println(temp);

    }
    public ArrayList.ArrayListIterator addSumBetween(ArrayList.ArrayListIterator L){
        ArrayList.ArrayListIterator temp=L;
        while (L.hasNext()){
            temp.set(L.next());
        }
        int sayac=0;
        Integer sum=0;
        while (temp.hasNext()){
            if (sayac==0){
                sum=(Integer) temp.next();
                sayac++;
            }
            if (sayac==1){
                L.next();
                sum+=(Integer) temp.next();
                L.add(sum);
                sayac--;
            }
        }
        return L;
    }
}
