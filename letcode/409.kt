class Solution {
    fun longestPalindrome(s: String): Int {
        val hm = HashMap<Char, Int>()
        s.forEach{
            if(hm[it] != null){
                hm[it] = hm[it]!! + 1
            } else {
                hm[it] = 1
            }
        }
        var len = 0
        if(hm.any{it.value % 2 == 1}) len++
        hm.forEach{
            if(it.value % 2 == 0){
                len += it.value
            } else if(it.value > 1) {
                len += it.value - 1
            }
        }
        return len
    }
}
