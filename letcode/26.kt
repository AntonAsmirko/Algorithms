class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if(nums.size < 2){
            return nums.size
        }
        var i = 1
        var countRemoved = 0
        while(i < nums.size){
            if(nums[i] == nums[i - 1]){
                countRemoved++
                i++
                continue
            }
            nums[i - countRemoved] = nums[i]
            i++
        }
        return nums.size - countRemoved
    }
}
