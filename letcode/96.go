var dp []int

func catalan(n int) (res int) {
	if n == 0 || n == 1 {
		return 1
	}
	if dp[n] != -1 {
		return dp[n]
	}
	for i := 0; i < n; i++ {
		res += catalan(i) * catalan(n-i-1)
	}
	dp[n] = res
	return
}

func numTrees(n int) int {
	dp = make([]int, n+1)
	for i := 0; i < len(dp); i++ {
		dp[i] = -1
	}
	return catalan(n)
}