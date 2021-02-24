var combinations [][]int

func clone(arr []int) (b []int) {
	b = make([]int, len(arr))
	copy(b, arr)
	return
}

func makeCombinations(n, k, i int, combination []int) {
	if len(combination) == k {
		combinations = append(combinations, combination)
		return
	}
	if i > n {
		return
	}
	makeCombinations(n, k, i+1, clone(combination))
	makeCombinations(n, k, i+1, append(clone(combination), i))
}

func combine(n int, k int) [][]int {
	combinations = make([][]int, 0)
	makeCombinations(n, k, 1, make([]int, 0))
	return combinations
}