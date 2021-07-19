class Solution {
    fun maxSubArray(nums: IntArray): Int {
        val prefixSum = IntArray(nums.size)
        prefixSum[0] = nums[0]
        for(i in 1 until prefixSum.size){
            prefixSum[i] = nums[i] + prefixSum[i - 1]
        }
        val treeSet = TreeSet<Int>()
        treeSet.add(0)
        var maxx = Int.MIN_VALUE
        for(i in prefixSum){
            if(i - treeSet.first() > maxx){
                maxx = i - treeSet.first()
            }
            treeSet.add(i)
        }
        return maxx
    }
}
