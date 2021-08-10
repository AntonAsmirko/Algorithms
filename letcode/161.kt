class Solution {
    fun isOneEditDistance(s: String, t: String): Boolean {
        if(Math.abs(s.length - t.length) > 1){
            return false
        }
        if(s == t){
            return false
        }
        if(s.length == t.length){
            var diffCount = 0
            for(i in s.indices){
                if(s[i] != t[i]){
                    diffCount++
                }
                if(diffCount > 1){
                    return false
                }
            }
        } else {
            var max = ""
            var min = ""
            if(s.length > t.length){
                max = s
                min = t
            } else {
                max = t
                min = s
            }
            for(i in min.indices){
                if(max[i] != min[i]){
                    for(j in i+1 until max.length){
                        if(max[j] != min[j - 1]){
                            return false
                        }
                    }
                }
            }
        }
        return true
    }
}
