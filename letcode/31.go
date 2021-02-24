import "sort"

func sortt(l, r int, nums []int) {
	for i := l; i < r; i++ {
		min, minI := nums[i], i
		for j := i; j < r; j++ {
			if nums[j] < min {
				min, minI = nums[j], j
			}
		}
		nums[i], nums[minI] = nums[minI], nums[i]
	}
}

func nextPermutation(nums []int) {
	if len(nums) == 1 {
		return
	}
	for i := len(nums) - 2; i >= 0; i-- {
		if nums[i] < nums[i+1] {
			min := nums[i+1]
			minI := i + 1
			for j := i + 2; j < len(nums); j++ {
				if nums[j] > nums[i] && nums[j] < min {
					min = nums[j]
					minI = j
				}
			}
			nums[i], nums[minI] = nums[minI], nums[i]
			sortt(i+1, len(nums), nums)
			return
		}
	}
	sort.Ints(nums)
}