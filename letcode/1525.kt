class Solution {
    fun numSplits(s: String): Int {
        val arrDistinct1 = IntArray(s.length)
        arrDistinct1[0] = 1
        val set1 = hashSetOf<Char>(s[0])
        for(i in 1 until s.length){
            set1.add(s[i])
            arrDistinct1[i] = set1.size 
        }
        val arrDistinct2 = IntArray(s.length)
        arrDistinct2[arrDistinct2.size - 1] = 1
        val set2 = hashSetOf<Char>(s[s.length - 1])
        for(i in s.length - 2 downTo 0){
            set2.add(s[i])
            arrDistinct2[i] = set2.size
        }
        var num = 0
        for(i in 0 until s.length - 1){
            if(arrDistinct1[i] == arrDistinct2[i+1]){
                num++
            }
        }
        return num
    }
}
