class Solution {
    fun minDifference(nums: IntArray): Int {
        if(nums.size <= 4){
            return 0
        }
        nums.sort()
        if(nums[0] == nums[1]){
            return 0
        }
        var minDiff = Int.MAX_VALUE
        var l = 0
        var r = nums.size - 1 - 3
        while (r < nums.size){
            minDiff = Math.min(minDiff, Math.abs(nums[r] - nums[l]))
            l++
            r++
        }
        return minDiff
    }
}
