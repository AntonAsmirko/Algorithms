import java.util.Scanner;

public class Stars {

    static int n;

    static long[][][] space;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        space = new long[n][n][n];
        n--;
        int u = scanner.nextInt();
        while (u != 3) {
            switch (u) {
                case 1:
                    int f = scanner.nextInt(), s = scanner.nextInt(),
                            th = scanner.nextInt();
                    long fo = scanner.nextInt();
                    update(f, s, th, fo);
                    break;
                case 2:
                    int first = scanner.nextInt(), second = scanner.nextInt(),
                            third = scanner.nextInt(), fourth = scanner.nextInt(),
                            fifth = scanner.nextInt(), sixth = scanner.nextInt();
                    System.out.println(sum(first, second, third, fourth, fifth, sixth));
            }
            u = scanner.nextInt();
        }
    }

    static void update(int x, int y, int z, long v) {
        if (x < 0 || y < 0 || z < 0)
            return;
        for (int i = x; i <= n; i = (i | (i + 1)))
            for (int j = y; j <= n; j = (j | (j + 1)))
                for (int k = z; k <= n; k = (k | (k + 1)))
                    space[i][j][k] += v;
    }

    static long sum(int x1, int y1, int z1, int x2, int y2, int z2) {
        return (sum(x2, y2, z2)
                + sum(x1 - 1, y1 - 1, z2)
                + sum(x1 - 1, y2, z1 - 1)
                + sum(x2, y1 - 1, z1 - 1)
                - sum(x1 - 1, y2, z2)
                - sum(x2, y1 - 1, z2)
                - sum(x2, y2, z1 - 1)
                - sum(x1 - 1, y1 - 1, z1 - 1));
    }

    static void update(int x1, int y1, int z1, int x2, int y2, int z2, long v) {
        update(x2, y2, z2, v);
        update(x1 - 1, y1 - 1, z2, v);
        update(x1 - 1, y2, z1 - 1, v);
        update(x2, y1 - 1, z1 - 1, v);
        update(x1 - 1, y2, z2, -v);
        update(x2, y1 - 1, z2, -v);
        update(x2, y2, z1 - 1, -v);
        update(x1 - 1, y1 - 1, z1 - 1, -v);
    }

    static long summm(int a, int b, int c, int d, int e, int f) {
        long fs = sum(d, e, f);
        long ss = sum(a - 1, e, f); // -
        long ts = sum(d, b - 1, f); // -
        long fos = sum(a - 1, b - 1, f); // +
        long fis = sum(d, e, c - 1); // -
        long sis = sum(a - 1, e, c - 1); //+
        long ses = sum(d, b - 1, c - 1); // +
        long eis = sum(a - 1, b - 1, c - 1); // -
        return fs - ss - ts + fos - fis + sis + ses - eis;
    }

    static long sum(int x, int y, int z) {
        long res = 0;
        /*
        while (i <= n) {
            int x = j;
            while (x <= n) {
                int y = k;
                while (y <= n) {
                    res += space[i][x][y];
                    y += (y & (-y));
                }
                x += (x & (-x));
            }
            i += (i & (-i));
        }

         */
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1)
            for (int j = y; j >= 0; j = (j & (j + 1)) - 1)
                for (int k = z; k >= 0; k = (k & (k + 1)) - 1) {
                    res += space[i][j][k];
                }
        return res;
    }


}