class Solution {
    fun isSubsequence(s: String, t: String): Boolean {
        var sI = 0
        var tI = 0
        if(s.length == 0){
            return true
        }
        while(tI < t.length){
            if(s[sI] == t[tI]){
                sI++
            }
            if(sI == s.length){
                return true
            }
            tI++
        }
        return false
    }
}
