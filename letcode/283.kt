class Solution {
    fun moveZeroes(nums: IntArray): Unit {
        var numZ = 0
        for(i in nums.indices){
            if (nums[i] == 0){
                numZ++   
            } else {
                nums[i - numZ] = nums[i]
                if(numZ != 0){
                    nums[i] = 0   
                }
            }
        }
    }
}
