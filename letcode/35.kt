// [1,3,5,6]
class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = -1
        var r = nums.size - 1
        var mid = l + (r - l) / 2
        while(r - l > 1){
            mid = l + (r - l) / 2
            if(nums[mid] > target){
                r = mid
            } else if (nums[mid] == target){
              return mid  
            } else {
                l = mid
            }
        }
        return if(nums[r] < target) r+ 1 else r
    }
}