import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class FloatingBS {
    static Double c;

    static double f(double x){return Math.pow(x,2)+Math.sqrt(x)-c;}

    static Double floatingBS(Double l, Double r, double eps){
        while (r-l>eps){
            double m = (r+l)/2;
            if (f(m)<=0) l = m;
            else r = m;
        }
        return (l+r)/2;
    }
    public static void main(String[] args) {
        DecimalFormat dec = new DecimalFormat("#0.000000");
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        c = scanner.nextDouble();
        double ans = floatingBS(0., c, 0.000001);
        System.out.println(ans);
    }
}
