import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] coasts = new long[n];
        for(int i = 0; i < n; i++){
            coasts[i] = scanner.nextLong();
        }
        int[] dp = new int[n];
        int[] prev = new int[n];
        dp[0] = 1;
        prev[0] = -1;
        for(int i = 1; i < n; i++){
            int maxIncreasingIndex = -1;
            int maxIncreasingVal = 0;
            for(int j = i-1; j>=0; j--){
                if (coasts[j]<coasts[i]&&dp[j]>=maxIncreasingVal){
                    maxIncreasingVal = dp[j];
                    maxIncreasingIndex = j;
                }
            }
            dp[i] = maxIncreasingVal + 1;
            prev[i] = maxIncreasingIndex;
        }
        int maxIndex = 0;
        long maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if (dp[i]>maxValue){
                maxValue = dp[i];
                maxIndex = i;
            }
        }
        System.out.println(dp[maxIndex]);
        int prevv = maxIndex;
        Stack<Long> stack = new Stack<>();
        while (prevv!=-1){
            stack.push(coasts[prevv]);
            prevv = prev[prevv];
        }
        while (stack.size()!=0){
            System.out.print(stack.pop()+" ");
        }
    }
}
