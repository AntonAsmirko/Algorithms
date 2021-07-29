class Solution {
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        var l1 = 0
        var l2 = 0
        val res = mutableListOf<IntArray>()
        while(l1 < firstList.size && l2 < secondList.size){
            val f = firstList[l1]
            val s = secondList[l2]
            if(f[1] < s[0]){
                l1++
                continue
            }
            if(s[1] < f[0]){
                l2++
                continue
            }
            res.add(intArrayOf(Math.max(f[0], s[0]), Math.min(f[1], s[1])))
            if(f[1] > s[1]){
                l2++
            }
            if(s[1] > f[1]){
                l1++
            }
            if(s[1] == f[1]){
                l1++
                l2++
            }
        }
        return res.toTypedArray()
    }
}
