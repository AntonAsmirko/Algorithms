func rotate(matrix [][]int) {
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < i; j++ {
			matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
		}
	}
	for l, r := 0, len(matrix[0])-1; l < r; l, r = l+1, r-1 {
		for i := 0; i < len(matrix); i++ {
			matrix[i][l], matrix[i][r] = matrix[i][r], matrix[i][l]
		}
	}
}