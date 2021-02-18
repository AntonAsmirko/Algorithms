import "math"

func minPathSum(grid [][]int) int {
    if len(grid) == 1 && len(grid[0]) == 1{
        return grid[0][0]
    } else if len(grid) == 1{
        sum := 0
        for i:= 0; i < len(grid[0]); i++{
            sum += grid[0][i]
        }
        return sum
    } else if len(grid[0]) == 1{
        sum := 0
        for i:=0; i < len(grid); i++{
            sum += grid[i][0]
        }
        return sum
    }
    dp := make([][]int, len(grid))
    for i:=0; i < len(grid); i++{
        dp[i] = make([]int, len(grid[0]))
        for j:= 0; j < len(grid[0]); j++{
            dp[i][j] = 0
        }
    }
    dp[0][0] = grid[0][0]
    for i:=1; i < len(grid[0]); i++{
        dp[0][i] += grid[0][i] + dp[0][i-1] 
    }
    for i:=1; i < len(grid); i++{
        dp[i][0] += grid[i][0] + dp[i-1][0]
    }
    for i:=1; i< len(grid); i++{
        for j :=1; j< len(grid[0]);j++{
            dp[i][j] = int(math.Min(float64(dp[i-1][j]), float64(dp[i][j-1]))) + grid[i][j]
        }
    }
    return dp[len(grid)-1][len(grid[0])-1]
}