class Solution {
    fun countPrimes(n: Int): Int {
        if(n < 3){
            return 0
        }
        val nums = BooleanArray(n){true}
        nums[0] = false
        nums[1] = false
        for(i in 2 until n){
            if(nums[i]){
                var j = i + i
                while(j < n){
                    nums[j] = false
                    j+=i
                }
            }
        }
        return nums.count{it}
    }
}
