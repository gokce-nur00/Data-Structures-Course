import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Watcher_List extends DoublyLinkedList{
    /*
    Takes an input file which has informations about requests of the users.
      They can be adding new user, deleting one user or wanting to know query largest earthquake.
     */
    private String watcher_list_name;
    //watcher is for all line
    private DoublyLinkedList<String> watcher;
    //watcher_list is for just names
    private DoublyLinkedList<String> watcher_list;
    public Watcher_List(String watcher_list_name) throws FileNotFoundException{
        this.watcher_list_name=watcher_list_name;
        Scanner reader=null;
        try {
            reader=new Scanner(new File(this.watcher_list_name));
            watcher_list=new DoublyLinkedList<>();
            watcher=new DoublyLinkedList<>();
            while (reader.hasNextLine()){
                String newLine=reader.nextLine();
                watcher.addLast(newLine);
            }
        }catch (FileNotFoundException e){
            System.out.println("there is no file with that name is:"+watcher_list_name);
        }

    }
    /*
    0 add -105.7 -24.3 Tom
    1 add 21.2 -38.6 Jane
    3 query-largest
    4 add -11.0 63.1 Taylor
    5 add -79.2 37.3 John
    6 add -125.1 -38.5 Henry
    8 delete Taylor
    10 query-largest
     */
    public DoublyLinkedList<String> getWatcher(){
        return this.watcher;
    }

    public DoublyLinkedList<String> getWatcher_list() {
        return watcher_list;
    }

    public String add(String allLine){
        String name=allLine.substring(allLine.lastIndexOf(" ")+1);
        this.watcher_list.addLast(name);
        return name+" is added to the watcher-list";
    }
    public String delete(String name){
        DoublyLinkedList<String> tempList=new DoublyLinkedList<>();
        while (!name.equals(this.watcher.first())){
            tempList.addFirst(this.watcher.first());
            this.watcher.removeFirst();
        }
        //when name is found in watcher it will be the first.
        this.watcher.removeFirst();
        //now name is not in the doubly linked list
        for (int i = 0; i <tempList.size() ; i++) {
            this.watcher.addFirst(tempList.first());
            tempList.removeFirst();
        }
        return name+" is removed from the watcher-list";
    }
    public String query_largest(Magnitude_Ordered_List magnitude_ordered_list){
        return magnitude_ordered_list.query_largest_line;
    }


}
