//[2,7,9,3,1,1]

class Solution {
    fun rob(nums: IntArray): Int {
        val sum = IntArray(nums.size)
        val maxSum = Array<MyPair>(nums.size){ i -> MyPair(0, 0)}
        for(i in nums.indices){
            sum[i] = nums[i]
            maxSum[i].first = nums[i]
            maxSum[i].second = i
            if(i - 2 >= 0){
                if(maxSum[i - 2].first == sum[i-2]){
                    sum[i] += sum[i - 2]
                    maxSum[i].first = sum[i]
                } else {
                    maxSum[i].first = sum[maxSum[i - 2].second] + nums[i]
                    sum[i] += sum[maxSum[i - 2].second]
                }
            }
            if(i - 1 >= 0 && maxSum[i - 1].first > maxSum[i].first){
                maxSum[i].first = maxSum[i - 1].first
                maxSum[i].second = maxSum[i - 1].second
            }
        }
        return sum.max()!!
    }
}

data class MyPair(var first: Int, var second: Int)
