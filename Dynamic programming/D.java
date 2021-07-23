import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[][] dp = new long[10][n+1];
        for(int i = 0; i< 10; i++){
            dp[i][1] = 1;
        }
        long a = 1_000_000_000;
        for(int j = 2; j < n+1; j++){
            dp[1][j] = (dp[6][j-1]+ dp[8][j-1])%a;
            dp[2][j] = (dp[9][j-1]+ dp[7][j-1])%a;
            dp[3][j] = (dp[4][j-1]+ dp[8][j-1])%a;
            dp[4][j] = (dp[3][j-1]+ dp[9][j-1]+dp[0][j-1])%a;
            dp[6][j] = (dp[1][j-1]+ dp[7][j-1]+ dp[0][j-1])%a;
            dp[7][j] = (dp[2][j-1]+ dp[6][j-1])%a;
            dp[8][j] = (dp[1][j-1]+ dp[3][j-1])%a;
            dp[9][j] = (dp[2][j-1]+ dp[4][j-1])%a;
            dp[0][j] = (dp[6][j-1]+ dp[4][j-1])%a;
        }
        System.out.println(((dp[1][n]+dp[2][n])%a+(dp[3][n]+dp[4][n])%a+(dp[5][n]+dp[6][n])%a+(dp[7][n]+dp[9][n])%a)%a);
    }
}
