func findDuplicates(nums []int) (res []int) {
	res = make([]int, 0)
	for i := 0; i < len(nums); i++ {
		if nums[i] != i+1 {
			if nums[i] < 0 {
				continue
			}
			if nums[i] == nums[nums[i]-1] {
				if nums[nums[i]-1] < 0 {
					continue
				}
				res = append(res, nums[i])
				nums[nums[i]-1] *= -1
			} else {
				nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
				i--
			}
		}
	}
	return res
}