import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Earthquake_Queue extends Queue<String> {
    private String inputFileName;
    private Queue<String> allInfo;//ilk once butun linelari aliyor
    private Queue<String> timeOfList;//sadece zaman
    private Queue<String> placeOfList;//sadece yer
    private Queue<String> coorOfList;//sadece koordinat
    private Queue<String> magOfList;//sadece magnitude
    public Earthquake_Queue(String name){
        timeOfList=new Queue<>();
        placeOfList=new Queue<>();
        coorOfList=new Queue<>();
        magOfList=new Queue<>();
        this.inputFileName=name;
        this.allInfo=new Queue<>();
        Scanner reader=null;
        try {
            reader=new Scanner(new File(this.inputFileName));
            while (reader.hasNextLine()){
                this.allInfo.enqueue(reader.nextLine());

            }
        }catch (FileNotFoundException e){
            System.out.println("there is no file like:"+name);
        }

    }
    //ne kadar deprem var
    private int countOfEarthquake(){
        int count=0;
        Queue<String> temp=getAllInfo();
        while (!temp.isEmpty()){
            if (temp.first().equals("<earthquake>")){
                count++;
            }
            temp.dequeue();

        }
        return count;
    }
    //sadece zamanlari tutuyor
    public Queue<String> getTime(){
        Queue<String> temp=getAllInfo();
        this.timeOfList=null;
        int time=2;
        int i=0;
        while (time<time+(7*countOfEarthquake())){//
            if (i==time){
                this.timeOfList.enqueue(temp.first());
                temp.dequeue();
                time=time+7;
                i++;
            }
            else{
                i++;
                temp.dequeue();
            }
        }
        return this.timeOfList;
    }
    //zamana gore listeden silme islemi
    public void setTime(int time){//verilen zamana gore listedekiler son 6 saat icindekiler mi onu duzenler.
        Queue<Integer> numTime=new Queue<>();
        Queue<String> temp=getTime();
        for (int i = 0; i < getTime().size() ; i++) {
            numTime.enqueue(Integer.parseInt(temp.first()));
        }
        boolean kosul=true;
        while (kosul){
            if (time-numTime.first()<=6)
                break;
            numTime.dequeue();
            placeOfList.dequeue();
            coorOfList.dequeue();
            magOfList.dequeue();
            for (int i = 0; i <=6; i++) {
                allInfo.dequeue();
                /*
                0-><earthquake>
                1->id
                2->time
                3->place
                4->coordinates
                5->magnitude
                6-><earthquake/>
                 */
            }
        }
    }
    //yerleri donuyor
    public Queue<String> getPlace(){
        Queue<String> temp=getAllInfo();
        placeOfList=null;
        int time=3;
        int i=0;
        while (time<time+(7*countOfEarthquake())){//
            if (i==time){
                this.placeOfList.enqueue(temp.first());
                temp.dequeue();
                time=time+7;
                i++;
            }
            else{
                i++;
                temp.dequeue();
            }
        }
        return this.placeOfList;
    }
    //coordinate donuyor
    public Queue<String> getCoordinates(){
        Queue<String> temp=getAllInfo();
        coorOfList=null;
        int time=4;
        int i=0;
        while (time<time+(7*countOfEarthquake())){//
            if (i==time){
                this.coorOfList.enqueue(temp.first());
                temp.dequeue();
                time=time+7;
                i++;
            }
            else{
                i++;
                temp.dequeue();
            }
        }
        return this.coorOfList;
    }
    //magnitude donuyor
    public Queue<String> getMagnitude(){
        Queue<String> temp=getAllInfo();
        magOfList=null;
        int time=5;
        int i=0;
        while (time<time+(7*countOfEarthquake())){//
            if (i==time){
                this.magOfList.enqueue(temp.first());
                temp.dequeue();
                time=time+7;
                i++;
            }
            else{
                i++;
                temp.dequeue();
            }
        }
        return this.magOfList;
    }
    //tum bilgileri donuyor
    public Queue<String> getAllInfo() {

        return allInfo;
    }


}
