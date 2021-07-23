import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class F{

    static ArrayList<Integer> arrResult = new ArrayList<>();

    static boolean isSorted(int index){
        if(arrResult.size()==1){
            return true;
        }
        if(index == 0){
            if(arrResult.get(0)<=arrResult.get(1)){
                return true;
            }
            return false;
        }
        if(arrResult.get(index)<= arrResult.get(index+1)){
            return isSorted(index-1);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(scanner.nextLine());
        int n = scanner2.nextInt();
        scanner2 = new Scanner(scanner.nextLine());
        Stack<Integer> stack = new Stack<>();
        ArrayList<String> commands = new ArrayList<>();
        while(scanner2.hasNextInt()){
            int m = scanner2.nextInt();
            while(stack.size()!=0&&m>stack.peek()){
                arrResult.add(stack.pop());
                commands.add("pop");
            }
            stack.push(m);
            commands.add("push");
        }
        while(stack.size()!=0){
            arrResult.add(stack.pop());
            commands.add("pop");
        }
        if(!isSorted(arrResult.size()-2)){
            System.out.println("impossible");
            return;
        }
        for(String res : commands){
            System.out.println(res);
        }
    }
}
