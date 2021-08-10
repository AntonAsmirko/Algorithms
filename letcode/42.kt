class Solution {
    fun trap(height: IntArray): Int {
        var res = 0
        for(i in height.indices){
            var maxLeft = 0
            var maxRight = 0
            for(j in i - 1 downTo 0){
                maxLeft = Math.max(maxLeft, height[j])
            }
            for(j in i+1 until height.size){
                maxRight = Math.max(maxRight, height[j])
            }
            res += Math.max(Math.min(maxLeft, maxRight) - height[i], 0)
        }
        return res
    }
}
