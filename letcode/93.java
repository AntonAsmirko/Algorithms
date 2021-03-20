class Solution {
    
    List<String> result;
    char[] chRep;
    
    private boolean canEatOneDigit(int start){
        return start < chRep.length;
    }
    
    private boolean canEatTwoDigits(int start){
        return start < chRep.length - 1 && chRep[start] != '0';
    }
    
    private boolean canEatThreeDigits(int start){
        if(!(start < chRep.length - 2 && chRep[start] != '0')){
            return false;
        }
        int f = (int)(chRep[start] - '0'), s = (int)(chRep[start + 1] - '0'), t = (int)(chRep[start + 2] - '0');
        return f*100 + s * 10 + t <= 255;        
    }
    
    private String eatOne(int start){
        StringBuilder sb = new StringBuilder();
        sb.append(chRep[start]);
        return sb.toString();
    }
    
    private String eatTwo(int start){
        StringBuilder sb = new StringBuilder();
        sb.append(chRep[start]);
        sb.append(chRep[start+1]);
        return sb.toString();
    }
    
    private String eatThree(int start){
        StringBuilder sb = new StringBuilder();
        sb.append(chRep[start]);
        sb.append(chRep[start + 1]);
        sb.append(chRep[start + 2]);
        return sb.toString();
    }
    
    private boolean correct(int start){
        return start == chRep.length;
    }
    
    private void rec(int start, String current, int numInts){
        if (numInts == 4){
            if(correct(start)){
                result.add(current);
            }
            return;
        }
        String newStr;
        if(canEatOneDigit(start)){
            newStr = current + eatOne(start);
            if(numInts != 3){
                newStr += ".";
            }
            rec(start+1, newStr, numInts + 1);
        }
        if(canEatTwoDigits(start)){
            newStr = current + eatTwo(start);
            if(numInts != 3){
                newStr += ".";
            }
            rec(start + 2, newStr, numInts + 1);
        }
        if(canEatThreeDigits(start)){
            newStr = current + eatThree(start);
            if(numInts != 3){
                newStr += ".";
            }
            rec(start + 3, newStr, numInts + 1);
        } 
    }
    
    public List<String> restoreIpAddresses(String s) {
        result = new LinkedList<>();
        chRep = s.toCharArray();
        rec(0, "", 0);
        return result;
    }
}