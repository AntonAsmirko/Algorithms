class Solution {
    fun maxProfit(prices: IntArray): Int {
        var maxx = Int.MIN_VALUE
        val treeSet = TreeSet<Int>()
        for(i in prices){
            treeSet.add(i)
            if(i - treeSet.first() > maxx){
                maxx = i - treeSet.first()
            }
        }
        return maxx
    }
}