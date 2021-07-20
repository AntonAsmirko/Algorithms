class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        val first = strs[0]
        if(strs.size == 1){
            return first
        }
        for(i in first.indices){
            for(j in strs){
                if(j.length <= i || j[i] != first[i]){
                    return first.substring(0, i)
                }
            }
        }
        return first
    }
}