func setZeroes(matrix [][]int) {
	pendingZeros := make([][]int, 0)
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0]); j++ {
			if matrix[i][j] == 0 {
				pendingZeros = append(pendingZeros, []int{i, j})
			}
		}
	}
	for pos := 0; pos < len(pendingZeros); pos++ {
		zero := pendingZeros[pos]
		for i := zero[0] - 1; i >= 0; i-- {
			matrix[i][zero[1]] = 0
		}
		for i := zero[0] + 1; i < len(matrix); i++ {
			matrix[i][zero[1]] = 0
		}
		for j := zero[1] - 1; j >= 0; j-- {
			matrix[zero[0]][j] = 0
		}
		for j := zero[1] + 1; j < len(matrix[0]); j++ {
			matrix[zero[0]][j] = 0
		}
	}
}