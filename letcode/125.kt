class Solution {
    fun isPalindrome(s: String): Boolean {
        var s = s.toLowerCase()
        var sFiltered = ""
        for(i in s){
            if(Character.isDigit(i) || Character.isLetter(i)){
                sFiltered+=i
            }
        }
        if(sFiltered.length < 2){
            return true
        }
        var l = 0
        var r = sFiltered.length - 1
        while(l < r){
            if(sFiltered[l] != sFiltered[r]){
                return false
            }
            l++
            r--
        }
        return true
    }
}
