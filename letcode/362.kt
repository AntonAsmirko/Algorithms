class HitCounter() {

    /** Initialize your data structure here. */
    
    val hits = TreeMap<Int, Int>()

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    fun hit(timestamp: Int) {
        if(hits.contains(timestamp)){
            hits[timestamp] = hits[timestamp]!! + 1
        } else {
            hits[timestamp] = 1
        }
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    fun getHits(timestamp: Int): Int {
        val tmp = hits.tailMap(timestamp - 300, false)
        var count = 0
        tmp.forEach{ count += it.value }
        return count
    }

}

/**
 * Your HitCounter object will be instantiated and called as such:
 * var obj = HitCounter()
 * obj.hit(timestamp)
 * var param_2 = obj.getHits(timestamp)
 */
 