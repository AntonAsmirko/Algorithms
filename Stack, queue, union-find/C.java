import java.util.ArrayList;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> queue = new ArrayList<>();
        int counter = 0;
        TreeMap<Integer, Shesh> iiii = new TreeMap<>();
        int m;
        for(int i = 0; i < n; i++){
            m = scanner.nextInt();
            if(m == 1){
                int id = scanner.nextInt();
                iiii.put(id, new Shesh(queue.size(), counter));
                queue.add(id); 
            } else if(m == 2){
                queue.remove(0);
                counter++;
            } else if (m == 3){
                queue.remove(queue.size()-1);
            } else if (m == 4){
                int k = scanner.nextInt();
                Shesh shesh = iiii.get(k);
                System.out.println(shesh.pos-(counter-shesh.count));
            } else if (m == 5){
                System.out.println(queue.get(0));
            }
        }
    }
}

class Shesh{
    int pos;
    int count;
    Shesh(int pos, int count){
        this.pos = pos;
        this.count = count;
    }
}
