public class Magnitude_Ordered_List extends ArrayList {
    ArrayList<String> magnitude_list;
    private int lastIndexOfArrayList;
    String query_largest_line;
    private int index;
    public Magnitude_Ordered_List(Queue<String> earthquake_queue){
        this.index=0;
        this.magnitude_list=new ArrayList<String>(1000);
        this.lastIndexOfArrayList=earthquake_queue.size();
        for (int i = 0; i < earthquake_queue.size(); i++) {
            magnitude_list.add(i,(String) earthquake_queue.first());
            earthquake_queue.dequeue();
        }
        this.query_largest_line="";
    }

    public int getLastIndexOfArrayList() {
        return lastIndexOfArrayList;
    }

    public String query_largest(int time){
        String allLine=this.magnitude_list.get(index);
        String magnitude=allLine.substring(allLine.lastIndexOf("<magnitude>")+1, allLine.indexOf("</magnitude>"));
        String place=allLine.substring(allLine.lastIndexOf("<place>")+1, allLine.indexOf("</place>"));
        return "Largest earthquake in the past 6 hours:\n"+"Magnitude "+magnitude+" at "+place;
    }

    /*try{
            Watcher_List watcher_list=new Watcher_List(watcherFileName);
            Earthquake_Queue earthquake_queue=new Earthquake_Queue(earthquakeFileName);
            Magnitude_Ordered_List magnitude_ordered_list=new Magnitude_Ordered_List(earthquake_queue.getAllInfo());
            DoublyLinkedList<String> watcherListAllLine=watcher_list.getWatcher();
            while (true){
                String watcherAllLine=watcherListAllLine.first();
                int watcherAllLineTime=Integer.parseInt(watcherAllLine.substring(0,watcherAllLine.indexOf(" ")));

                if (watcherAllLineTime==time){
                    if (watcherAllLine.contains("add")){//add istegi var
                        System.out.println(watcher_list.add(watcherAllLine));
                    }
                    else if (watcherAllLine.contains("delete")){//delete istegi var
                        System.out.println(watcher_list.delete(watcherAllLine.substring(watcherAllLine.lastIndexOf(" ")+1)));

                    }
                    else if (watcherAllLine.contains("query-largest")){//query-largest istegi var
                        System.out.println(magnitude_ordered_list.query_largest(time));
                    }
                    watcher_list.removeFirst();
                    watcherListAllLine.removeFirst();
                }
                else{//earthquake e bakilacak
                    earthquake_queue.setTime(time);
                }


            }


        }catch (Exception e){

        }*/
}
