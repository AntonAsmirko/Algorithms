import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PaintItBlack {
    private static MyPair[] max;
    private static final int BIAS = 500_000;

    private static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1)
            return;
        if (!max[x].drop)
            return;

        max[2 * x + 1].rightBorder = max[x].rightBorder;
        max[2 * x + 1].leftBorder = max[x].leftBorder;
        max[2 * x + 1].blackSegmentCount = max[x].leftBorder == MyPair.Color.WHITE ? 0 : 1;
        max[2 * x + 1].drop = true;
        max[2 * x + 2].rightBorder = max[x].rightBorder;
        max[2 * x + 2].leftBorder = max[x].leftBorder;
        max[2 * x + 2].blackSegmentCount = max[x].leftBorder == MyPair.Color.WHITE ? 0 : 1;
        max[2 * x + 2].drop = true;
        max[2 * x + 1].blackSegmentLength = max[x].leftBorder == MyPair.Color.BLACK ?
                max[2 * x + 1].rightIndexBorder - max[2 * x + 1].leftIndexBorder : 0;
        max[2 * x + 2].blackSegmentLength = max[x].rightBorder == MyPair.Color.BLACK ?
                max[2 * x + 2].rightIndexBorder - max[2 * x + 2].leftIndexBorder : 0;
        max[x].drop = false;
    }

    private static MyPair max(int l, int r, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return null;
        }
        if (lx >= l && rx <= r) {
            return max[x];
        }
        int m = (lx + rx) / 2;
        MyPair sl = max(l, r, 2 * x + 1, lx, m);
        MyPair sr = max(l, r, 2 * x + 2, m, rx);
        return func(sl, sr);
    }

    private static MyPair func(MyPair a, MyPair b) {
        return new MyPair(a.blackSegmentCount + b.blackSegmentCount - (a.rightBorder == b.leftBorder
                && a.rightBorder == MyPair.Color.BLACK ? 1 : 0),
                a.blackSegmentLength + b.blackSegmentLength, a.leftBorder, b.rightBorder, false,
                a.leftIndexBorder, b.rightIndexBorder);
    }

    private static void set(int l, int r, MyPair.Color color, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            max[x].blackSegmentCount = color == MyPair.Color.BLACK ? 1 : 0;
            max[x].blackSegmentLength = color == MyPair.Color.BLACK ? rx - lx : 0;
            max[x].leftBorder = color;
            max[x].rightBorder = color;
            max[x].drop = true;

            return;
        }
        int m = (lx + rx) / 2;
        set(l, r, color, 2 * x + 1, lx, m);
        set(l, r, color, 2 * x + 2, m, rx);
        max[x] = func(max[2 * x + 1], max[2 * x + 2]);
    }

    private static int logPizdatiy(int a) {
        int count = 0;
        while (a != 0) {
            count++;
            a /= 2;
        }
        return count - 1;
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int size = 1024*1024;
        int n = scanner.nextInt();
        StringBuilder result = new StringBuilder();
        int shesh = logPizdatiy(size);
        int tmp = (int) Math.pow(2,shesh);
        max = new MyPair[size * 2 - 1];

        for (int i = 0; i < max.length; i++) {
            max[i] = new MyPair();
            max[i].leftIndexBorder = i;
            max[i].rightIndexBorder = i + 1;
        }
        for (int i = tmp - 2; i >= 0; i--) {
            max[i].leftIndexBorder = max[2 * i + 1].leftIndexBorder;
            max[i].rightIndexBorder = max[2 * i + 2].rightIndexBorder;
        }
        for (int i = 0; i < n; i++){
            String st = scanner.next();
            switch (st) {
                case "W":
                    int l = scanner.nextInt(), r = scanner.nextInt();
                    set(l + BIAS, l + r + BIAS, MyPair.Color.WHITE, 0, 0, tmp);
                    MyPair myPair = max(0, tmp, 0, 0, tmp);
                    result.append(myPair.blackSegmentCount).append(" ").append(myPair.blackSegmentLength).append("\n");
                    break;
                case "B":
                    int first = scanner.nextInt();
                    int second = scanner.nextInt();
                    set(first + BIAS, first + second + BIAS, MyPair.Color.BLACK, 0, 0, tmp);
                    MyPair myPairr = max(0, tmp, 0, 0, tmp);
                    result.append(myPairr.blackSegmentCount).append(" ").append(myPairr.blackSegmentLength).append("\n");
                    break;
            }
        }
        System.out.println(result.toString());
    }

    static class MyPair {
        enum Color {
            BLACK, WHITE
        }
        int leftIndexBorder = 0;
        int rightIndexBorder = 0;
        long blackSegmentCount = 0;
        long blackSegmentLength = 0;
        Color leftBorder = Color.WHITE;
        Color rightBorder = Color.WHITE;
        boolean drop = false;

        MyPair(long blackSegmentCount, long blackSegmentLength,
               Color leftBorder, Color rightBorder, boolean drop, int leftIndexBorder,
               int rightIndexBorder) {
            this.blackSegmentCount = blackSegmentCount;
            this.blackSegmentLength = blackSegmentLength;
            this.leftBorder = leftBorder;
            this.rightBorder = rightBorder;
            this.drop = drop;
            this.leftIndexBorder = leftIndexBorder;
            this.rightIndexBorder = rightIndexBorder;
        }

        MyPair() {

        }
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}