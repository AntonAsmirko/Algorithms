func productExceptSelf(nums []int) (result []int) {
	result = make([]int, len(nums))
	if len(result) == 0 {
		return
	}
	if len(result) == 1 {
		result[0] = nums[0]
		return
	}
	for i := 0; i < len(result); i++ {
		result[i] = nums[i]
	}
	for i := 1; i < len(nums); i++ {
		nums[i] *= nums[i-1]
	}
	for i := len(result) - 2; i >= 0; i-- {
		result[i] *= result[i+1]
	}
	for i := 0; i < len(result); i++ {
		if i == 0 {
			result[i] = result[i+1]
		} else if i == len(result)-1 {
			result[i] = nums[i-1]
		} else {
			result[i] = result[i+1] * nums[i-1]
		}
	}
	return
}