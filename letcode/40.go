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
	if candidates[i] > 0 {
		candidates[i]--
		makeCombinations(candidates, append(clone(cur), i), target, i)
		candidates[i]++
	}
}

func combinationSum2(candidates []int, target int) [][]int {
	combinations = make([][]int, 0)
	candidates2 := make([]int, 55)
	for _, item := range candidates {
		candidates2[item]++
	}
	makeCombinations(candidates2, make([]int, 0), target, 0)
	return combinations
}