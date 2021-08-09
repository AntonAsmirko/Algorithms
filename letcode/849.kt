class Solution {
    fun maxDistToClosest(seats: IntArray): Int {
        val segments = seats.joinToString(separator = "")
            .split('1')
            .filter { it.isNotEmpty() }
        return segments.mapIndexed { index, s -> Pair(index, s) }
            .groupBy { it.first == 0 && seats[0] == 0 || it.first == segments.size - 1 && seats[seats.size - 1] == 0 }
            .map { Pair(it.key, it.value.map { it.second }) }
            .fold(0) { acc, it ->
                val kek = if (it.first) {
                    it.second.fold(0) { acc, it -> Math.max(acc, it.length) }
                } else {
                    it.second.fold(0) { acc, s ->
                        val sLen = s.length
                        Math.max(
                            acc, if (sLen % 2 == 1) {
                                sLen / 2
                            } else {
                                sLen / 2 - 1
                            } + 1
                        )
                    }
                }
                Math.max(acc, kek)
            }
    }
}
