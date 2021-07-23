class Solution {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var numRemoved = 0
        for(i in nums.indices){
            if(nums[i] == `val`){
                numRemoved++
                continue
            }
            nums[i - numRemoved] = nums[i]
        }
        return nums.size - numRemoved
    }
}
