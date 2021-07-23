import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FastSearchArr {
    static Integer[] arr;

    static int bs(int l, int r, int x){
        int m = 0;
        while (r-l>1){
            m = (l+r)/2;
            if (arr[m]<x){
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        arr = new Integer[n];
        for (int i = 0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        int k = scanner.nextInt();
        int l, r;
        for (int i = 0; i < k; i++){
            l = scanner.nextInt();
            r = scanner.nextInt();
            System.out.print(bs(-1, arr.length,r+1)-bs(-1, arr.length, l)+" ");
        }
    }
}
