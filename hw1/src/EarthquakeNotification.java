import java.util.*;
import java.io.*;
public class EarthquakeNotification {
    public static void main(String[] args) {

        String earthquakeFileName=args[args.length-1];
        earthquakeFileName=earthquakeFileName.substring(1,earthquakeFileName.length()-1);//without <,>
        String watcherFileName=args[args.length-2];
        watcherFileName=watcherFileName.substring(1,watcherFileName.length()-1);


        int time=0;

        Scanner earthquake_reader=null;
        Scanner watcher_reader=null;

        DoublyLinkedList<String> watcher_list=new DoublyLinkedList<>();
        Queue<String> earthquake_queue=new Queue<>();//Queue classini linkedlist ile modifiye ettim
        //linked list based queue
        ArrayList<String> magnitude_ordered_list=new ArrayList<>(1000);


        try {
            earthquake_reader=new Scanner(new File(earthquakeFileName));
            watcher_reader=new Scanner(new File(watcherFileName));
            int i=0;
            int countOfmagnitude=0;

            boolean watchBool=true;//true ise listeden islem line i alinacak. yanlis ise beklenecek
            boolean earthBool=true;//true ise listeden earthquake bilgileri cekilecek. yanlis ise beklenecek

            String watchLine="";
            String[] earthquake=new String[7];//bir deprem 7 satir bilgi girisine sahip

            while (i<watcher_list.size()){

                if (watchBool&&watcher_reader.hasNextLine()){//linedaki isleme bak
                    watchLine=watcher_reader.nextLine();
                    i++;

                    if (Integer.parseInt(watchLine.substring(0,watchLine.indexOf(" ")))!=time){
                        //islem line'inin time i uygun degilse
                        watchBool=false;

                    }
                }
                if (watchLine.contains("add")){
                    //watcher-liste kisiyi ekle
                    watcher_list.addLast(watchLine.substring(watchLine.lastIndexOf("")+1));
                    System.out.println(watchLine.substring(watchLine.lastIndexOf("")+1)+" is added to the watcher-list");
                    //watchLine kullanildiginden watchBool yeni line i almak icin true doner
                    watchBool=true;

                }

                if (watchLine.contains("delete")){
                    String deleteName=watchLine.substring(watchLine.lastIndexOf(" ")+1);
                    //watcher-listten kisi sil
                    DoublyLinkedList<String> tempWatcher=null;
                    for (int j = 0; j <watcher_list.size() ; j++) {
                            if (watcher_list.first().equals(deleteName))
                                watcher_list.removeFirst();
                            else{
                                tempWatcher.addLast(watcher_list.first());
                            }

                    }
                    for (int j = 0; j < tempWatcher.size(); j++) {
                        watcher_list.addFirst(tempWatcher.last());
                        tempWatcher.removeLast();
                    }

                    System.out.println(deleteName+"  is removed from the watcher-list");
                    watchBool=true;

                }

                if (watchLine.contains("query-largest")){
                    String magnitudePlusPlace=magnitude_ordered_list.get(0);

                    String magnitudeFrom=magnitudePlusPlace.substring(0,magnitudePlusPlace.indexOf("*"));
                    magnitudeFrom=magnitudeFrom.substring(magnitudeFrom.lastIndexOf("<magnitude>")+1,magnitudeFrom.indexOf("</magnitude>"));

                    String placeFrom=magnitudePlusPlace.substring(magnitudePlusPlace.indexOf("*")+1);
                    placeFrom=placeFrom.substring(placeFrom.lastIndexOf("<place>")+1,placeFrom.indexOf("</place>"));

                    System.out.println("Largest earthquake in the past 6 hours:");
                    System.out.println("Magnitude "+magnitudeFrom+" at "+placeFrom);

                    //print the largest earthquake
                    watchBool=true;

                }


                //***************************************


                //time a gore earthquake e ekleme
                if (earthBool&&earthquake_reader.hasNextLine()){
                    for (int j = 0; j < 7; j++) {
                        earthquake[j]=earthquake_reader.nextLine();
                    }

                }
                int timeOfearthquake=Integer.parseInt(earthquake[2].substring(earthquake[2].indexOf(" ")+1,earthquake[2].lastIndexOf(" ")));
                if (!(timeOfearthquake<=time)){//eger timedan kucuk esit degilse
                    earthBool=false;
                }
                else {//kucuk esit ise ekle
                    for (int j = 0; j < earthquake.length; j++) {
                        earthquake_queue.enqueue(earthquake[j]);
                    }
                    earthBool=true;

                }

                //********************

                //time a gore earthquake'den silme islemi
                String firstTimeLine=earthquake_queue.get(2);//en eski kaydin time line'ini getir
                int firstTime=Integer.parseInt(firstTimeLine.substring(firstTimeLine.indexOf(" ")),firstTimeLine.lastIndexOf(" "));
                if (time-firstTime>6){//son 6 saatte degilse
                    for (int j = 0; j < 7; j++) {//o earthquake ile ilgili butun linelari sil
                        earthquake_queue.dequeue();
                    }
                }

                //****************
                //uploading magnitude-ordered-list
                //en buyugu hep en one swap edecegim
                String[] magnitudeArray=new String[earthquake_queue.size()/7];
                for (int j = 0; j < magnitudeArray.length; j++) {
                    magnitudeArray[j]=earthquake_queue.get(5+(j*7))+"*"+earthquake_queue.get(3+(j*7));
                }
                Arrays.sort(magnitudeArray);
                magnitude_ordered_list=null;
                for (int j = 0; j < magnitudeArray.length; j++) {
                    magnitude_ordered_list.add(j,magnitudeArray[magnitudeArray.length-j-1]);
                }//en buyuk en onde

                if (args[args.length-3].equals("[--all]")&& magnitudeArray.length-1>countOfmagnitude){//yeni eleman var yani
                    String lastPlace=earthquake_queue.get(earthquake_queue.size()-4);
                    lastPlace=lastPlace.substring(lastPlace.lastIndexOf("<place>")+1,lastPlace.indexOf("</place>"));
                    System.out.println("Earthquake "+lastPlace+" is inserted into the magnitude-ordered-list");
                }

                time++;


            }

        }catch (Exception e){

        }

    }

}
