class Solution {
    
    private boolean isNotLetter(char ch){
        return !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z');
    }
    
    private static class Pair{
        int f;
        char s;
        public Pair(int f, char s){
            this.f = f;
            this.s = s;
        }
    }
    
    public String reverseOnlyLetters(String S) {
        char[] ch = S.toCharArray();
        ArrayList<Pair> nonLetterPos = new ArrayList<>();
        ArrayList<Character> cleared = new ArrayList<>();
        for(int i = 0; i < ch.length; i++){
            if(isNotLetter(ch[i])){
                nonLetterPos.add(new Pair(i, ch[i]));
            } else {
                cleared.add(ch[i]);
            }
        }
        for(int i = 0, j = cleared.size() - 1; i < j; i++, j--){
            char tmp = cleared.get(i);
            cleared.set(i, cleared.get(j));
            cleared.set(j, tmp);
        }
        char[] res = new char[S.length()];
        for(Pair p : nonLetterPos){
            res[p.f] = p.s;
        }
        for(int i =0, j = 0; i < S.length() && j < cleared.size(); i++){
            if(res[i] != 0){
                continue;
            }
            res[i] = cleared.get(j);
            j++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.length; i++){
            sb.append(res[i]);
        }
        return sb.toString();
    }
}