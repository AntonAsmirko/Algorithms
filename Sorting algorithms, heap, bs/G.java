import java.util.Scanner;

public class VerySimpeTask {

    static int n, x, y;

    static int f(int m){
        return m/x+m/y;
    }

    static int bS(int l ,int r, int n){
        int m = (l+r)/2;
        if (l>=r) return l;
        if (f(m)<n) return bS(m+1, r, n);
        else return bS(l, m, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        x = scanner.nextInt();
        y = scanner.nextInt();
        int a = x;
        x = Math.max(x, y);
        y = Math.min(a, y);
        int m = bS(0, x*(n-1), n-1)+y;
        System.out.println(m);
    }
}
