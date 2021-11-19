import java.util.Iterator;

public class ExpressionTree extends LinkedBinaryTree<String>{
    //BU KODDA SIN COS CALISMAZ BU SEBEPLE LUTFEN STRINGE SIN COS VERMEYIN!

    private LinkedBinaryTree<String> copyTree;
    private LinkedQueue<String> myQueue;
    public ExpressionTree(String str){
        copyTree=treefromString(str);//tree olustu

    }

    private LinkedBinaryTree<String> treefromString(String str){
        LinkedStack<LinkedBinaryTree<String>> stack=new LinkedStack<>();
        for (int i = 0; i <str.length(); i++) {
            if (!(str.charAt(i)=='(')&& !(str.charAt(i)==')')){
                LinkedBinaryTree<String> next=new LinkedBinaryTree<>();
                next.addRoot(""+str.charAt(i));
                stack.push(next);
            }
            else if (str.charAt(i)==')'){
                LinkedBinaryTree<String> t2=stack.pop();
                LinkedBinaryTree<String> t=stack.pop();
                LinkedBinaryTree<String> t1=stack.pop();
                t.attach(t.root(),t1,t2);
                stack.push(t);
            }

        }
        return stack.pop();
    }




    public int evaluate(int xvalue){
       boolean donus=isX(copyTree.root(),xvalue);//X degistirildi
       return sum(copyTree,copyTree.root());
    }

    private LinkedBinaryTree<String> getCopyTree(){
        LinkedBinaryTree<String> copyTree=new LinkedBinaryTree<>();
        int height=height(root());
        Position<String> temp=root();
        copyTree.addRoot(temp.getElement());
        for (int i = 0; i <height ; i++) {
            if (left(temp)!=null)
                copyTree.addLeft(left(temp),left(temp).getElement());
            if (right(temp)!=null)
                copyTree.addRight(right(temp),right(temp).getElement());

        }
        return null;
    }


    private boolean isX(Position<String> p, int x){//x varsa bulur ve degerini degistirir
        if (p.getElement().equals("X")){
            copyTree.set(p,""+x);
            return true;
        }
        else if (copyTree.left(p)!=null && copyTree.right(p)!=null){
            return isX(copyTree.left(p),x)|| isX(copyTree.right(p),x);
        }
        return false;

    }

    private int sum(LinkedBinaryTree<String> t,Position<String> p){//islemleri yapar

        if (isOperator(p.getElement())){
            int num1=sum(t,t.left(p));
            int num2=sum(t,t.right(p));
            String operator=p.getElement();
            int total=0;
            switch (operator) {
                case "+":{
                    total = num1 + num2;
                    break;
                }
                case "-":{
                    total=num1 - num2;
                    break;
                }
                case "*":{
                    total=num1*num2;
                    break;
                }
            }
            t.set(p,""+total);
            return total;
        }
        else if (!p.getElement().equals(")")){//is count
            int num1=Integer.parseInt(p.getElement());
            return num1;
        }
        return 0;

    }




    public ExpressionTree derivative(){
        String lastTree=copyTree.toString();
        ExpressionTree temp=new ExpressionTree(lastTree);
        Position<String> root=temp.root();
        if (root!=null){
            switch (temp.root().getElement()){
                case "+":{
                    derivativeSum(temp.left(root),temp.right(root),temp);
                }
                case "-":{
                    derivativeSub(temp.left(root),temp.right(root),temp);
                }
                case "*":{
                    derivativeMux(temp.left(root),temp.right(root),temp);
                }
            }
        }

        temp.simplify();
        return temp;
    }

    private void derivativeSum(Position<String> left, Position<String> right,ExpressionTree t){
        if (isOperator(left.getElement())){
            switch (left.getElement()){
                case "+":{
                    derivativeSum(t.left(left),t.right(left),t);
                    break;
                }
                case "-":{
                    derivativeSub(t.left(left),t.right(left),t);
                    break;
                }
                case "*":{
                    derivativeMux(t.left(left),t.right(left),t);
                    break;
                }

            }
        }

        if (isOperator(right.getElement())){
            switch (right.getElement()){
                case "+":{
                    derivativeSum(t.left(right),t.right(right),t);
                    break;
                }
                case "-":{
                    derivativeSub(t.left(right),t.right(right),t);
                    break;
                }
                case "*":{
                    derivativeMux(t.left(right),t.right(right),t);
                    break;
                }

            }
        }


        if (isOperand(left.getElement())&& isOperand(right.getElement())){//ikisi de constant ise
            t.set(t.parent(left),"0");
            t.remove(left);
            t.remove(right);
        }
        if (left.getElement().equals("X")&& isOperand(right.getElement())){//left X ve right constant ise
            t.set(t.parent(left),"1");
            t.remove(left);
            t.remove(right);
        }
        if (right.getElement().equals("X")&& isOperand(left.getElement())){
            t.set(t.parent(right),"1");
            t.remove(left);
            t.remove(right);
        }
        if (right.getElement().equals("X")&& left.getElement().equals("X")){
            t.set(t.parent(right),"2");
            t.remove(left);
            t.remove(right);
        }

    }

    private void derivativeSub(Position<String> left, Position<String> right,ExpressionTree t){
        if (isOperator(left.getElement())){
            switch (left.getElement()){
                case "+":{
                    derivativeSum(t.left(left),t.right(left),t);
                    break;
                }
                case "-":{
                    derivativeSub(t.left(left),t.right(left),t);
                    break;
                }
                case "*":{
                    derivativeMux(t.left(left),t.right(left),t);
                    break;
                }

            }
        }

        if (isOperator(right.getElement())){
            switch (right.getElement()){
                case "+":{
                    derivativeSum(t.left(right),t.right(right),t);
                    break;
                }
                case "-":{
                    derivativeSub(t.left(right),t.right(right),t);
                    break;
                }
                case "*":{
                    derivativeMux(t.left(right),t.right(right),t);
                    break;
                }

            }
        }


        if (isOperand(left.getElement())&& isOperand(right.getElement())){//ikisi de constant ise
            t.set(t.parent(left),"0");
            t.remove(left);
            t.remove(right);
        }
        if (left.getElement().equals("X")&& isOperand(right.getElement())){//left X ve right constant ise
            t.set(t.parent(left),"1");
            t.remove(left);
            t.remove(right);
        }
        if (right.getElement().equals("X")&& isOperand(left.getElement())){
            t.set(t.parent(right),"1");
            t.remove(left);
            t.remove(right);
        }
        if (right.getElement().equals("X")&& left.getElement().equals("X")){
            t.set(t.parent(right),"0");
            t.remove(left);
            t.remove(right);
        }

    }

    private void derivativeMux(Position<String> left, Position<String> right,ExpressionTree t){
        if (isOperator(left.getElement())){
            switch (left.getElement()){
                case "+":{
                    derivativeSum(t.left(left),t.right(left),t);
                    break;
                }
                case "-":{
                    derivativeSub(t.left(left),t.right(left),t);
                    break;
                }
                case "*":{
                    derivativeMux(t.left(left),t.right(left),t);
                    break;
                }

            }
        }

        if (isOperator(right.getElement())){
            switch (right.getElement()){
                case "+":{
                    derivativeSum(t.left(right),t.right(right),t);
                    break;
                }
                case "-":{
                    derivativeSub(t.left(right),t.right(right),t);
                    break;
                }
                case "*":{
                    derivativeMux(t.left(right),t.right(right),t);
                    break;
                }

            }
        }

        if (isOperand(left.getElement())&& right.getElement().equals("X")){
            t.set(left,"0");
            int secondPart=Integer.parseInt(left.getElement());
            t.set(right,""+secondPart);
            derivativeSum(left,right,t);
        }
        else if (isOperand(right.getElement())&& left.getElement().equals("X")){
            t.set(right,"0");
            int secondPart=Integer.parseInt(right.getElement());
            t.set(left,""+secondPart);
            derivativeSum(left,right,t);
        }
        else if (isOperand(left.getElement())&& isOperand(right.getElement())){
            t.set(left,"0");
            t.set(right,"0");
            derivativeSum(left,right,t);
        }
        else if (left.getElement().equals("X") && right.getElement().equals("X")){
           t.set(left,"2");
           t.set(right,"X");
           derivativeMux(left,right,t);

        }


    }





   /* public ExpressionTree simplify(){

        LinkedBinaryTree<String> simplerTree=new LinkedBinaryTree<>();
        myQueue=new LinkedQueue<>();
        Iterable<Position<String>> iterable=inorder();
        for (Position<String> stringPosition : iterable) {
            String next = stringPosition.getElement();
            myQueue.enqueue(next);
        }
        String str="";
        while (!myQueue.isEmpty()){
            str+=myQueue.dequeue();
        }
        while (str.contains("0")){
            if (str.indexOf("0")!=0){//hep kendinden oncesine bakiyor
                String operator=""+str.charAt(str.indexOf("0")-1);
                switch (operator){
                    case "+":{//x+0-7->x-7
                        str=str.substring(0,str.indexOf("0")-1)+str.substring(str.indexOf("0")+1,str.length());
                    }
                    case "-":{//x-0+7->x+7
                        str=str.substring(0,str.indexOf("0")-1)+str.substring(str.indexOf("0")+1);
                    }
                    case "*":{//x*0+7->7
                        str=str.substring(0,str.indexOf("0")-2)+str.substring(str.indexOf("0")+2);
                    }
                }
            }
            else if (str.indexOf("0")==0){//ilk index ise kendinden sonrasina bakar
                String operator=""+str.charAt(str.indexOf("0")+1);
                switch (operator){
                    case "+":{
                        str=str.substring(str.indexOf("0")+2);//0+x+7->x+7
                    }
                    case "*":{//0*x+7->7
                        str=str.substring(str.indexOf("0")+4);//bir sayi ile birlikte onu baglayan operatoru de sil
                    }
                }
            }

        }
        while (str.contains("1")){
            if (str.indexOf("1")!=0){//hep kendinden oncesine
                String operator=""+str.charAt(str.indexOf("0")-1);
                if(str.charAt(str.indexOf("1")-1)=='*'){//x*1+7->x+7
                    str=str.substring(str.indexOf("1")-2,str.indexOf("1"))+str.substring(str.indexOf("1")+1);
                }
            }
            else{//ilk index ise kendinden sonrasina bakar
                String operator=""+str.charAt(str.indexOf("0")-1);
                if(str.charAt(str.indexOf("1")+1)=='*'){//1*x+7->x+7
                    str=str.substring(str.indexOf("1")+2);
                }
            }

        }
        ExpressionTree temp=new ExpressionTree(str);

        return temp;
    }
    */
    public ExpressionTree simplify(){
        String str=copyTree.toString();
        ExpressionTree temp=new ExpressionTree(str);
        Iterable<Position<String>> iterable=temp.inorder();
        for (Position<String> p: iterable) {
            temp=temp.simplify(temp, p);
            iterable=temp.inorder();
        }
        return temp;
    }

    public ExpressionTree simplify(ExpressionTree temp, Position<String> p){
        if (p.getElement().equals("0")&& temp.parent(p).getElement().equals("*")){
            temp.set(temp.parent(p),"0");
            temp.remove(temp.sibling(p));
            temp.remove(p);

        }
        else if (p.getElement().equals("0")&& (temp.parent(p).getElement().equals("+")||temp.parent(p).getElement().equals("-"))){
            temp.set(temp.parent(p), temp.sibling(p).getElement());
            temp.remove(temp.sibling(p));
            temp.remove(p);
        }
        else if (p.getElement().equals("1")&&temp.parent(p).getElement().equals("*")){
            temp.set(temp.parent(p),temp.sibling(p).getElement());
            temp.remove(temp.sibling(p));
            temp.remove(p);
        }

        return temp;
    }

    public void displayTree(){
        Iterable<Position<String>> iterable=copyTree.inorder();
        Iterator<Position<String>> iterator=iterable.iterator();
        while (iterator.hasNext()){
            Position<String> next=iterator.next();
            int depth = copyTree.depth(next);
            for (int i = 0; i < depth; i++) {
                System.out.print(".");
            }
            System.out.println(next.getElement());
        }

    }

    public String toString(){
        return printExpression(copyTree.root());

    }

    private String printExpression(Position<String> p){
        String str="";
        if(copyTree.left(p)==null){//isExternal
            str+="("+p.getElement();
        }
        else if (copyTree.left(p)!=null){

            str+=" "+printExpression(copyTree.left(p));
            str+=" "+p.getElement();
            str+=" "+printExpression(copyTree.right(p))+")";
        }
        return str;
    }





    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*");
    }


    private boolean isOperand(String s){
        char c=s.charAt(0);
        for (int i = '0'; i <='9' ; i++) {
            if (i==c)
                return true;
        }
        return false;
    }




}
