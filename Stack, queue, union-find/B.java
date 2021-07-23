import java.util.Scanner;
import java.util.Stack;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Scanner scanner2 = new Scanner(line);
        Stack<Integer> stack = new Stack<>();
        int countSame = 0;
        Stack<Integer> temp = new Stack<>();
        int normCount = 0;
        int next = 0;
        while(scanner2.hasNextInt()){
            next = scanner2.nextInt();
            if(stack.size()==0){
                stack.push(next);
                countSame++;
                continue;
            }
            if(countSame >= 3&&next!=stack.peek()){
                while(countSame!=0){
                    normCount++;
                    countSame--;
                    stack.pop();
                }
                if (stack.size()!=0){
                    temp.push(stack.pop());
                    while(stack.size()!=0&&stack.peek()==temp.peek()){
                        temp.push(stack.pop());
                    }
                    while(temp.size()!=0){
                        stack.push(temp.pop());
                        countSame++;
                    }
                }
            }
            if(stack.size()==0||next == stack.peek()){
                countSame++;
            } else{
                countSame = 1;
            }
            stack.push(next);
        }
        if(countSame >= 3){
            while(countSame!=0){
                normCount++;
                countSame--;
                stack.pop();
            }
            if (stack.size()!=0){
                temp.push(stack.pop());
                while(stack.size()!=0&&stack.peek()==temp.peek()){
                    temp.push(stack.pop());
                }
                while(temp.size()!=0){
                    stack.push(temp.pop());
                    countSame++;
                }
            }
        }
        System.out.println(normCount);
    }
}
