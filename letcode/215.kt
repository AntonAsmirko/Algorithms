class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        return nums.sortedBy{-it}[k-1]
    }
}
 