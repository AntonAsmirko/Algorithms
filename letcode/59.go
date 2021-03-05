func generateMatrix(n int) (res [][]int) {
	res = make([][]int, n)
	for i := 0; i < len(res); i++ {
		res[i] = make([]int, n)
	}
	count, step := 1, 0
	for ; count <= n*n; step++ {
		i := step
		j := step
		for ; j < n-step; j++ {
			res[i][j] = count
			count++
		}
		if count > n*n {
			break
		}
		j--
		i++
		for ; i < n-step; i++ {
			res[i][j] = count
			count++
		}
		if count > n*n {
			break
		}
		i--
		j--
		for ; j >= step; j-- {
			res[i][j] = count
			count++
		}
		if count > n*n {
			break
		}
		j++
		i--
		for ; i > step; i-- {
			res[i][j] = count
			count++
		}
		if count > n*n {
			break
		}
	}
	return
}