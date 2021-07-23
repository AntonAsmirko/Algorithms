import java.util.Scanner;

public class NearBinSearch {

    static long[] a;

    static long bs(int l, int r, long x){
        int m = 0;
        while (r-l>1){
            m = (l+r)/2;
            if (a[m]<=x){
                l = m;
            } else {
                r = m;
            }
        }
        if (l<0) return a[r];
        else if(r>=a.length) return a[l];
        else if (Math.abs(x-a[r])<Math.abs(x- a[l])){
            return a[r];
        } else return a[l];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();
        a = new long[n];
        for (int i = 0; i< n; i++){
            a[i] = scanner.nextLong();
        }
        for (int i = 0; i < k; i++){
            long z = bs(-1, n, scanner.nextInt());
            System.out.println(z);
        }
    }
}
