import "math"

var dp []int
var arr []int

func computeDp(pos int) int {
	if pos == len(arr)-1 {
		return 0
	}
	if dp[pos] != -1 {
		return dp[pos]
	}
	minSum := math.MaxInt32
	for i := pos + 1; i < len(arr) && i <= pos+arr[pos]; i++ {
		minSum = int(math.Min(float64(computeDp(i)), float64(minSum)))
	}
	dp[pos] = 1 + minSum
	return dp[pos]
}

func jump(nums []int) int {
	dp = make([]int, len(nums))
	for i := 0; i < len(dp); i++ {
		dp[i] = -1
	}
	arr = nums
	return computeDp(0)
}