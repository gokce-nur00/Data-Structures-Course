import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class DictionaryDriver
{
    private static int aver=0;
    private static int max=0;
    private static int hash=24520;


    public static void main(String[] args) {
        ProbeHashMap<String,String>  probeHashMap=new ProbeHashMap<>(24520);
        String filename="dictionary.txt";
        insertion(probeHashMap,filename);
        String searchname="search.txt";

        System.out.println("average number of probes during insertions:"+aver);
        System.out.println("max number of probes during insertion:"+max);

        search(probeHashMap,searchname);


    }

    private static void insertion(ProbeHashMap<String,String> probeHashMap,String filename){
        Scanner reader=null;
        int i=0;
        try {
            reader=new Scanner(new FileInputStream(filename));
            while (reader.hasNextLine()){
                String next=reader.nextLine();
                probeHashMap.bucketPut(i, next,null);
                i++;
                aver=probeHashMap.ortProbe(aver,next,hash,i);
                max= probeHashMap.maxProbe(max,next,hash);
            }
        }catch (NoSuchElementException | FileNotFoundException e){ }
    }

    private static void search(ProbeHashMap<String,String> probeHashMap,String filename){
        Scanner reader=null;
        try {
            reader=new Scanner(new FileInputStream(filename));
            while (reader.hasNextLine()){
                String next=reader.nextLine();
                next=next.toLowerCase();
                String temp=next;
                if (probeHashMap.get(next)==null) {
                    System.out.print(next+": ");
                    for (int i = 0; i < next.length() / 2; i++) {
                        /*
                        best=be st-> 4 harf icin 2 arama
                        length/2->2
                        0. ve 1. index=2*i, 2*i+1(i=0)
                        2. ve 3. index=2*i,2*i+1(i=1)
                         */
                        String first = next.substring(2 * i, 2 * i + 1);
                        String second = next.substring(2 * i + 1, 2 * i + 2);
                        next = next.substring(0, 2 * i) + second + first + next.substring(2 * i + 2);
                        if (probeHashMap.get(next)!=null){
                            System.out.print(next+" ");
                        }
                        next=temp;

                    }
                }
            }

        }catch (NoSuchElementException | FileNotFoundException e){

        }
    }

}
