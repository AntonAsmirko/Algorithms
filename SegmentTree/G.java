import java.util.*;

public class Windows {
    private static MaxElement[] max;
    private static MyPair[] set;
    private static ArrayList<Line> lines;
    private static final int BIAS = 2 * 100_000;

    private static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1)
            return;
        if (set[x].state == MyPair.State.NONE)
            return;
        if (set[x].state == MyPair.State.ADD) {
            set[2 * x + 1].val += set[x].val;
            set[2 * x + 1].state = set[x].state;
            max[2 * x + 1].val += set[x].val;
            set[2 * x + 2].val += set[x].val;
            set[2 * x + 2].state = set[x].state;
            max[2 * x + 2].val += set[x].val;
            set[x].val = 0;
            set[x].state = MyPair.State.NONE;
        }

    }

    private static void add(int l, int r, int v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            set[x].val += v;
            set[x].state = MyPair.State.ADD;
            max[x].val += v;
            return;
        }
        int m = (lx + rx) / 2;
        add(l, r, v, 2 * x + 1, lx, m);
        add(l, r, v, 2 * x + 2, m, rx);
        max[x] = fnc(max[2 * x + 1], max[2 * x + 2]);
    }

    private static MaxElement fnc(MaxElement a, MaxElement b) {
        return new MaxElement(a.val >= b.val ? a : b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        lines = new ArrayList<>();
        int yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;
        for (int i = 0, j = 0; i < n; i++, j += 2) {
            int x1 = scanner.nextInt(), y1 = scanner.nextInt(),
                    x2 = scanner.nextInt(), y2 = scanner.nextInt();
            yMax = Math.max(Math.max(y1, y2), yMax);
            yMin = Math.min(Math.min(y1, y2), yMin);
            lines.add(new Line(x1, y1, y2, Line.Type.OPEN));
            lines.add(new Line(x2, y1, y2, Line.Type.CLOSE));
        }
        Collections.sort(lines);
        int sizeYY = yMax - yMin + 1;
        int tmp = (int) Math.pow(2, (int) (Math.log(sizeYY) / Math.log(2)) + 1);
        max = new MaxElement[(int) Math.pow(2, (int) (Math.log(sizeYY) / Math.log(2)) + 1) * 2 - 1];
        set = new MyPair[max.length];
        for (int i = 0; i < set.length; i++) {
            set[i] = new MyPair();
        }

        for (int i = tmp - 1; i < max.length; i++) {
            max[i] = new MaxElement(0, i - (tmp - 1) + yMin);
        }

        for (int i = tmp - 2; i >= 0; i--) {
            max[i] = fnc(max[2 * i + 1], max[2 * i + 2]);
        }

        int result = Integer.MIN_VALUE;
        Integer resultPos = null;
        Integer resultX = null;
        for (int i = 0; i < lines.size(); i++) {
            add(lines.get(i).yL - yMin, lines.get(i).yH - yMin + 1, lines.get(i).type == Line.Type.OPEN ? 1 : -1, 0, 0, tmp);
            MaxElement maxElement = max[0];
            if (maxElement.val > result) {
                result = maxElement.val;
                resultPos = maxElement.pos;
                resultX = lines.get(i).x;
            }
        }
        System.out.println(result + "\n" + resultX + " " + (resultPos));
    }

    static class MyPair {
        enum State {
            ADD, SET, NONE
        }

        int val = 0;
        State state = State.NONE;
        int minPos;

        public MyPair(int val, State state) {
            this.val = val;
            this.state = state;
        }

        public MyPair() {

        }

    }

    static class MaxElement {
        int val;
        int pos;

        MaxElement(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }

        MaxElement(MaxElement maxElement) {
            this.val = maxElement.val;
            this.pos = maxElement.pos;
        }
    }

    static class Line implements Comparable<Line> {

        @Override
        public int compareTo(Line line) {
            if (this.x > line.x) return 1;
            else if (this.x < line.x) return -1;
            else {
                if (this.type == Type.OPEN && line.type == Type.OPEN) return 0;
                else if (this.type == Type.OPEN && line.type == Type.CLOSE) return -1;
                else if (this.type == Type.CLOSE && line.type == Type.OPEN) return 1;
                else return 0;
            }
        }

        enum Type {
            OPEN, CLOSE
        }

        int x, yL, yH;
        Type type;

        public Line(int x, int yL, int yH, Type type) {
            this.x = x;
            this.yL = yL;
            this.yH = yH;
            this.type = type;
        }
    }

}