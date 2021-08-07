class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val prefixSum = IntArray(nums.size + 1)
        prefixSum[1] = nums[0]
        for (i in 2 until prefixSum.size) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1]
        }
        val sett = hashMapOf<Int, Int>()
        var res = 0
        prefixSum.forEach {
            if (sett.containsKey(it)) {
                res+=sett[it]!!
            }
            sett.merge(k + it, 1) { f, s -> f + s }
        }
        return res
    }
}
