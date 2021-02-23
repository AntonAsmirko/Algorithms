var combinations [][]int

func clone(arr []int) []int {
	b := make([]int, len(arr))
	copy(b, arr)
	return b
}

func sum(arr []int) int {
	sum := 0
	for _, item := range arr {
		sum += item
	}
	return sum
}

func makeCombinations(candidates, cur []int, target, i int) {
	if i >= len(candidates) {
		return
	}
	summ := sum(cur)
	if summ == target {
		combinations = append(combinations, clone(cur))
		return
	} else if summ > target {
		return
	}
	makeCombinations(candidates, clone(cur), target, i+1)
	makeCombinations(candidates, append(clone(cur), candidates[i]), target, i)
}

func combinationSum(candidates []int, target int) [][]int {
	combinations = make([][]int, 0)
	makeCombinations(candidates, make([]int, 0), target, 0)
	return combinations
}