import java.util.ArrayList;
import java.util.Scanner;

public class Kucha {

    static ArrayList<Integer> arrayList = new ArrayList<>();

    private static void swap(Integer a, Integer b){
        int tmp = arrayList.get(a);
        arrayList.set(a, arrayList.get(b));
        arrayList.set(b, tmp);
    }

    private static void siftDown(Integer index){
        Integer val = arrayList.get(index);
        Integer val1, val2;
        try {
            val1 = arrayList.get(2*index+1);
        } catch (Exception e){
            val1 = -1;
        }
        try {
            val2 = arrayList.get(2*index+2);
        } catch (Exception e){
            val2 = -1;
        }
        if(2*index+1<arrayList.size()&&Math.max(val1,val2)==val1&&val1>val){
            swap(index, 2*index+1);
            index = 2*index+1;
            siftDown(index);
        } else if (2*index+2<arrayList.size()&&Math.max(val1,val2)==val2&&val2>val){
            swap(index, 2*index+2);
            index = 2*index+2;
            siftDown(index);
        }
    }

    static int getMax(){
        swap(0, arrayList.size()-1);
        int result = arrayList.get(arrayList.size()-1);
        arrayList.remove(arrayList.size()-1);
        if (arrayList.size()!=0) siftDown(0);
        return result;
    }

    static void siftUp(Integer index){
        if (index!=0&&arrayList.get(index)>arrayList.get((index-1)/2)){
            swap(index, (index-1)/2);
            siftUp((index-1)/2);
        }
    }

    static void insert(Integer x){
        arrayList.add(x);
        siftUp(arrayList.size()-1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            a = scanner.nextInt();
            if (a == 0) {
                b = scanner.nextInt();
                insert(b);
            }
            if (a == 1) System.out.println(getMax());
        }
    }
}
