class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var prev = 0
        var cur = 0
        var max = 0
        if(nums.all{it == 1}){
            return nums.size
        }
        for(i in nums){
            if(i == 1){
                cur++
                max = Math.max(max, prev + cur + 1)
            } else {
                max = Math.max(max, prev + cur + 1)
                prev = cur
                cur = 0
            }
        }
        return max
    }
}
