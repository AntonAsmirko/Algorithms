import java.util.Locale;
import java.util.Scanner;

public class TernarySearch {

    static double a;
    static int vp, vf;

    static double f(double x){
        double d = Math.sqrt(Math.pow(0-x, 2)+Math.pow(1-a, 2))/vp+Math.sqrt(Math.pow(x-1, 2)+Math.pow(a-0, 2))/vf;
        return d;
    }

    static double ternarySearch(double l, double r){
        double m1 = (l+r)/3;
        double m2 = 2*(l+r)/3;
        while (r-l>0.0001){
            m1 = l + (r - l) / 3;
            m2 = r - (r - l) / 3;
            if (f(m1)<f(m2)) {
                r = m2;
            } else l = m1;
        }
        return (l+r)/2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        vp = scanner.nextInt();
        vf = scanner.nextInt();
        a = scanner.nextDouble();
        System.out.println(ternarySearch(0., 1));
    }
}
