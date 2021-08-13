class Solution {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        var cap = capacity
        val heap = PriorityQueue<IntArray>(Comparator { f, s -> f[2] - s[2] })
        trips.sortedBy{it[1]}
            .forEach{
                while(heap.size > 0 && heap.peek()[2] <= it[1]){
                    cap = Math.min(cap + heap.poll()[0], capacity)
                }
                if(it[0] > cap){
                    return false
                }
                cap -= it[0]
                heap.add(it)
            }
       return true     
    }
}
