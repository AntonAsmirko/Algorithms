import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Norm{

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static long[] arr;

    static void set(int i, long v, int x, int lx, int rx){
        if (rx - lx == 1) {
            arr[x] = v;
            return;
        }
        int m = (lx + rx) / 2;
        if (i < m) {
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        arr[x] = arr[2 * x + 1] + arr[2 * x + 2];
    }

    static long sum(int l, int r, int x, int lx, int rx){
        if (l >= rx || lx >= r) {
            return 0L;
        }
        if(x>arr.length){
            return 0L;
        }
        if (lx >= l && rx <= r) {
            return arr[x];
        }
        int m = (lx + rx) / 2;
        long sl = sum(l, r, 2 * x + 1, lx, m);
        long sr = sum(l, r, 2 * x + 2, m, rx);
        return sl + sr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        int size = scanner.nextInt();
        int tmp = (int)Math.pow(2,(int)(Math.log(size)/Math.log(2))+1);
        arr = new long[(int)Math.pow(2,(int)(Math.log(size)/Math.log(2))+1)*2-1];

        for(int i = tmp-1; i < tmp-1+size; i++){
            arr[i] = scanner.nextLong();
        }
        for(int i = tmp - 2; i>=0; i--){
            arr[i] = arr[i*2+1]+ arr[i*2+2];
        }
        while (scanner.hasNext()){
            String st = scanner.next();
            switch (st){
                case "set":
                    int i = scanner.nextInt() - 1;
                    long x = scanner.nextLong();
                    set(i, x, 0, 0, tmp);
                    break;
                case "sum":
                    int first = scanner.nextInt() - 1;
                    int second = scanner.nextInt();
                    result.append(sum(first, second, 0, 0, tmp));
                    result.append("\n");
            }
        }
        System.out.println(result.toString());
    }
}