import java.util.Scanner;
import java.util.Stack;
public class Q2 {
    //hocam beynimi klavyede erittim 3. soruyu hesaplarken.
    //bu sebepten bu kodumu cok effective sekilde yazamadim.
    //bazi yerlerde buna gerek yok kodu uzatmis bos yere derseniz haklisinizdir
    //aklima ne geldiyse yazdim


    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        Stack<String> myStack=new Stack<String>();
        System.out.println("Input:");
        String input=keyboard.nextLine();
        int count=0;
        //ne kadar parantez cifti var onu bulmak icin...
        for (char c:input.toCharArray()) {
            if (c=='[')
                count++;
        }
        //count kadar ice girip stack icine atacagiz.
        String howMany="";//ne kadar stacke tekrar girilecek onu bulmak icin cekecegimiz bilgi
        String what="";//hangi String parcasi stacke eklenecek
        for (int i = 1; i <=count ; i++) {

            String temp=input.substring(input.lastIndexOf('[')+1);//en icte kalan acma parantezinden sonrasi
            what=input.substring(input.lastIndexOf('[')+1,input.lastIndexOf('[')+temp.indexOf(']')+1);//en ic parantez icini alir.
            howMany=""+input.charAt(input.lastIndexOf('[')-1);//en ic parantezden once ne kadar tekrar yapilacagi yazilir.
            int many=Integer.parseInt(howMany);

            if (input.indexOf(']')<input.lastIndexOf('[')){//1[a2[de]3[f]] gibi bir durumsa
                String str="";
                for (int j = 0; j < many; j++) {//ne kadar tekrar ediyorsa
                    str+=what;
                }
                if (!myStack.isEmpty()){
                    //stack bos degilse
                    myStack.pop();
                }

                myStack.push(str);//yeni stringi ekle
                input=input.substring(0,input.lastIndexOf('[')-1)+str+temp.substring(temp.indexOf(']')+1);
            }
            else if (input.indexOf(']')>input.lastIndexOf('[')){//2[ab2[de]] gibi orneklerde
                String str="";
                for (int j = 0; j < many; j++) {
                    str+=what;
                }
                String lastlast=input.substring(input.indexOf(']')+1);
                if (lastlast.indexOf(']')!=-1){
                    str+=lastlast.substring(0,lastlast.indexOf(']'));
                    lastlast=lastlast.substring(lastlast.indexOf(']'));
                }

                input=input.substring(0,input.lastIndexOf('[')-1)+str+lastlast;
                if (!myStack.isEmpty())
                    myStack.pop();
                myStack.push(str);

            }

        }
        int sizeOfStack=myStack.size();
        System.out.println("Output:");
        for (int i = 0; i < sizeOfStack; i++) {
            System.out.print(myStack.peek());
            myStack.pop();

        }
    }

}
