import java.util.Scanner;
import java.util.LinkedList;

public class D{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MyLL myLL = new MyLL();
        for(int i = 0; i < n; i++){
            String sign = scanner.next();
            if(sign.equals("+")){
                myLL.insert(scanner.nextInt());
            } else if(sign.equals("*")){
                myLL.insertMiddle(scanner.nextInt());
            }
            else {
                System.out.println(myLL.delete());
            }
        }
    }
}

class MyLL{
       int size = 0;
       Node head = new Node(null, null , null);
       Node tail = new Node(null, head, null);
       Node middle = head;

       public MyLL(){
           head.rNode = tail;
       }

       public void insert(int a){
           Node toInsert = new Node(a, tail.lNode, tail);
           tail.lNode.rNode = toInsert;
           tail.lNode = toInsert;
           if(size%2==0){
               middle = middle.rNode;
           }
           size++;
       }
       public void insertMiddle(int a){
           Node toInsert;
           if(size !=0) {
               toInsert = new Node(a, middle, middle.rNode);
               middle.rNode.lNode = toInsert;
               middle.rNode = toInsert;
           } else {
               toInsert = new Node(a, head, tail);
               head.rNode = toInsert;
               tail.lNode = toInsert;
           }
           if (size%2==0) {
               middle = toInsert;
           }
           size++;
       }
       public Integer delete(){
           Integer val = head.rNode.val;
           Node tmp = head.rNode.rNode;
           head.rNode = tmp;
           tmp.lNode = head;
           if(size%2==0){
               middle = middle.rNode;
           }
           size--;
           if(size == 0){
               middle = head;
               head.rNode = tail;
               tail.lNode = head;
           }
           return val;
       }
}

class Node{
    Integer val;
    Node lNode;
    Node rNode;
    public Node(Integer val, Node lNode, Node rNode){
        this.val = val;
        this.lNode = lNode;
        this.rNode = rNode;
    }
    public Node(){}
}
