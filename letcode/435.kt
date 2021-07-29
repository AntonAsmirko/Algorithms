class Solution {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        val sortedI = intervals.sortedBy{it[1]}.toTypedArray()
        val res = LinkedList<IntArray>().apply{
            add(sortedI[0])
        }
        var count = 0
        for(i in 1 until sortedI.size){
            if(sortedI[i][0] < res.getLast()[1]){
                count++
            } else {
                res.add(sortedI[i])
            }
        }
        return count
    }
}
