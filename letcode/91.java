class Solution {
    
    private Set<Integer> alowed = Set.of(10 ,11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
    
    private Integer charsToInt(char f, char s){
        int fi = (int)(f - '0');
        int si = (int)(s - '0');
        return (Integer) fi*10 + si;
    }
    
    public int numDecodings(String s) {
        char[] chRep = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        if(chRep[0] == '0'){
            return 0;
        }
        
        dp[0] = 1;
        for (int i = 1; i < chRep.length + 1; i++){
            if(chRep[i-1] != '0'){
                dp[i] += dp[i-1];
            }
            if(i >= 2 && alowed.contains(charsToInt(chRep[i-2], chRep[i - 1]))){
                dp[i] += dp[i - 2];
            }
        }
        return dp[dp.length - 1];
    }
}