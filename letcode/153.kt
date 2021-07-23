class Solution {
    fun findMin(nums: IntArray): Int {
        var l = 0
        var r = nums.size
        var mid = l + (r - l) / 2
        while (r - l > 5) {
            mid = l + (r - l) / 2
            when {
                nums[l] < nums[mid] && nums[mid] < nums[r - 1] -> r = mid
                nums[l] > nums[mid] && nums[mid] < nums[r - 1] -> r = mid + 1
                nums[l] < nums[mid] && nums[mid] > nums[r - 1] -> l = mid + 1
            }
        }
        var minn = Int.MAX_VALUE
        for(i in l until r){
            minn = Math.min(nums[i], minn)
        }
        return minn
    }
}
