class Solution {
    fun countSubstrings(s: String): Int {
        var countt = 0
        for(i in 0 until s.length){
            for(j in 0..i){
                val strr = s.substring(j, i+1)
                if(isPalenderome(strr)){
                    countt++
                }
            }
        }
        return countt
    }
    
    fun isPalenderome(sFiltered: String): Boolean{
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
