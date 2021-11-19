
import java.io.*;
import java.util.Scanner;

public class DNADatabase {
    public static void main(String[] args) {
        DnaTree myTree=new DnaTree();
        // java DNADatabase <directivesFile>
        String filename=args[0];
        Scanner reader;
        try {
            reader=new Scanner(new FileInputStream(filename));
            while (reader.hasNextLine()){
                String line=reader.nextLine();//insert A
                if (line.contains("insert")){
                    line=line.replace("insert","");// A
                    line=line.trim();//A
                    int sonuc=myTree.insert(line);
                    if (sonuc==-1){
                        System.out.println(line+" could not insert.");
                    }
                    else{
                        System.out.println("level of inserted sequence:"+sonuc);
                    }

                }
                else if (line.contains("remove")){
                    line=line.replace("remove","");
                    line=line.trim();
                    int sonuc=myTree.remove(line);
                    if (sonuc==1){
                        System.out.println("removing process is complete.");
                    }
                    else{
                        System.out.println("removing process is not complete.");
                    }
                }
                else if (line.contains("display-lengths")){
                    myTree.display(myTree.getNode(),true);
                }
                else if (line.contains("display")){
                    myTree.display(myTree.getNode(),false);
                }
                else if (line.contains("search")){
                    line=line.replace("search", "");
                    line=line.trim();
                    myTree.search(line);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("there is no file as you say.");
            System.exit(0);
        }
    }
}
