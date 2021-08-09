class RecentCounter() {

    var requests = TreeSet<Int>()
    
    fun ping(t: Int): Int {
        requests.add(t)
        requests = requests.tailSet(t - 3000) as TreeSet<Int>
        return requests.size
    }

}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * var obj = RecentCounter()
 * var param_1 = obj.ping(t)
 */
 