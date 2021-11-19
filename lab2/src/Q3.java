public class Q3 {

    //cevaplar asagida yorum olarak verilmistir
    public static void main(String[] args) {
        int n=22;

        int[] arr=new int[n];
        for (int i =0; i <n; i++) {
            arr[i]=i;
        }
        arr=method1(arr);
        /*
        method1 icin zaman-> O(n)'dir.
        cozumleme yaparsak ilk for dongusu n-10(k) kere doner
        ikinci for dongusu ise sabit 10(k) kere doner i ne olursa olsun.
        Bu da ,diger sabitleri hesapta gostermeden, zaman denklemini (n-10)*10 eder.
        f(n)<=c.g(n) n>=n0
        10n-100<=c.n
        (10-c)n<=100
        n<=100/(10-c)
        c<10
        n0=100
        Sonuc olarak T(n)=10n-100
        En buyuk exp. degeri 1 ve method lineer.
        Verilen degerleri denedigimizde
        n=20000 icin:
        199900->(20000-10)*10->19990*10->199900
        n=40000 icin:
        399900->(40000-10)*10->39990*10->399900
        n=80000 icin:
        799900->(80000-10)*10->79990*10->799900
        bu da hesabı dogru karsiliyor
         */
        for (int i = 0; i <n; i++) {
            arr[i]=i;
        }
        arr=method2(arr);

        /*
        method 2 icin O(n^2)
        if n%2==0-> T(n)=9n^2/100 -> T(n)=(9/100)n^2
        if n%2==1-> T(n)=(9n/10-1)n/10
        n%2==0 durumu icin acikliyorum
        ilk for dongusu n-n/10 kere doner
        ikinci for dongusu n/10 kere doner
        totalde (n-n/10)*n/10
        T(n)=(9n/10)*n/10->(9*n^2)/100-> (9/100)n^2->(0.09)n^2
        f(n)<=c.g(n) n>=n0
        (9/100)n^2<=c*n^2
        (9/100-c)n^2<=0
        9/100-c<=0
        9/100<=c
        n0>=0
        n=20000 icin
        n%2==1 icin kodun int degeri gercekte bolme sonucu birbirini karislamadigindan(kusurat sebebiyle)
        tek sayilarda cikmasi gereken sayidan uzaklastigi gozukuyor(sayi buyudukce)
        36000000->(20000*20000*9)/100-> 36*(10^8)/100->36000000
        ****
        n=40000 icin:
        144000000->(40000*40000*9)/100->144*10^8/10^2->144000000
        ****
        n=80000 icin:
        576000000->(64*9*10^8)/100->576*10^6->576000000
        bu hesabı dogru karsiliyor

         */
        for (int i = 0; i < n; i++) {
            arr[i]=i;
        }
        arr=method3(arr);
        /*
        method 3 icin iki turlu islem soz konusu
        n%2==0 ise-> (n-(n%10))(n%10)
        n%2==1 ise->(n-(n%10+1))(n%10+1)
        ornek:
        n=21 icin time:38
        n%2=1-> (21-(1+1))(1+1)->19*2->38
        n=22 icin time:40
        n%2=0->(22-2)2->20*2=40
        n=20 icin time=0
        n%2=0->(20-0)0=0

        ****
        n%2==1 icin:
        k=n%10+1 oldugundan max=10, min=1
        buna gore ilk for dongusu n-k kere doner
        ikinci for dongusu k kere doner
        (n-k).k-> nk-k^2 ve k her zaman 1<=k<=10
        n<=k olamaz negatife doner.
        bu sebeple kn-k^2-> O(n)
        f(n)<=c.g(n) n>=n0
        kn-k^2<=c.n n>n0
        (k-c)n<=k^2
        n<=k^2/(k-c)
        1<=k<=10
        1-c<=k-c<=10
        k-c!=0
        bu sebeple
        1-c>0
        c<1
        ****
        n=20000->time=0, n=40000->time=0, n=80000->time=0
         */



    }

    public static int[] method1(int[] array)
    {
        int k = 10;
        int count1=0;
        for (int i = k/2 ; i < array.length - k/2 ; i++)
        {
            for (int j = i - k/2; j < i + k/2 ; j++)
            {
                array[i] += array[j];
                count1++;
            }
            array[i] /= k;
        }

        System.out.println("method1 time:"+count1);
        return array;
    }
    public static int[] method2(int[] array)
    {
        int k = array.length/10;
        int count2=0;
        for (int i = k/2 ; i < array.length - k/2 ; i++)
        {

            for (int j = i - k/2; j < i + k/2 ; j++)
            {
                array[i] += array[j];
                count2++;
            }
            array[i] /= k;
        }
        System.out.println("method2 time:"+count2);
        return array;
    }
    public static int[] method3(int[] array)
    {
        int count3=0;
        int k = array.length%10 + 1;
        for (int i = k/2 ; i < array.length - k/2 ; i++)
        {
            for (int j = i - k/2; j < i + k/2 ; j++)
            {
                count3++;
                array[i] += array[j];
            }
            array[i] /= k;
        }
        System.out.println("method3 time:"+count3);
        return array;
    }
}
