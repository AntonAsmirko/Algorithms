import java.util.Arrays;
import java.util.Scanner;

public class Lazy {
    private static long[] max;
    private static MyPair[] set;

    private static void propagate(int x, int lx, int rx){
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
        } else if (set[x].state == MyPair.State.ADD){
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

    private static long max(int l, int r, int x, int lx, int rx){
        propagate(x, lx, rx);
        if (l >= rx || lx >= r){
            return Long.MAX_VALUE;
        }
        if (lx >= l && rx <= r){
            return max[x];
        }
        int m = (lx + rx)/2;
        long sl = max(l, r, 2*x +1, lx, m);
        long sr = max(l, r, 2*x+2, m, rx);
        return Math.min(sl, sr);
    }

    private static void set(int l, int r, long v, int x, int lx, int rx){
        propagate(x, lx, rx);
        if (l >= rx || lx >= r){
            return;
        }
        if (lx >= l && rx <= r){
            set[x].val = v;
            set[x].state = MyPair.State.SET;
            max[x] = v;
            return;
        }
        int m = (lx+rx)/2;
        set(l, r, v, 2*x+1, lx, m);
        set(l, r, v, 2*x+2, m, rx);
        max[x] = Math.min(max[2*x+1], max[2*x+2]);
    }

    private static void add(int l, int r, long v, int x, int lx, int rx){
        propagate(x, lx, rx);
        if (l >= rx || lx >= r){
            return;
        }
        if (lx >= l && rx <= r){
            set[x].val += v;
            if (set[x].state == MyPair.State.NONE)
                set[x].state = MyPair.State.ADD;
            max[x] += v;
            return;
        }
        int m = (lx+rx)/2;
        add(l, r, v, 2*x+1, lx, m);
        add(l, r, v, 2*x+2, m, rx);
        max[x] = Math.min(max[2*x+1], max[2*x+2]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        int size = scanner.nextInt();
        int tmp = (int)Math.pow(2,(int)(Math.log(size)/Math.log(2))+1);
        max = new long[(int)Math.pow(2,(int)(Math.log(size)/Math.log(2))+1)*2-1];
        set = new MyPair[max.length];
        Arrays.fill(max, Long.MAX_VALUE);
        for(int i = 0; i < set.length; i++){
            set[i] = new MyPair();
        }

        for(int i = tmp-1; i < tmp-1+size; i++){
            max[i] = scanner.nextLong();
        }
        for(int i = tmp - 2; i>=0; i--){
            max[i] = Math.min(max[i*2+1], max[i*2+2]);
        }
        while (scanner.hasNext()){
            String st = scanner.next();
            switch (st){
                case "set":
                    int l = scanner.nextInt()-1, r = scanner.nextInt();
                    long x = scanner.nextLong();
                    if (l < 0){
                        l = 0;
                    }
                    if (r> size){
                        r = size;
                    }
                    set(l, r, x, 0, 0, tmp);
                    break;
                case "min":
                    int first = scanner.nextInt() - 1;
                    int second = scanner.nextInt();
                    result.append(max(first, second, 0, 0, tmp));
                    result.append("\n");
                    break;
                case "add":
                    int ll = scanner.nextInt()-1, rr = scanner.nextInt();
                    long xx = scanner.nextLong();
                    if (ll < 0){
                        ll = 0;
                    }
                    if (rr> size){
                        rr = size;
                    }
                    add(ll, rr, xx, 0, 0, tmp);
                    break;
            }
        }
        System.out.println(result.toString());
    }

    static class MyPair{
        enum State{
            ADD, SET, NONE
        }
        long val = 0;
        State state = State.NONE;
        public MyPair(long val, State state){
            this.val = val;
            this.state = state;
        }
        public MyPair(){

        }

    }
}