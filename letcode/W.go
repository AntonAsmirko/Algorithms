func insertionSort(nums []int) {
	for i := 0; i < len(nums); i++ {
		min := nums[i]
		minI := i
		for j := i; j < len(nums); j++ {
			if nums[j] < min {
				min = nums[j]
				minI = j
			}
		}
		nums[i], nums[minI] = nums[minI], nums[i]
	}
}

func sortColors(nums []int) {
	insertionSort(nums)
}