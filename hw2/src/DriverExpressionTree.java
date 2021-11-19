public class DriverExpressionTree {
    //HOCAM ODEV TESLIMINDEN SONRA DOGRU ORNEK KOD PAYLASIR MISINIZ?
    //O KODLAR BIZE COK YARDIMCI OLUYOR PAYLASIRSANIZ COK MUTLU OLURUM <3
    public static void main(String[] args) {
        //BU KODDA SIN COS CALISMAZ BU SEBEPLE LUTFEN STRINGE SIN COS VERMEYIN!
        String str="(X+(0*7))";
        ExpressionTree expressionTree=new ExpressionTree(str);
        expressionTree.displayTree();//dogru calisiyor
        int c=expressionTree.evaluate(3);//dogru calisiyor
        System.out.println("answer of evaluate:"+c);
        str="(X+(2-9))";
        expressionTree=new ExpressionTree(str);
        System.out.println("new tree:"+expressionTree.toString());//dogru calisiyor


        str="(X+(0*1))";
        ExpressionTree simp=new ExpressionTree(str);
        System.out.println("new tree:"+simp.toString());
        simp=simp.simplify();//mantik dogru ama koddan cekemiyor
        System.out.println(simp.toString());
        str="(X+(X*2))";
        ExpressionTree temp=new ExpressionTree(str);
        temp=temp.derivative();//bana gore tabiki mantik dogru ama neden calismadigini da anlamadim
        System.out.println(temp.toString());
    }
}
