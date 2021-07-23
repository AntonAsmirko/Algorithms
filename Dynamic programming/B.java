import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
            Scanner scanner = new Scanner(br);
            int n = scanner.nextInt(), m = scanner.nextInt();
            int[][] coastArr = new int[n][m];
            MyPair[][] dp = new MyPair[n][m];
            for(int i = 0; i < n ; i ++){
                for(int j = 0; j < m; j++){
                    coastArr[i][j] = scanner.nextInt();
                }
            }
            dp[0][0] = new MyPair(coastArr[0][0], new StringBuilder(""));
            for(int i = 1; i < n; i++){
                StringBuilder sb = new StringBuilder(dp[i-1][0].path);
                sb.append("D");
                dp[i][0] = new MyPair(dp[i-1][0].num+coastArr[i][0],sb);
                dp[i][0].numChecks++;
            }
            for(int i = 1; i < m; i++){
                StringBuilder sb = new StringBuilder(dp[0][i-1].path);
                sb.append("R");
                dp[0][i] = new MyPair(dp[0][i-1].num+coastArr[0][i],sb);
                dp[0][i].numChecks++;
            }
            for(int i = 1; i < n; i++){
                for(int j = 1; j < m; j++){
                    if (dp[i-1][j].num>dp[i][j-1].num){
                        StringBuilder sb = new StringBuilder(dp[i-1][j].path);
                        sb.append("D");
                        dp[i][j] = new MyPair(dp[i-1][j].num+coastArr[i][j],sb);
                    } else {
                        StringBuilder sb = new StringBuilder(dp[i][j-1].path);
                        sb.append("R");
                        dp[i][j] = new MyPair(dp[i][j-1].num+coastArr[i][j],sb);
                    }
                    dp[i-1][j].numChecks++;
                    dp[i][j-1].numChecks++;
                    if (dp[i-1][j].numChecks>=2){
                        dp[i-1][j] = null;
                    }
                    if (dp[i][j-1].numChecks>=2){
                        dp[i][j-1] = null;
                    }
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output.txt")));
            bw.write(dp[n-1][m-1].num+"\n");
            bw.write(dp[n-1][m-1].path.toString());
            bw.close();
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
class MyPair{
    int num;
    byte numChecks = 0;
    StringBuilder path;
    public MyPair(int num, StringBuilder path){
        this.num =num;
        this.path = path;
    }
}
