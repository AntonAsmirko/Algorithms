import java.util.Scanner;

public class ChiniceWall {
    private static MyPair[] max;
    static int tmp;

    private static void propagate(int x, int lx, int rx) {
        if (!max[x].drop) {
            return;
        }

        if (rx - lx <= 1){
            return;
        }

        if (max[2*x+1].wallMinHeight < max[x].wallMinHeight){
            max[2*x+1].wallMinHeight = max[x].wallMinHeight;
            max[x].drop = false;
            max[2*x+1].drop = true;
        }

        if (max[2*x+2].wallMinHeight < max[x].wallMinHeight){
            max[2*x+2].wallMinHeight = max[x].wallMinHeight;
            max[x].drop = false;
            max[2*x+2].drop = true;
        }
    }

    private static MyPair max(int l, int r, int x, int lx, int rx){
        propagate(x, lx, rx);
        if (l >= rx || lx >= r){
            return new MyPair(x);
        }
        if (lx >= l && rx <= r){
            return max[x];
        }
        int m = (lx + rx)/2;
        MyPair sl = max(l, r, 2*x +1, lx, m);
        MyPair sr = max(l, r, 2*x+2, m, rx);
        return fnc(sl, sr);
    }

    private static MyPair fnc(MyPair a, MyPair b){
        return new MyPair(Math.min(a.wallMinHeight, b.wallMinHeight) == a.wallMinHeight?
                a.wallMinHeightIndexStart : b.wallMinHeightIndexStart, Math.min(a.wallMinHeight, b.wallMinHeight));
    }

    private static void set(int l, int r, long v, int x, int lx, int rx){
        propagate(x, lx, rx);
        if (l >= rx || lx >= r){
            return;
        }
        if (lx >= l && rx <= r){
            if (max[x].wallMinHeight < v) {
                max[x].wallMinHeight = v;
                if (rx - lx > 1) {
                    max[x].wallMinHeightIndexStart =
                            max[2 * x + 1].wallMinHeight > max[x].wallMinHeight ?
                                    max[2 * x + 2].wallMinHeightIndexStart
                                    : max[2 * x + 1].wallMinHeightIndexStart;
                } else if (rx - lx == 1){
                    max[x].wallMinHeightIndexStart = x - tmp + 1;
                }
                max[x].drop = true;
            }
            return;
        }

        int m = (lx+rx)/2;
        set(l, r, v, 2*x+1, lx, m);
        set(l, r, v, 2*x+2, m, rx);
        max[x] = fnc(max[2*x+1], max[2*x+2]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int m = scanner.nextInt();
        tmp = (int)Math.pow(2,(int)(Math.log(size)/Math.log(2))+1);
        max = new MyPair[(int)Math.pow(2,(int)(Math.log(size)/Math.log(2))+1)*2-1];
        for(int i = 0; i < max.length; i++){
            max[i] = new MyPair(i);//shesh
        }

        for(int i = tmp-1; i < tmp-1+size; i++){
            max[i].wallMinHeight = 0;
            max[i].wallMinHeightIndexStart = i - tmp + 1;
        }
        for(int i = tmp - 2; i>=0; i--){
            max[i] = fnc(max[i*2+1], max[i*2+2]);
        }
        for(int i = 0; i < m; i++){
            String st = scanner.next();
            switch (st){
                case "defend":
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
                case "attack":
                    int first = scanner.nextInt() - 1;
                    int second = scanner.nextInt();
                    MyPair tmmpp = max(first, second, 0, 0, tmp);
                    System.out.println(tmmpp.wallMinHeight+ " "+(tmmpp.wallMinHeightIndexStart +1));
                    break;
            }
        }
    }

    static class MyPair{
        long wallMinHeight = 0;
        int wallMinHeightIndexStart = 0;
        boolean drop = false;
        MyPair(int wallMinHeightIndexStart){
            this.wallMinHeightIndexStart = wallMinHeightIndexStart;
            wallMinHeight = Long.MAX_VALUE;
        }

        MyPair(int wallMinHeightIndex, long wallMinHeight){
            this.wallMinHeightIndexStart = wallMinHeightIndex;
            this.wallMinHeight = wallMinHeight;
        }

    }
}