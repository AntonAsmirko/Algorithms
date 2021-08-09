class Solution {
    fun longestSubarray(nums: IntArray): Int {
        var prev = 0
        if(nums.all{it == 1}){
            return nums.size - 1
        }
        var cur = if(nums[0] == 1) 1 else 0
        var max = Math.max(cur, 0)
        for(i in 1 until nums.size){
            if(nums[i] == 1){
                cur++
                max = Math.max(prev + cur, max)
            }
            if(nums[i] != 1){
                prev = cur
                cur = 0
            }
            if(nums[i] != 1 && nums[i - 1] != 1){
                prev = 0
            }
        }
        return max
    }
}
