var (
	startI    int
	startJ    int
	endI      int
	endJ      int
	peakCount = 0
)

var count int = 0

func dfs(grid [][]int, visited [][]bool, i, j, length int) {
	if i == endI && j == endJ {
		if length < peakCount+1 || length > peakCount+1 {
			return
		}
		count++
		return
	}
	if i > 0 && grid[i-1][j] != -1 && !visited[i-1][j] {
		visited[i-1][j] = true
		dfs(grid, visited, i-1, j, length+1)
		visited[i-1][j] = false
	}
	if j > 0 && grid[i][j-1] != -1 && !visited[i][j-1] {
		visited[i][j-1] = true
		dfs(grid, visited, i, j-1, length+1)
		visited[i][j-1] = false
	}
	if i < len(grid)-1 && grid[i+1][j] != -1 && !visited[i+1][j] {
		visited[i+1][j] = true
		dfs(grid, visited, i+1, j, length+1)
		visited[i+1][j] = false
	}
	if j < len(grid[0])-1 && grid[i][j+1] != -1 && !visited[i][j+1] {
		visited[i][j+1] = true
		dfs(grid, visited, i, j+1, length+1)
		visited[i][j+1] = false
	}
}

func uniquePathsIII(grid [][]int) int {
	startI = 0
	startJ = 0
	endI = 0
	endJ = 0
	peakCount = 0
	count = 0
	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[0]); j++ {
			if grid[i][j] == 1 {
				startI = i
				startJ = j
			}
			if grid[i][j] == 2 {
				endI = i
				endJ = j
			}
			if grid[i][j] == 0 {
				peakCount++
			}
		}
	}
	visited := make([][]bool, len(grid))
	for i := 0; i < len(visited); i++ {
		visited[i] = make([]bool, len(grid[0]))
	}
	visited[startI][startJ] = true
	dfs(grid, visited, startI, startJ, 0)
	return count
}