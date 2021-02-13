import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Norm {

    static int r;

    static int[][] arr;

    static int[] matrixMult(int[] a, int[] b) {
        int[] c = new int[4];
        c[0] = (a[0] * b[0] + a[1] * b[2]) % r;
        c[1] = (a[0] * b[1] + a[1] * b[3]) % r;
        c[2] = (a[2] * b[0] + a[3] * b[2]) % r;
        c[3] = (a[2] * b[1] + a[3] * b[3]) % r;
        return c;
    }

    static int[] sum(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) {
            int[] res = {1, 0, 0, 1};
            return res;
        }
        if (lx >= l && rx <= r) {
            return arr[x];
        }
        int m = (lx + rx) / 2;
        int[] sl = sum(l, r, 2 * x + 1, lx, m);
        int[] sr = sum(l, r, 2 * x + 2, m, rx);
        return matrixMult(sl, sr);
    }

    static StringBuilder prepareOutput(int[] output) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < output.length; i++) {
            sb.append(output[i]).append(i % 2 == 0 ? " " : "");
            if (i % 2 == 1){
                sb.append('\n');
            }
        }
        return sb;
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
        FastScanner scanner = null;
        scanner = new FastScanner(new File("crypto.in"));
        r = scanner.nextInt();
        int size = scanner.nextInt(), m = scanner.nextInt();
        StringBuilder result = new StringBuilder();
        int tmp = (int) Math.pow(2, (logPizdatiy(size)) + 1);
        arr = new int[(int) Math.pow(2, (logPizdatiy(size)) + 1) * 2 - 1][4];

        for (int u = tmp - 1; u < tmp - 1 + size; u++) {
            int[] res = new int[4];
            for (int i = 0; i < 4; i++) {
                res[i] = scanner.nextInt();
            }
            arr[u] = res;
        }
        for (int i = tmp - 2; i >= 0; i--) {
            arr[i] = matrixMult(arr[i * 2 + 1], arr[i * 2 + 2]);
        }
        for (int i = 0; i < m; i++) {
            int first = scanner.nextInt() - 1;
            int second = scanner.nextInt();
            result.append(prepareOutput(sum(first, second, 0, 0, tmp)));
            result.append("\n");
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("crypto.out")));
            bufferedWriter.write(result.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}