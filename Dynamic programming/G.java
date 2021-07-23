import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static String str;
    static Integer[][] dp;
    static Integer[][] reconstrucAns;
    static ArrayList<String> ansRes = new ArrayList<>();
    static Map<Character, Character> parantesisPairsMap = Map.of(
        '(', ')',
        '{', '}',
        '[', ']'
    );

    static void generateAns(int l, int r){
        if (dp[l][r] == r-l+1){
            return;
        } else {
            if (dp[l][r]==0){
                ansRes.add(str.substring(l, r+1));
                return;
            }
            if (reconstrucAns[l][r] == Integer.MIN_VALUE){
                ansRes.add(String.valueOf(str.charAt(l)));
                generateAns(l+1, r-1);
                ansRes.add(String.valueOf(str.charAt(r)));
                return;
            }
            generateAns(l, reconstrucAns[l][r]);
            generateAns(reconstrucAns[l][r]+1, r);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        str = scanner.next();
        dp = new Integer[str.length()][str.length()];
        reconstrucAns = new Integer[str.length()][str.length()];
        for (Integer[] now : dp){
            Arrays.fill(now, Integer.MAX_VALUE);
        }
        for(int i = 0; i < str.length(); i++){
            dp[i][i] = 1;
        }

        for(int i = 1; i < str.length(); i++){
            for(int j = i; j >= 0; j--){
                if (j!=i){
                    int minNum = Integer.MAX_VALUE;
                    int minPivot = Integer.MIN_VALUE;
                    if (parantesisPairsMap.containsKey(str.charAt(j))&&
                            parantesisPairsMap.get(str.charAt(j)) == str.charAt(i)){
                        minNum = i-j!=1?dp[j+1][i-1]:0;
                    }
                    for(int k = j; k < i; k++){
                        if (minNum > dp[j][k]+dp[k+1][i]){
                            minNum = dp[j][k]+dp[k+1][i];
                            minPivot = k;
                        }
                    }
                    dp[j][i] = minNum;
                    reconstrucAns[j][i]= minPivot;
                }
            }
        }
        generateAns(0 , str.length()-1);
        StringBuilder sb = new StringBuilder();
        for(String now : ansRes){
            sb.append(now);
        }
        System.out.println(sb.toString());
    }
}
