class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.equals("")){
            return 0;
        }
        char[] hayStackCh = haystack.toCharArray();
        char[] needleCh = needle.toCharArray();
        for(int i = 0; i < hayStackCh.length; i++){
            if(hayStackCh[i] == needleCh[0]){
                int j = 1;
                for(; j + i < hayStackCh.length 
                    && j < needleCh.length 
                    && hayStackCh[j+i] == needleCh[j]; j++){}
                if(j == needleCh.length){
                    return i;
                }
            }
        }
        return -1;
    }
}