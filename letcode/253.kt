class Solution {
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        val minHeap = PriorityQueue<Int>()
        intervals.sortedBy{it[0]}
            .forEach{
                if (minHeap.size != 0 && it[0] >= minHeap.peek()){
                    minHeap.poll()
                }
                minHeap.add(it[1])
            }
            return minHeap.size
    }
}
