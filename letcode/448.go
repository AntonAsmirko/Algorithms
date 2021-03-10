import "math"

func findDisappearedNumbers(nums []int) (res []int) {
	res = make([]int, 0)
	for i := 0; i < len(nums); i++ {
		if nums[int(math.Abs(float64(nums[i])))-1] > 0 {
			nums[int(math.Abs(float64(nums[i])))-1] *= -1
		}
	}
	for i := 0; i < len(nums); i++ {
		if nums[i] > 0 {
			res = append(res, i+1)
		}
	}
	return
}