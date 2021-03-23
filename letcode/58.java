class Solution {
    public int lengthOfLastWord(String s) {
        char[] chRep = s.toCharArray();
        boolean ok = false;
        for(int i = 0; i < chRep.length; i++){
            if(chRep[i] != ' '){
                ok = true;
                break;
            }
        }
        if(!ok){
            return 0;
        }
        int i = chRep.length - 1;
        for(; i >= 0 && chRep[i] == ' '; i--){}
        int r = i;
        int j = i;
        for(; j >= 0 && chRep[j] != ' '; j--){}
        return r - j; 
    }
}