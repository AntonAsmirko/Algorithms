import "math"

var Triangle [][]int
var dp [][]int

func dfs(i, j int) int {
	if i == len(Triangle)-1 {
		dp[i][j] = Triangle[i][j]
		return dp[i][j]
	}
	var (
		path1 int
		path2 int
	)
	if dp[i+1][j] != -1 {
		path1 = dp[i+1][j]
	} else {
		path1 = dfs(i+1, j)
	}
	if dp[i+1][j+1] != -1 {
		path2 = dp[i+1][j+1]
	} else {
		path2 = dfs(i+1, j+1)
	}
	dp[i][j] = int(math.Min(float64(path1), float64(path2))) + Triangle[i][j]
	return dp[i][j]
}

func minimumTotal(triangle [][]int) int {
	Triangle = triangle
	dp = make([][]int, len(triangle))
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, i+1)
		for j := 0; j < len(dp[i]); j++ {
			dp[i][j] = -1
		}
	}
	dfs(0, 0)
	return dp[0][0]
}