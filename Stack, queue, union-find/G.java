import java.util.ArrayList;
import java.util.Scanner;

public class D{
    public static void main(String[] args) {
        ArrayList<String> strArr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        UF uf = new UF(n);
        while(scanner.hasNext()){
            String command = scanner.next();
            if(command.equals("union")){
                int u = scanner.nextInt()-1;
                int v = scanner.nextInt()-1;
                uf.union(uf.nodeArr.get(u), uf.nodeArr.get(v));
            }
            if(command.equals("get")){
                int u = scanner.nextInt()-1;
                strArr.add(uf.get(uf.nodeArr.get(u)));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strArr.size();i++){
            sb.append(strArr.get(i));
            if(i!=strArr.size()-1){
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}

class UF{

    ArrayList<Node> nodeArr = new ArrayList<>();

    public UF(int num){
        for(int i = 0; i < num; i++){
            nodeArr.add(new Node(i+1));
        }
    }

    public void union(Node x, Node y){
        Node repX = find(x);
        Node repY = find(y);
        if(repX.size< repY.size){
            Node tmp = repX;
            repX = repY;
            repY = tmp;
        }
        if(repX.val == repY.val) return;
        repY.ancestor = repX;
        if(repY.max>repX.max){
            repX.max = repY.max;
        }
        if(repY.min<repX.min){
            repX.min = repY.min;
        }
        repX.size+=repY.size;
    }

    public String get(Node x){
        Node repX = find(x);
        return repX.min+" "+repX.max+" "+repX.size;
    }

    public Node find (Node x){
        if (x.ancestor == null){
            return x;
        } else{
            Node rep = find(x.ancestor);
            x.ancestor = rep;
            return rep;
        }

    }

}

class Node{
    int val;
    int max;
    int min;
    int size = 1;
    Node ancestor;
    public Node(int val){
        this.val = val;
        this.max = val;
        this.min = val;
    }
}
