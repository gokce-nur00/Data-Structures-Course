import java.util.Scanner;
import java.util.Stack;
public class Q1 {

    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        Stack<String> myStack=new Stack<String>();

        System.out.println("Input:");
        String input=keyboard.nextLine();
        input=input.substring(1);//ilk / yok sayiyorum
        myStack.push("/");

        while (input.indexOf('/')!=-1){
            String temp=input.substring(0,input.indexOf('/'));
            if (temp.equals("..") && myStack.size()!=0){//geriye donme eger size 0 degilse
                myStack.pop();
                myStack.pop();

            }
            else if (temp.equals("."));//current
            else if (temp.length()==0);//art arda girilmis / isareti
            else {//obur hallerde ekle
                myStack.push(temp);
                myStack.push("/");
            }
            input=input.substring(input.indexOf('/')+1);
        }
        myStack.push(input);
        int sizeOfStack=myStack.size();
        String[] stackToArray=new String[sizeOfStack];

        for (int i = 0; i <sizeOfStack ; i++) {//stack to string array
            stackToArray[i]=myStack.peek();
            myStack.pop();
        }
        System.out.println("Output:");
        for (int i = sizeOfStack-1; i >=0 ; i--) {//array to the output
            System.out.print(stackToArray[i]);
        }


    }
}
