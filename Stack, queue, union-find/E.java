import java.util.*;

public class E {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        String s = scanner.nextLine();
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++){
            if (arr[i].equals("+")){
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1+num2);
            } else if (arr[i].equals("*")){
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1*num2);
            } else if (arr[i].equals("-")){
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1-num2);
            } else if (arr[i].equals("/")){
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1+num2);
            } else stack.push(Integer.parseInt(arr[i]));
        }
        System.out.println(stack.peek());
    }
}
