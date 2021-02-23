import "sort"
import "math"

func merge(intervals [][]int) [][]int {
	sort.SliceStable(intervals, func(i, j int) bool {
		f, s := intervals[i], intervals[j]
		return f[0] < s[0]
	})
	for i := 1; i < len(intervals); i++ {
		if intervals[i-1][1] >= intervals[i][0] {
			if i+1 < len(intervals) {
				intervals = append(append(intervals[:i-1], []int{intervals[i-1][0], int(math.Max(float64(intervals[i][1]), float64(intervals[i-1][1])))}), intervals[i+1:]...)
			} else {
				intervals = append(intervals[:i-1], []int{intervals[i-1][0], int(math.Max(float64(intervals[i][1]), float64(intervals[i-1][1])))})
			}
			i--
		}
	}
	return intervals
}