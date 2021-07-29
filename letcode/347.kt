class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val numsCounted = HashMap<Int, Int>()
        nums.forEach { num ->
            if (numsCounted[num] != null) {
                numsCounted[num] = numsCounted[num]!! + 1
            } else {
                numsCounted[num] = 1
            }
        }
        return numsCounted.toList().sortedBy { it.second }.reversed().map { it.first }.take(k).toIntArray()
    }
}
