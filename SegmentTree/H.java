import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReverseRMQ {

    static Query[] queries;
    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;
    static FastReader scanner;
    private static int[] max;
    private static MyPair[] set;


    private static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1)
            return;
        if (set[x].state == MyPair.State.NONE)
            return;
        if (set[x].state == MyPair.State.SET) {
            set[2 * x + 1].val = set[x].val;
            set[2 * x + 1].state = set[x].state;
            max[2 * x + 1] = set[x].val;
            set[2 * x + 2].val = set[x].val;
            set[2 * x + 2].state = set[x].state;
            max[2 * x + 2] = set[x].val;
            set[x].val = 0;
            set[x].state = MyPair.State.NONE;
        } else if (set[x].state == MyPair.State.ADD) {
            set[2 * x + 1].val += set[x].val;
            if (set[2 * x + 1].state != MyPair.State.SET)
                set[2 * x + 1].state = set[x].state;
            max[2 * x + 1] += set[x].val;
            set[2 * x + 2].val += set[x].val;
            if (set[2 * x + 2].state != MyPair.State.SET)
                set[2 * x + 2].state = set[x].state;
            max[2 * x + 2] += set[x].val;
            set[x].val = 0;
            set[x].state = MyPair.State.NONE;
        }

    }

    private static int max(int l, int r, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return Integer.MAX_VALUE;
        }
        if (lx >= l && rx <= r) {
            return max[x];
        }
        int m = (lx + rx) / 2;
        int sl = max(l, r, 2 * x + 1, lx, m);
        int sr = max(l, r, 2 * x + 2, m, rx);
        return Math.min(sl, sr);
    }

    private static void set(int l, int r, int v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            set[x].val = v;
            set[x].state = MyPair.State.SET;
            max[x] = v;
            return;
        }
        int m = (lx + rx) / 2;
        set(l, r, v, 2 * x + 1, lx, m);
        set(l, r, v, 2 * x + 2, m, rx);
        max[x] = Math.min(max[2 * x + 1], max[2 * x + 2]);
    }

    public static void main(String[] args) throws IOException {
        bufferedReader
                = new BufferedReader(new FileReader(new File("rmq.in")));
        bufferedWriter
                = new BufferedWriter(new FileWriter(new File("rmq.out")));
        scanner = new FastReader(bufferedReader);

        int n = scanner.nextInt(), m = scanner.nextInt();
        int size = 1024 * 1024 * 2;
        int tmp = (int) Math.pow(2,  18);
        max = new int[(int) Math.pow(2, 18) * 2 - 1];
        set = new MyPair[max.length];
        Arrays.fill(max, Integer.MAX_VALUE);
        for (int i = 0; i < set.length; i++) {
            set[i] = new MyPair();
        }
        queries = new Query[m];
        for (int i = 0; i < m; i++) {
            queries[i] = new Query(scanner.nextInt(),
                    scanner.nextInt(),
                    scanner.nextInt());
        }
        Arrays.sort(queries);
        for (Query query :
                queries) {
            set(query.i - 1, query.j, query.q, 0, 0, tmp);
        }
        boolean isNotQueryOk = false;

        for (Query query :
                queries) {
            if (max(query.i - 1, query.j, 0, 0, tmp) != query.q) {
                isNotQueryOk = true;
            }
        }
        if (!isNotQueryOk) {
            bufferedWriter.write("consistent\n");
            StringBuilder resArr = new StringBuilder();
            for (int i = 0; i < n; i++) {
                resArr.append(max(i, i+1, 0, 0, tmp)).append(" ");
            }
            bufferedWriter.write(resArr.toString());
        } else {
            bufferedWriter.write("inconsistent\n");
        }
        bufferedWriter.close();
    }

    public static class Query implements Comparable<Query> {
        int i, j;
        int q;

        public Query(int i, int j, int q) {
            this.i = i;
            this.j = j;
            this.q = q;
        }

        @Override
        public int compareTo(Query query) {
            if (this.q > query.q) {
                return 1;
            } else if (this.q < query.q) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    static class MyPair {
        enum State {
            ADD, SET, NONE
        }

        int val = 0;
        State state = State.NONE;
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader bufferedReader)
        {
            br = bufferedReader;
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