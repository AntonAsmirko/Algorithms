class Solution {
    
    private boolean[] toBonVector(int num, int size){
        boolean[] res = new boolean[size];
        if (num == 0){
            return res;
        }
        int i = size - 1;
        while(num != 0){
            int rem = num % 2;
            if (rem != 0) {
                res[i] = true;
            }
            i--;
            num /= 2;
        }
        return res;
    }
    
    private boolean isPalindrome(char[] chRep, int l, int r){
        if (r - l == 0){
            return false;
        }
        if (r - l == 1){
            return true;
        }
        for(int i = l, j = r - 1; i < j; i++, j--){
            if(chRep[i] != chRep[j]){
                return false;
            }
        }
        return true;
    }
    
    private String subString(char[] chRep, int l, int r){
        StringBuilder sb = new StringBuilder();
        for(int i = l; i < r; i++){
            sb.append(chRep[i]);
        }
        return sb.toString();
    }
    
    private List<String> checkPartition(boolean[] partition, char[] chRep){
        int lastBreak = 0;
        List<String> res = new LinkedList<>();
        for(int i = 0; i < chRep.length; i++){
            if(partition[i]){
                if (isPalindrome(chRep, lastBreak, i + 1)){
                    res.add(subString(chRep, lastBreak, i+1));
                    lastBreak = i + 1;
                } else {
                    return null;
                }
            }
        }
        if (isPalindrome(chRep, lastBreak, chRep.length)){
                    res.add(subString(chRep, lastBreak, chRep.length));
                } else {
                    return null;
                }
        if(res.size() == 0){
            return null;
        }
        return res;
    }
    
    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<List<String>>();
        char[] chRep = s.toCharArray();
        for(int i = 0; i < ((Double)Math.pow(2.0, ((Integer)s.length()).doubleValue())).intValue(); i++){
            List<String> partition = checkPartition(toBonVector(i, s.length()), chRep);
            if(partition != null){
                result.add(partition);
            }
        }
        return result;
    }
}