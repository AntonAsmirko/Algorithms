import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Diff {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String first = scanner.next(), second = scanner.next();
            Integer[][] dp = new Integer[first.length()+1][second.length()+1];
            for(int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for(int i = 0; i < first.length()+1; i++){
                dp[i][0] = i;
            }
            for(int i = 1; i < second.length()+1; i++){
                dp[0][i] = i;
            }
            for(int i = 1; i < first.length()+1; i++){
                for(int j = 1; j < second.length()+1; j++){
                    Integer[] tmp = new Integer[4];
                    if (first.charAt(i-1) == second.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]<dp[i][j]?dp[i-1][j-1]:dp[i][j];
                    }
                    dp[i][j] = dp[i-1][j-1]+1<dp[i][j]?dp[i-1][j-1]+1:dp[i][j];
                    dp[i][j] = dp[i][j-1]+1<dp[i][j]?dp[i][j-1]+1:dp[i][j];
                    dp[i][j] = dp[i-1][j]+1<dp[i][j]?dp[i-1][j]+1:dp[i][j];
                }
            }
            System.out.println(String.valueOf(dp[first.length()][second.length()]));
            scanner.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
