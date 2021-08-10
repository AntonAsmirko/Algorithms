class Solution {
    fun missingNumber(nums: IntArray): Int {
        nums.sort()
        for(i in 1 until nums.size){
            if(nums[i] != nums[i-1]+1){
                return nums[i] - 1
            }
        }
        if(nums[nums.size - 1] == nums.size){
            return 0
        }
        return nums.size
    }
}
