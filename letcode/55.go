func canJump(nums []int) bool {
    dp := make([]bool, len(nums))
    dp[len(dp) - 1] = true
    out:
    for i:= len(dp) - 2; i >=0; i--{
        item := nums[i]
        for j:= 0; j <= item;j++{
            if i + j < len(dp){
                if dp[i + j]{
                    dp[i] = true
                    continue out
                }
            }
        }
    }
    return dp[0]
}