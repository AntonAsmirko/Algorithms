class Solution {
    fun summaryRanges(nums: IntArray): List<String> {
        if(nums.size == 0){
            return listOf<String>()
        }
        if(nums.size == 1){
            return listOf(nums[0].toString())
        }
        var start = nums[0]
        var end = nums[0]
        var res = mutableListOf<String>()
        for(i in 1 until nums.size){
            if(nums[i] == nums[i - 1] + 1){
                end = nums[i]
            } else {
                res.add(if(start != end) "${start}->${end}" else start.toString())
                start = nums[i]
                end = nums[i]
            }
        }
        res.add(if(start != end) "${start}->${end}" else start.toString())
        return res
    }
}
