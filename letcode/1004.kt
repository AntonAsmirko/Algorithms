class Solution {
    fun longestOnes(nums: IntArray, k: Int): Int {
        if(nums.all{it == 0}){
            return Math.min(k, nums.size)
        }
        if(nums.all{it == 1}){
            return nums.size
        }
        var l = 0
        var r = 0
        var k = k
        var max = 0
        var cur = 0
        while(r < nums.size){
            if(nums[r] == 0){
                while(k == 0 && l < r){
                    if(nums[l] == 2){
                        k++
                    }
                    l++
                    cur--
                }
                if(k == 0){
                    while(r < nums.size && nums[r] == 0){
                        r++
                    }
                    r--
                } else {
                    k--
                    nums[r] = 2
                    cur++
                    max = Math.max(cur, max)   
                }
            } else if (nums[r] == 1){
                cur++
                max = Math.max(cur, max)
            }
            r++
        }
        return max
    }
}
