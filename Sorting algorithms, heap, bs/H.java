import java.util.Scanner;

public class Diplomas {

    static long f(long x){
        long l = x/w;
        long r = x/k;
        return l*r;
    }

    static long bs(long l, long r){
        long m = 0;
        while (l<r){
            m = (r+l)/2;
            long ans = f(m);
            if (ans<n){
                l = m+1;
                m+=1;
            } else {
                r = m;
            }
        }
        return m;
    }

    static long w,k,n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        w = scanner.nextInt();
        k = scanner.nextInt();
        n = scanner.nextInt();
        System.out.println(bs(0, n*Math.max(w,k)));
    }
}
