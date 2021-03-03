var subsets [][]int

func clone(arr []int) (res []int) {
	res = make([]int, len(arr))
	copy(res, arr)
	return
}

func makeSubsets(subset, count []int, i int) {
	if i == len(count) {
		subsets = append(subsets, subset)
		return
	}
	if count[i] > 0 {
		nncount := clone(count)
		nncount[i]--
		nnsubset := append(clone(subset), i-10)
		makeSubsets(nnsubset, nncount, i)
	}
	makeSubsets(subset, count, i+1)
}

func subsetsWithDup(nums []int) [][]int {
	subsets = make([][]int, 0)
	count := make([]int, 21)
	for i := 0; i < len(nums); i++ {
		count[nums[i]+10] += 1
	}
	makeSubsets(make([]int, 0), count, 0)
	return subsets
}