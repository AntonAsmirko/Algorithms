import "math"

func maxProduct(nums []int) (result int) {
	result = math.MinInt32
	prevDp := nums
	for _, i := range nums {
		if i > result {
			result = i
		}
	}
	for i := 2; i <= len(nums); i++ {
		dp := make([]int, len(nums)-i+1)
		for j := 0; j < len(nums)-i+1; j++ {
			dp[j] = prevDp[j] * nums[j+i-1]
			if dp[j] > result {
				result = dp[j]
			}
		}
		prevDp = dp
	}
	return
}