class Solution {
    func climbStairs(_ n: Int) -> Int {
        var dp = Array(repeating: 0, count: n)
        dp[0] = 1
        if dp.count == 1 {
            return dp[0]
        }
        dp[1] = 2
        for index in 2..<n {
            dp[index] = dp[index - 1] + dp[index - 2]
        }
        return dp[n - 1]
    }
}
