import "math"

var dist int = 40000
var sum int = 0

func makeTriplets(nums []int, i int, triplet []int, target int) {
	if len(triplet) == 3 {
		newSum := triplet[0] + triplet[1] + triplet[2]
		newDist := int(math.Abs(float64(newSum - target)))
		if newDist < dist {
			dist = newDist
			sum = newSum
		}
		return
	}
	if i >= len(nums) {
		return
	}
	makeTriplets(nums, i+1, triplet, target)
	makeTriplets(nums, i+1, append(triplet, nums[i]), target)
}

func threeSumClosest(nums []int, target int) int {
	dist = 40000
	sum = 0
	makeTriplets(nums, 0, make([]int, 0), target)
	return sum
}