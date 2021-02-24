func subsets(nums []int) [][]int {
	result := make([][]int, 0)
	for i := 0; i < 1<<len(nums); i++ {
		tmp := make([]int, 0)
		j := i
		for k := 0; j != 0; k++ {
			rem := j % 2
			if rem == 1 {
				tmp = append(tmp, nums[k])
			}
			j /= 2
		}
		result = append(result, tmp)
	}
	return result
}