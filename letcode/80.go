func removeDuplicates(nums []int) int {
	count := 0
	removed := 0
	for i:= 1; i < len(nums); i++{
		if nums[i] == nums[i-1]{
			count++
		} else {
			count = 0
		}
		if count == 2{
			pos := i
			num := nums[pos]
			for ;pos < len(nums) - removed && nums[pos] == num; pos++{}
			if pos >= len(nums){
				return i - removed
			} else {
				removed += pos - i
				for j:=i ;pos < len(nums); j, pos = j+1, pos+1{
					nums[j] = nums[pos]
				}
				count = 0
			}
		}
	}
	return len(nums) - removed
}